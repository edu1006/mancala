import { TranslateService } from './../../../../internationalization/translate.service';
import { BaseOperationComponent } from './../../../base.operation.component';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-process-query',
  templateUrl: './query.component.html',
  styleUrls: ['./query.component.css']
})
export class QueryComponent extends BaseOperationComponent implements OnInit {

  @Output()
  loadNewProcess = new EventEmitter();

  constructor(translateService: TranslateService) {
    super(translateService);
  }

  ngOnInit() {
  }

  find() {
    console.log('find');
  }

  newProcess() {
    this.loadNewProcess.emit();
  }

}
