import { PaginationLoadLazy } from './../pagination/pagination.load';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-table-select-rows',
  templateUrl: './table-select-rows.component.html',
  styleUrls: ['./table-select-rows.component.css']
})
export class TableSelectRowsComponent implements OnInit {

  @Output()
  reloadTable = new EventEmitter();

  @Input()
  rowsPerPage: number;

  constructor() { }

  ngOnInit() {
    if (!this.rowsPerPage) {
      this.rowsPerPage = 5; // Default pagination
    }
  }

  onChangeRows() {
    const event = new PaginationLoadLazy();

    event.rows = this.rowsPerPage;
    event.first = 0;

    this.reloadTable.emit(event);
  }

}
