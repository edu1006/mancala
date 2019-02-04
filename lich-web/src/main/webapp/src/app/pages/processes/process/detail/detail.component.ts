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
  periodicityEnum = PeriodicityEnum;
  calendarLocale: CalendarLocale;

  jobProcessData: JobProcessData;
  jobProcess: JobProcess;

  constructor(translateService: TranslateService,
              calendarLocale: CalendarLocale,
              private processService: ProcessService) {
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

}
