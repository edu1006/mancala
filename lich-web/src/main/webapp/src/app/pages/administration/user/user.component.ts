import { GroupService } from './../../../service/group.service';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { UserService } from './../../../service/user.service';
import { User } from './../../../model/user';
import { Group } from './../../../model/group';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { SelectModel } from 'src/app/common/select/select.model';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent extends BaseComponent implements OnInit {

  idModalUserData = 'idModalUserData';
  idModalUserStatus = 'idModalUserStatus';
  idModalChangePassword = 'idModalChangePassword';

  filter: User;
  users: Array<User>;
  totalRecords: number;

  user: User;
  groups: Array<Group>;
  selectGroups: Array<SelectModel<Group>>;

  constructor(translateService: TranslateService,
              private userService: UserService,
              private groupService: GroupService) {
    super(translateService);
  }

  ngOnInit() {
    this.filter = new User();
    this.filter.status = undefined;

    this.groupService.findEnabled().subscribe(
      res => {
        this.groups = res;
        if (!this.groups || this.groups.length <= 0) {
          this.addMessageError(this.getMessage('users.groups.not.exists'));
        }
      },
      error => {
        this.addMessageError('', error);
      }
    );
  }

  find() {
    this.users = null;
    this.totalRecords = null;

    this.userService.count(this.filter).subscribe(
      res => {
        this.totalRecords = res;
      }
    );
  }

  loadUsers(event: PaginationLoadLazy) {
    this.userService.find(this.filter, event.first, (event.first + event.rows)).subscribe(
      res => {
        this.users = res;
      }
    );
  }

  newUser() {
    this.user = new User();
    this.loadSelectGroups();
    this.openModal(this.idModalUserData);
  }

  editUser(item: User) {
    this.userService.findById(item.id).subscribe(
      res => {
        this.user = res;
        this.loadSelectGroups(this.user.groups);
        this.openModal(this.idModalUserData);
      }
    );
  }

  loadSelectGroups(groups?: Array<Group>) {
    let idsGroups: Array<number>;

    if (groups) {
      idsGroups = groups.map(g => g.id);
    }

    this.selectGroups = new Array();
    for (const group of this.groups) {
      const selected = (idsGroups && idsGroups.includes(group.id));
      this.selectGroups.push(new SelectModel<Group>(group, selected));
    }
  }

  save(form: NgForm) {
    if (!form.invalid) {
      const groupsSelected: Array<Group> = new Array<Group>();
      for (const selected of this.selectGroups) {
        if (selected.selected) {
          groupsSelected.push(selected.model);
        }
      }

      if (groupsSelected.length <= 0) {
        this.addMessageError(this.getMessage('users.groups.empty'));
      } else {
        this.user.groups = groupsSelected;
        this.saveUser(() => {
          this.closeModal(this.idModalUserData);
          form.resetForm();
        });
      }
    }
  }

  saveUser(functionAfterSave: any) {
    this.userService.save(this.user).subscribe(
      res => {
        this.find();
        this.addMessageSuccess(this.getMessage('users.save.success'));

        functionAfterSave();
      },
      error => this.addMessageError(this.getMessage('users.save.error'), error)
    );
  }

  enableDisableUser(item: User) {
    this.user = Object.assign({}, item);
    this.openModal(this.idModalUserStatus);
  }

  confirmEnableDisableUser(status: number) {
    this.user.status = status;
    this.saveUser(() => this.closeModal(this.idModalUserStatus));
  }

  changePassword(item: User) {
    this.user = Object.assign({}, item);
    this.openModal(this.idModalChangePassword);
  }

  confirmeChangePassword(form: NgForm) {
    if (form.valid) {
      this.userService.defineNewPassword(this.user).subscribe(
        res => {
          this.addMessageSuccess(this.getMessage('users.save.success'));
          this.closeModal(this.idModalChangePassword);
          form.resetForm();
        },
        error => {
          this.addMessageError(this.getMessage('users.save.error'));
          this.closeModal(this.idModalChangePassword);
        }
      );
    }
  }
}
