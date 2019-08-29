import { AppState } from './../../../reducers/index';
import { Store, select } from '@ngrx/store';
import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { NgForm } from '@angular/forms';
import { Agent } from './../../../model/agent';
import { AgentService } from './../../../service/agent.service';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { AgentTypeEnum } from '../../../enums/agent.type.enum';
import { StatusEnum } from '../../../enums/status.enum';
import { PageQuery } from '../../../common/pagination/page.query';
import { agentsPageRequested, agentsCount, agentsSaveSucess, agentsCountSuccess } from './actions/agent.actions';
import { selectAgentsPage, selectAgentsCount, selectAllAgents } from './selectors/agent.selectors';
import { tap, catchError } from 'rxjs/operators';
import { of, Observable } from 'rxjs';
import { Table } from 'primeng/table';

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent extends BaseComponent implements OnInit, OnDestroy {

  @ViewChild('agentsTable')
  tableAgents: Table;

  idModalAgentSave = 'idModalAgentSave';
  idModalAgentStatus = 'idModalAgentStatus';

  agentTypeEnum = AgentTypeEnum;
  statusEnum = StatusEnum;

  filter: Agent;
  agents: Array<Agent>;
  totalRecords$: Observable<number>;
  executeFind: boolean;

  agent: Agent;

  constructor(translateService: TranslateService,
              private store: Store<AppState>,
              private agentService: AgentService) {
    super(translateService);
    this.filter = new Agent();
  }

  ngOnInit() {
    this.totalRecords$ = this.store.pipe(select(selectAgentsCount));
    this.cleanFilter();
  }

  ngOnDestroy() {
    this.store.dispatch(agentsCountSuccess({count: undefined}));
  }

  cleanFilter() {
    this.filter = new Agent();
    this.find();
  }

  find() {
    this.agents = null;
    this.executeFind = true;

    if (this.tableAgents) {
      this.tableAgents.reset();
    }

    this.store.dispatch(agentsCount({filter: Object.assign({}, this.filter)}));
  }

  loadAgents(event: PaginationLoadLazy) {

    const page: PageQuery = {
      first: event.first,
      max: (event.first + event.rows)
    };

    this.executeFind = true;

    this.store
      .pipe(
        select(selectAgentsPage(page)),
        tap(agents => {
          if (agents.length > 0) {
            this.agents = Object.assign([], agents);
          } else if (this.executeFind) {
            this.store.dispatch(agentsPageRequested({filter: Object.assign({}, this.filter), page}));
            this.executeFind = false;
          }
        }),
        catchError(err => of([]))
      ).subscribe();
  }

  newAgent() {
    this.agent = new Agent();
    this.agent.status = StatusEnum.ENABLED;
    this.openModal(this.idModalAgentSave);
  }

  editAgent(item: Agent) {
    this.agent = Object.assign({}, item);
    this.openModal(this.idModalAgentSave);
  }

  save(form: NgForm) {
    if (form.valid) {
      this.saveAgent(() => {
        form.resetForm();
        this.closeModal(this.idModalAgentSave);
      });
    }
  }

  saveAgent(functionAfterSave: Function) {
    this.agentService.save(this.agent).subscribe(
      res => {
        this.store.dispatch(agentsSaveSucess({agent: res}));
        this.addMessageSuccess(this.getMessage('agent.save.success'));

        functionAfterSave();
      },
      error => {
        this.addMessageError(this.getMessage('agent.save.error'), error);
      }
    );
  }

  enableDisableAgent(item: Agent) {
    this.agent = Object.assign({}, item);
    this.openModal(this.idModalAgentStatus);
  }

  confirmEnableDisableAgent(status: StatusEnum) {
    this.agent.status = status;
    this.saveAgent(() => this.closeModal(this.idModalAgentStatus));
  }

}
