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
              private globalData: GlobalData) {
    this.loggedUser = globalData.getUser();
  }

  ngOnInit() {
  }

  logout() {
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');

    this.router.navigate(['/login']);
  }

}
