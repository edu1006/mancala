import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../../../service/group.service';
import { Group } from '../../../model/group';
import { BaseComponent } from '../../base.component';
import { TranslateService } from '../../../internationalization/translate.service';
import { NgForm } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { AppState } from '../../../reducers/index';
import { Observable } from 'rxjs';
import { selectGroupsCount } from './selectors/groups.selectors';
import { groupsCount } from './actions/groups.actions';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent extends BaseComponent implements OnInit {

  idModalGroupData = 'idModalGroupData';
  idModalGroupStatus = 'idModalGroupStatus';

  filter: Group;
  groups: Array<Group>;
  //totalRecords: number;
  totalRecords$: Observable<number>;
  executeFind: boolean;

  group: Group;

  constructor(translateService: TranslateService,
              private store: Store<AppState>,
              private groupService: GroupService) {
    super(translateService);
  }

  ngOnInit() {
    this.totalRecords$ = this.store.pipe(select(selectGroupsCount));
    this.clearFilter();
  }

  clearFilter() {
    this.filter = new Group();
    this.filter.status = null;

    this.find();
  }

  find() {
    this.groups = null;
    this.store.dispatch(groupsCount({ filter: Object.assign({}, this.filter) }));
  }

  loadGroups(event: PaginationLoadLazy) {
    this.groupService.find(this.filter, event.first, (event.first + event.rows)).subscribe(
      res => {
        this.groups = res;
      }
    );
  }

  newGroup() {
    this.group = new Group();
    this.openModal(this.idModalGroupData);
  }

  editGroup(item: Group) {
    this.group = Object.assign({}, item);
    this.openModal(this.idModalGroupData);
  }

  save(form: NgForm) {
    if (!form.invalid) {
      this.saveGroup(() => this.closeModal(this.idModalGroupData));
    }
  }

  saveGroup(funcAfterSave: any) {
    this.groupService.save(this.group).subscribe(
      res => {
        this.find();
        this.addMessageSuccess(this.getMessage('groups.save.success'));

        funcAfterSave();
      },
      error => this.addMessageError(this.getMessage('groups.save.error'), error)
    );
  }

  enableDisableGroup(item: Group) {
    this.group = Object.assign({}, item);
    this.openModal(this.idModalGroupStatus);
  }

  confirmEnableDisableGroup(status: number) {
    this.group.status = status;
    this.saveGroup(() => this.closeModal(this.idModalGroupStatus));
  }

}
