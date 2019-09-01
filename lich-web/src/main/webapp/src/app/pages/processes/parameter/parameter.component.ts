import { Table } from 'primeng/table';
import { Store, select } from '@ngrx/store';
import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { ParameterService } from './../../../service/parameter.service';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { NgForm } from '@angular/forms';
import { Parameter } from './../../../model/parameter';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { StatusEnum } from '../../../enums/status.enum';
import { Observable, of } from 'rxjs';
import { AppState } from '../../../reducers/index';
import { selectParametersCount, selectParametersPage } from './selectors/parameter.selectors';
import { PageQuery } from '../../../common/pagination/page.query';
import { tap, catchError } from 'rxjs/operators';
import { parametersCountSuccess, parametersCount, parametersPageRequested, parametersSave, parametersSaveSuccess } from './actions/parameter.actions';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css']
})
export class ParameterComponent extends BaseComponent implements OnInit, OnDestroy {

  @ViewChild('parametersTable')
  tableParameters: Table;

  idModalParameterSave = 'idModalParameterSave';
  idModalParameterStatus = 'idModalParameterStatus';

  statusEnum = StatusEnum;

  filter: Parameter;
  parameters: Array<Parameter>;
  totalRecords$: Observable<number>;
  executeFind: boolean;

  parameter: Parameter;
  functionAfterSave: Function;

  constructor(translateService: TranslateService,
              private store: Store<AppState>,
              private parameterService: ParameterService) {
    super(translateService);
  }

  ngOnInit() {
    this.totalRecords$ = this.store.pipe(select(selectParametersCount));
    this.cleanFilter();
  }

  ngOnDestroy() {
    this.store.dispatch(parametersCountSuccess({ filter: undefined, count: 0 }));
  }

  cleanFilter() {
    this.filter = new Parameter();
    this.filter.status = null;

    this.find();
  }

  find() {
    this.parameters = null;

    if (this.tableParameters) {
      this.tableParameters.reset();
    }

    this.store.dispatch(parametersCount({filter: Object.assign({}, this.filter)}));
  }

  loadParameters(event: PaginationLoadLazy) {

    const page: PageQuery = {
      first: event.first,
      max: (event.first + event.rows)
    };
    this.executeFind = true;

    this.store
      .pipe(
        select(selectParametersPage(page)),
        tap(parameters => {
          if (parameters.length > 0) {
            this.parameters = Object.assign([], parameters);
          } else if (this.executeFind) {
            this.store.dispatch(parametersPageRequested({page}));
            this.executeFind = false;
          }
        }),
        catchError(err => of([]))
      ).subscribe();
  }

  newParameter() {
    this.parameter = new Parameter();
    this.openModal(this.idModalParameterSave);
  }

  save(form: NgForm) {
    if (form.valid) {
      this.saveParameter(() => {
        form.resetForm();
        this.closeModal(this.idModalParameterSave);
      });
    }
  }

  saveParameter(functionAfterSave: Function) {
    this.parameterService.save(this.parameter).subscribe(
      res => {
        this.addMessageSuccess(this.getMessage('parameter.save.success'));
        this.store.dispatch(parametersSaveSuccess({ parameter: res }));

        functionAfterSave();
      },
      error => this.addMessageError(this.getMessage('parameter.save.error'), error)
    );
  }

  runFunctionAfterSave() {
    this.functionAfterSave();
    this.functionAfterSave = null;
  }

  editParameter(item: Parameter) {
    this.parameter = Object.assign(new Parameter(), item);
    this.openModal(this.idModalParameterSave);
  }

  enableDisableParameter(item: Parameter) {
    this.parameter = Object.assign(new Parameter(), item);
    this.openModal(this.idModalParameterStatus);
  }

  confirmeEnableDisableParameter(status: StatusEnum) {
    this.parameter.status = status;
    this.saveParameter(() => {
      this.closeModal(this.idModalParameterStatus);
    });
  }

}
