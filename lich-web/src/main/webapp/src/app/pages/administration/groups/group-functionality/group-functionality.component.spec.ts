import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupFunctionalityComponent } from './group-functionality.component';

describe('GroupFunctionalityComponent', () => {
  let component: GroupFunctionalityComponent;
  let fixture: ComponentFixture<GroupFunctionalityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupFunctionalityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupFunctionalityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
