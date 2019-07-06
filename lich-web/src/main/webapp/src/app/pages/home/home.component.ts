import { State, getUserState } from './../../reducer';
import { User } from './../../model/user';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;

  constructor(private store: Store<State>) { }

  ngOnInit() {
    this.store.select(getUserState).subscribe(
      user => this.user = user
    );
  }

}
