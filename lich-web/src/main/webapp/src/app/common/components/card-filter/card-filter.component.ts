import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-card-filter',
  templateUrl: './card-filter.component.html',
  styleUrls: ['./card-filter.component.css']
})
export class CardFilterComponent implements OnInit {

  @Input()
  id: string;

  @Input()
  buttonAddText: string;

  @Output()
  clickAddButton: EventEmitter<any> = new EventEmitter();

  @Output()
  clickSearchButton: EventEmitter<any> = new EventEmitter();

  @Input()
  showClearButton: boolean;

  @Output()
  clickClearButton: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
    if (!this.showClearButton) {
      this.showClearButton = false;
    }
  }

  newElement() {
    // call the method to new element
    this.clickAddButton.emit();
  }

  searchElement() {
    // call the method to search element
    this.clickSearchButton.emit();

    this.closeAccordion();
  }

  clearFilter() {
    // call the method to clear filter
    this.clickClearButton.emit();

    this.closeAccordion();
  }

  closeAccordion() {
    // close filter accordion after search
    $('#' + this.id + '-accordion').collapse('hide');
  }
}
