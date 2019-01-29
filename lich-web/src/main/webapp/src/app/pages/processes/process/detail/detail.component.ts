import { TranslateService } from './../../../../internationalization/translate.service';
import { BaseOperationComponent } from './../../../base.operation.component';
import { OperationEnum } from './../../../../enums/operation.enum';
import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';

@Component({
  selector: 'app-process-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent extends BaseOperationComponent implements OnInit {

  @Output()
  loadQuery = new EventEmitter();

  constructor(translateService: TranslateService) {
    super(translateService);
  }

  ngOnInit() {
    console.log(this.operation);
  }

  newProcess() {
    console.log('new process');
  }

  backToQuery() {
    this.loadQuery.emit();
  }

}
