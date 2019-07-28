import { User } from './../../../model/user';
import { Action } from '@ngrx/store';

export enum LoginActionTypes {
    LoginAction = '[Login] Action',
    LogoutAction = '[Logout] Action',
}

export class LoginAction implements Action {
    readonly type = LoginActionTypes.LoginAction;
    constructor(public payload: {user: User}) {}
}

export class LogoutAction implements Action {
    readonly type = LoginActionTypes.LogoutAction;
}

export type LoginActions = LoginAction | LogoutAction;
