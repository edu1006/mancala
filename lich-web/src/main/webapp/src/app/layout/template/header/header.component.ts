import { LogoutAction } from './../../../public/login/actions/login.actions';
import { AppState } from './../../../reducers/index';
import { Store } from '@ngrx/store';
import { User } from './../../../model/user';
import { GlobalData } from './../../../util/global.data';
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
              private globalData: GlobalData,
              private store: Store<AppState>) {
    this.loggedUser = globalData.getUser();
  }

  ngOnInit() {
  }

  logout() {
    this.store.dispatch(new LogoutAction());
    // localStorage.removeItem('access_token');
    // localStorage.removeItem('refresh_token');

    // this.router.navigate(['/login']);
  }

}
