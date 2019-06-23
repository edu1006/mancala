import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableRowsSelectComponent } from './table-rows-select.component';

describe('TableRowsSelectComponent', () => {
  let component: TableRowsSelectComponent;
  let fixture: ComponentFixture<TableRowsSelectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableRowsSelectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableRowsSelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
