import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TableSelectRowsComponent } from './table-select-rows.component';

describe('TableSelectRowsComponent', () => {
  let component: TableSelectRowsComponent;
  let fixture: ComponentFixture<TableSelectRowsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TableSelectRowsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TableSelectRowsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
