import { userLogged } from './../../../public/login/selectors/login.selectors';
import { LogoutAction } from './../../../public/login/actions/login.actions';
import { AppState } from './../../../reducers/index';
import { Store, select } from '@ngrx/store';
import { User } from './../../../model/user';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  loggedUser: User;

  constructor(private router: Router,
              private store: Store<AppState>) {
  }

  ngOnInit() {
    this.store.pipe(
      select(userLogged)
    ).subscribe(
      user => this.loggedUser = user
    );
  }

  logout() {
    this.store.dispatch(new LogoutAction());
  }

}
