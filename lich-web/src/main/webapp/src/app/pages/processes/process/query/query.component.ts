import { JobProcessStatusVo } from './../../../../model/job.process.status';
import { AgentTypeEnum } from './../../../../enums/agent.type.enum';
import { PaginationLoadLazy } from './../../../../common/pagination/pagination.load';
import { ProcessService } from './../../../../service/process.service';
import { TranslateService } from './../../../../internationalization/translate.service';
import { BaseOperationComponent } from './../../../base.operation.component';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { JobProcess } from '../../../../model/job.process';
import { StatusEnum } from 'src/app/enums/status.enum';
import { TypeExecutionEnum } from '../../../../enums/type.execution.enum';
import { TypeStepProcessEnum } from '../../../../enums/type.step.process';
import { StepProcess } from '../../../../model/step.process';
import 'reflect-metadata';

@Component({
  selector: 'app-process-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.css']
})
export class QueryComponent extends BaseOperationComponent implements OnInit {

  idModalJobStatus = 'idModalJobStatus';

  @Output()
  loadNewProcess = new EventEmitter();
  @Output()
  loadEditProcess = new EventEmitter();

  typeExecutionEnum = TypeExecutionEnum;
  statusEnum = StatusEnum;

  filter: JobProcess;
  jobs: Array<JobProcess>;
  totalRecords: number;

  job: JobProcess;

  constructor(translateService: TranslateService,
              private processService: ProcessService) {
    super(translateService);
    this.filter = new JobProcess();
    this.filter.status = null;
  }

  ngOnInit() {
  }

  cleanFilter() {
    this.filter = new JobProcess();
    this.filter.status = null;
    this.find();
  }

  find() {
    this.jobs = null;
    this.totalRecords = null;

    this.processService.count(this.filter).subscribe(
      res => this.totalRecords = res
    );
  }

  loadJobs(event: PaginationLoadLazy) {
    this.processService.find(this.filter, event.first, (event.first + event.rows)).subscribe(
      res => this.jobs = res
    );
  }

  newProcess() {
    this.loadNewProcess.emit();
  }

  editProcess(item: JobProcess) {
    this.processService.loadById(item.id).subscribe(
      res => {

        // load agents
        for (const step of res.stepsProcesses) {
          if (step.type === TypeStepProcessEnum.STEP_PARALLEL) {
            // load steps parallels
            for (const par of step.stepsParallels) {
              this.loadAgent(par);
            }

          } else {
            this.loadAgent(step);
          }
        }

        this.loadEditProcess.emit(res);
      },
      error => this.addMessageError(null, error)
    );
  }

  loadAgent(step: StepProcess) {
    if (!step.idAgent) {
      step.typeAgent = AgentTypeEnum.Localhost;
    }
  }

  enableDisableJob(item: JobProcess) {
    this.job = item;
    this.openModal(this.idModalJobStatus);
  }

  confirmEnableDisableJob(status: StatusEnum) {
    const jobProcessStatusVo = new JobProcessStatusVo();

    jobProcessStatusVo.idJob = this.job.id;
    jobProcessStatusVo.status = status;

    this.processService.updateStatus(jobProcessStatusVo).subscribe(
      res => {
        this.job.status = status;
        this.addMessageSuccess(this.getMessage('process.save.success'));
        this.closeModal(this.idModalJobStatus);
      },
      error => this.addMessageError(this.getMessage('process.save.error'), error)
    );
  }
}
