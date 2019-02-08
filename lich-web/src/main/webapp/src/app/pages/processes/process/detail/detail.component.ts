import { FormatadorUtil } from './../../../../util/formatador.util';
import { JobProcessData } from './../../../../model/job.process.data';
import { ProcessService } from './../../../../service/process.service';
import { TranslateService } from './../../../../internationalization/translate.service';
import { BaseOperationComponent } from './../../../base.operation.component';
import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { JobProcess } from '../../../../model/job.process';
import { StatusEnum } from '../../../../enums/status.enum';
import { TypeExecutionEnum } from '../../../../enums/type.execution.enum';
import { TypeStepProcessEnum } from '../../../../enums/type.step.process';
import { CalendarLocale } from '../../../../common/calendar/calendar.locale';
import { PeriodicityEnum } from '../../../../enums/periodicity.enum';
import { StepProcess } from 'src/app/model/step.process';
import { AgentTypeEnum } from '../../../../enums/agent.type.enum';
import { AgentService } from '../../../../service/agent.service';
import { Agent } from '../../../../model/agent';

@Component({
  selector: 'app-process-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent extends BaseOperationComponent implements OnInit {

  @Output()
  loadQuery = new EventEmitter();

  idModalStepSave = 'idModalStepSave';
  idModalStepStatus = 'idModalStepStatus';
  idModalStepDelete = 'idModalStepDelete';

  statusEnum = StatusEnum;
  typeExecutionEnum = TypeExecutionEnum;
  typeStepProcessEnum = TypeStepProcessEnum;
  periodicityEnum = PeriodicityEnum;
  agentTypeEnum = AgentTypeEnum;
  calendarLocale: CalendarLocale;

  jobProcessData: JobProcessData;
  jobProcess: JobProcess;
  stepProcess: StepProcess;
  agents: Array<Agent>;

  constructor(translateService: TranslateService,
              calendarLocale: CalendarLocale,
              private processService: ProcessService,
              private agentService: AgentService) {
    super(translateService);
    this.calendarLocale = calendarLocale;
  }

  ngOnInit() {
    console.log(this.operation);
    this.processService.getJobProcessData().subscribe(
      res => {
        this.jobProcessData = res;

        this.jobProcessData.monthDays = new Array();
        for (let i = 1; i < 29; i++) {
            this.jobProcessData.monthDays.push(i);
        }
        this.jobProcessData.monthDays.push(99); // Last day of month.

      }
    );
  }

  newProcess() {
    this.jobProcess = new JobProcess();
  }

  onChangeTypeExection() {
    this.jobProcess.periodicity = null;
    this.jobProcess.periodicityStartDate = null;
    this.jobProcess.periodicityEndDate = null;
  }

  onChangePeriodicity() {
    this.jobProcess.xValue = null;
    this.jobProcess.timeExecution = '00:00';
    this.jobProcess.weekDay = null;
    this.jobProcess.monthDay = null;
  }

  onChangePeriodicityStart() {
    if (this.jobProcess.periodicityEndDate &&
        this.jobProcess.periodicityEndDate < this.jobProcess.periodicityStartDate) {
      this.jobProcess.periodicityEndDate = this.jobProcess.periodicityStartDate;
    }
  }

  save(form: NgForm) {
    console.log(form.valid);
  }

  backToQuery() {
    this.loadQuery.emit();
  }

  newStep() {
    this.stepProcess = new StepProcess();
    this.openModal(this.idModalStepSave);
  }

  onChangeAgentType() {
    this.agents = null;
    this.stepProcess.idAgent = null;

    if (this.stepProcess.typeAgent && this.agentTypeEnum.Localhost !== this.stepProcess.typeAgent) {
      this.agentService.findEnableByType(this.stepProcess.typeAgent).subscribe(
        res => {
          this.agents = res;
        }, error => this.addMessageError('general.error', error)
      );
    }
  }

  onChangeAskToContinue() {
    this.stepProcess.askToContinueMessage = null;
  }

  saveStep(form: NgForm) {
    if (form.valid) {
      // set order to step process
      if (!this.stepProcess.order) {
        this.stepProcess.order = this.jobProcess.stepsProcesses.length + 1;
        this.jobProcess.stepsProcesses.push(Object.assign('', this.stepProcess));
      } else {
        this.updateStepOnListSteps();
      }

      this.orderSteps();

      this.stepProcess = null;

      this.closeModal(this.idModalStepSave);
    }
  }

  updateStepOnListSteps(): void {
    const index = this.jobProcess.stepsProcesses.findIndex((sp) => sp.order === this.stepProcess.order);
    this.jobProcess.stepsProcesses.splice(index, 1);
    this.jobProcess.stepsProcesses.push(Object.assign('', this.stepProcess));
  }

  orderSteps(): void {
    // sort steps
    this.jobProcess.stepsProcesses = this.jobProcess.stepsProcesses.sort((sp1, sp2) => sp1.order - sp2.order);
  }

  editStep(item: StepProcess) {
    this.stepProcess = Object.assign('', item);
    this.openModal(this.idModalStepSave);
  }

  enableDisableStep(item: StepProcess) {
    this.stepProcess = item;
    this.openModal(this.idModalStepStatus);
  }

  confirmEnableDisableStep(status: StatusEnum) {
    this.stepProcess.status = status;
    this.closeModal(this.idModalStepStatus);

    this.stepProcess = null;
  }

  deleteStep(item: StepProcess) {
    this.stepProcess = item;
    this.openModal(this.idModalStepDelete);
  }

  confirmDeleteStep() {
    const index = this.jobProcess.stepsProcesses.findIndex((sp) => sp.order === this.stepProcess.order);
    this.jobProcess.stepsProcesses.splice(index, 1);
    this.closeModal(this.idModalStepDelete);

    for (const step of this.jobProcess.stepsProcesses) {
      if (step.order > this.stepProcess.order) {
        step.order = step.order - 1;
      }
    }

    this.stepProcess = null;
  }

  stepToUp(item: StepProcess) {
    const index = this.jobProcess.stepsProcesses.findIndex((sp) => sp.order === item.order);
    const stepUp: StepProcess = this.jobProcess.stepsProcesses[index - 1];

    stepUp.order = stepUp.order + 1;
    item.order = item.order - 1;

    this.orderSteps();
  }

  setToDown(item: StepProcess) {
    const index = this.jobProcess.stepsProcesses.findIndex((sp) => sp.order === item.order);
    const stepDown: StepProcess = this.jobProcess.stepsProcesses[index + 1];

    stepDown.order = stepDown.order - 1;
    item.order = item.order + 1;

    this.orderSteps();
  }

}
