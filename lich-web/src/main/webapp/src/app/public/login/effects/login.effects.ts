import { JwtHelperService } from '@auth0/angular-jwt';
import { Crypt } from './../../../util/crypt';
import { Router } from '@angular/router';
import { LoginAction, LogoutAction, AccessAction } from './../actions/login.actions';
import { Injectable } from '@angular/core';
import { Actions, Effect, ofType } from '@ngrx/effects';
import { LoginActionTypes } from '../actions/login.actions';
import { tap, map, mergeMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { defer } from 'rxjs';

@Injectable()
export class LoginEffects {

  @Effect()
  login$ = this.actions$.pipe(
    ofType<LoginAction>(LoginActionTypes.LoginAction),
    mergeMap(action => {
      const jsonUser = JSON.stringify(action.payload.user);
      const jsonUserEnc = Crypt.cryptAes(jsonUser);

      localStorage.setItem('lc_user', jsonUserEnc);

      // get permissions
      const accessToken = localStorage.getItem('access_token');

      const f: Array<number> = this.jwtHelper.decodeToken(accessToken).f;
      return of(new AccessAction({ f }));
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

      if (this.isAccessTokenEnable() || this.isRefreshTokenEnable()) {
        const userData = localStorage.getItem('lc_user');

        if (userData) {
          const userDataDec = Crypt.decryptAes(userData);
          return of(new LoginAction({user: JSON.parse(userDataDec)}));
        }
      }
    }
  );

  private isRefreshTokenEnable(): boolean {
    const refreshToken = localStorage.getItem('refresh_token');
    return !this.jwtHelper.isTokenExpired(refreshToken);
  }

  private isAccessTokenEnable(): boolean {
    const accessToken = localStorage.getItem('access_token');
    return !this.jwtHelper.isTokenExpired(accessToken);
  }

  constructor(private actions$: Actions, private router: Router, private jwtHelper: JwtHelperService) {}

}
