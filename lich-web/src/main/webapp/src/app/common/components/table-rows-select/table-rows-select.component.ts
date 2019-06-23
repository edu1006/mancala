import { PaginationLoadLazy } from './../../pagination/pagination.load';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';

@Component({
  selector: 'app-table-rows-select',
  templateUrl: './table-rows-select.component.html',
  styleUrls: ['./table-rows-select.component.css']
})
export class TableRowsSelectComponent implements OnInit {

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
