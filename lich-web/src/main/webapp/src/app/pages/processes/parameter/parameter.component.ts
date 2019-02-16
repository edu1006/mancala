import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { ParameterService } from './../../../service/parameter.service';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { NgForm } from '@angular/forms';
import { Parameter } from './../../../model/parameter';
import { Component, OnInit } from '@angular/core';
import { StatusEnum } from '../../../enums/status.enum';
import { find } from 'rxjs/operators';

@Component({
  selector: 'app-parameter',
  templateUrl: './parameter.component.html',
  styleUrls: ['./parameter.component.css']
})
export class ParameterComponent extends BaseComponent implements OnInit {

  idModalParameterSave = 'idModalParameterSave';
  idModalParameterStatus = 'idModalParameterStatus';

  statusEnum = StatusEnum;

  filter: Parameter;
  parameters: Array<Parameter>;
  totalRecords: number;

  parameter: Parameter;

  constructor(translateService: TranslateService,
              private parameterService: ParameterService) {
    super(translateService);
  }

  ngOnInit() {
    this.filter = new Parameter();
    this.filter.status = null;
  }

  cleanFilter() {
    this.ngOnInit();
    this.find();
  }

  find() {
    this.parameters = null;
    this.totalRecords = null;

    this.parameterService.countByFilter(this.filter).subscribe(
      res => this.totalRecords = res
    );
  }

  loadParameters(event: PaginationLoadLazy) {
    this.parameterService.findByFilter(this.filter, event.first, (event.first + event.rows)).subscribe(
      res => this.parameters = res
    );
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
        this.find();

        functionAfterSave();
      },
      error => this.addMessageError(this.getMessage('parameter.save.error'), error)
    );
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
