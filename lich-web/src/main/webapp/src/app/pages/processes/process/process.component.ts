import { DetailComponent } from './detail/detail.component';
import { QueryComponent } from './query/query.component';
import { BaseComponent } from './../../base.component';
import { TranslateService } from './../../../internationalization/translate.service';
import { OperationEnum } from './../../../enums/operation.enum';
import { Component, OnInit, ViewChild } from '@angular/core';
import { JobProcess } from '../../../model/job.process';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.css']
})
export class ProcessComponent extends BaseComponent implements OnInit {

  operationEnum = OperationEnum;
  operation: OperationEnum;

  @ViewChild('query')
  query: QueryComponent;

  @ViewChild('detail')
  detail: DetailComponent;

  constructor(translateService: TranslateService) {
    super(translateService);
    this.operation = OperationEnum.QUERY;
  }

  ngOnInit() {
  }

  newProcess() {
    this.operation = OperationEnum.INSERT;
    this.detail.newProcess();
  }

  editProcess(item: JobProcess) {
    this.operation = OperationEnum.UPDATE;
    this.detail.editProcess(item);
  }

  backToQuery() {
    this.operation = OperationEnum.QUERY;
    this.query.find();
  }

}
