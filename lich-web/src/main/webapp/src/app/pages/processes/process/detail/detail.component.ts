import { EnumValue } from './../../../../model/enum.value';
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

@Component({
  selector: 'app-process-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent extends BaseOperationComponent implements OnInit {

  @Output()
  loadQuery = new EventEmitter();

  statusEnum = StatusEnum;
  typeExecutionEnum = TypeExecutionEnum;
  typeStepProcessEnum = TypeStepProcessEnum;

  jobProcessData: JobProcessData;
  jobProcess: JobProcess;

  constructor(translateService: TranslateService,
              private processService: ProcessService) {
    super(translateService);
  }

  ngOnInit() {
    console.log(this.operation);
    this.processService.getJobProcessData().subscribe(
      res => this.jobProcessData = res
    );
  }

  newProcess() {
    this.jobProcess = new JobProcess();
  }

  onChangeTypeExection(e: Event) {
    console.log(this.jobProcessData);
  }

  save(form: NgForm) {
    console.log(form.valid);
  }

  backToQuery() {
    this.loadQuery.emit();
  }

}
