import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { Component, OnInit } from '@angular/core';
import { GroupService } from '../../../service/group.service';
import { Group } from '../../../model/group';
import { BaseComponent } from '../../base.component';
import { TranslateService } from '../../../internationalization/translate.service';
import { NgForm } from '@angular/forms';

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
  totalRecords: number;

  group: Group;

  constructor(translateService: TranslateService,
              private groupService: GroupService) {
    super(translateService);
  }

  ngOnInit() {
    this.clearFilter();
  }

  clearFilter() {
    this.filter = new Group();
    this.filter.status = null;

    this.find();
  }

  find() {
    this.groups = null;
    this.totalRecords = null;

    this.groupService.count(this.filter).subscribe(
      res => {
        this.totalRecords = res as number;
      }
    );
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
        this.addMessageSuccess(this.getMessage('groups.save.sucess'));

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
