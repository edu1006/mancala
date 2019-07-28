import { Router } from '@angular/router';
import { LoginAction, LogoutAction } from './../actions/login.actions';
import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { LoginActionTypes } from '../actions/login.actions';
import { tap, map } from 'rxjs/operators';
import { of } from 'rxjs';
import { defer } from 'rxjs';

@Injectable()
export class LoginEffects {

  @Effect({dispatch: false})
  login$ = this.actions$.pipe(
    ofType<LoginAction>(LoginActionTypes.LoginAction),
    tap(action => {
      localStorage.setItem('lc_user', JSON.stringify(action.payload.user));
    })
  );

  @Effect({dispatch: false})
  logout$ = this.actions$.pipe(
    ofType<LogoutAction>(LoginActionTypes.LogoutAction),
    tap(() => {
      localStorage.removeItem('lc_user');

      localStorage.removeItem('access_token');
      localStorage.removeItem('refresh_token');

      this.router.navigateByUrl('/login');
    })
  );

  @Effect()
  init$ = defer(
    () => {
      const userData = localStorage.getItem('lc_user');

      if (userData) {
        return of(new LoginAction({user: JSON.parse(userData)}));
      }

    }
  );

  constructor(private actions$: Actions, private router: Router) {}

}
