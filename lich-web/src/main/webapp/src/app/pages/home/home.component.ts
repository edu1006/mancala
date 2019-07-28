import { AppState } from './../../reducers/index';
import { User } from './../../model/user';
import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { userLogged } from '../../public/login/selectors/login.selectors';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;

  constructor(private store: Store<AppState>) {}

  ngOnInit() {
    this.store.pipe(
      select(userLogged)
    ).subscribe(
      user => this.user = user
    );

  }

}
