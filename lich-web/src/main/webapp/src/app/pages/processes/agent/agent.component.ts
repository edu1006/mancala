import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { NgForm } from '@angular/forms';
import { Agent } from './../../../model/agent';
import { AgentService } from './../../../service/agent.service';
import { Component, OnInit } from '@angular/core';
import { AgentTypeEnum } from '../../../enums/agent.type.enum';
import { StatusEnum } from '../../../enums/status.enum';

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent extends BaseComponent implements OnInit {

  idModalAgentSave = 'idModalAgentSave';
  idModalAgentStatus = 'idModalAgentStatus';

  agentTypeEnum = AgentTypeEnum;
  statusEnum = StatusEnum;

  filter: Agent;
  agents: Array<Agent>;
  totalRecords: number;

  agent: Agent;

  constructor(translateService: TranslateService,
              private agentService: AgentService) {
    super(translateService);
    this.filter = new Agent();
  }

  ngOnInit() {
  }

  cleanFilter() {
    this.filter = new Agent();
    this.find();
  }

  find() {
    this.agents = null;
    this.totalRecords = null;

    this.agentService.count(this.filter).subscribe(
      res => this.totalRecords = res
    );
  }

  loadAgents(event: PaginationLoadLazy) {
    this.agentService.find(this.filter, event.first, (event.first + event.rows)).subscribe(
      res => this.agents = res
    );
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
        this.find();
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
