import { User } from './../../../model/user';
import { Action } from '@ngrx/store';

export enum LoginActionTypes {
    LoginAction = '[Login] Action',
    LogoutAction = '[Logout] Action',
    AccessAction = '[Login] Access Action',
}

export class LoginAction implements Action {
    readonly type = LoginActionTypes.LoginAction;
    constructor(public payload: {user: User}) {}
}

export class LogoutAction implements Action {
    readonly type = LoginActionTypes.LogoutAction;
}

export class AccessAction implements Action {
    readonly type = LoginActionTypes.AccessAction;
    constructor(public payload: {f: Array<number>}) {}
}

export type LoginActions =
    LoginAction |
    LogoutAction |
    AccessAction;
