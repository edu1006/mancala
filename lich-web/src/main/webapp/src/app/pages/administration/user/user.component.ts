import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { PaginationLoadLazy } from './../../../common/pagination/pagination.load';
import { UserService } from './../../../service/user.service';
import { User } from './../../../model/user';
import { Group } from './../../../model/group';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent extends BaseComponent implements OnInit {

  idModalUserData = 'idModalUserData';
  idModalUserStatus = 'idModalUserStatus';

  filter: User;
  users: Array<User>;
  totalRecords: number;

  user: User;
  groups: Array<Group>;

  constructor(translateService: TranslateService,
              private userService: UserService) {
    super(translateService);
  }

  ngOnInit() {
    this.filter = new User();
    this.filter.status = undefined;
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
    this.openModal(this.idModalUserData);
  }

  editUser(item: User) {
    this.user = Object.assign({}, item);
    this.openModal(this.idModalUserData);
  }

  save(form: NgForm) {
    if (!form.invalid) {
      this.saveUser(() => this.closeModal(this.idModalUserData));
    }
  }

  saveUser(functionAfterSave: any) {
    this.userService.save(this.user).subscribe(
      res => {
        this.find();
        this.addMessageSuccess('Success to save user.');

        functionAfterSave();
      },
      error => this.addMessageError('Error to save user.', error)
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
}
