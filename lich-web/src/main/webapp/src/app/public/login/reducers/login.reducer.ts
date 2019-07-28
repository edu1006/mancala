import { LoginActionTypes } from './../actions/login.actions';
import { User } from './../../../model/user';
import { Action } from '@ngrx/store';
import { LoginActions } from '../actions/login.actions';


export interface LoginState {
  loggedIn: boolean;
  user: User;
}

export const initialState: LoginState = {
  loggedIn: false,
  user: undefined
};

export function reducer(state = initialState, action: LoginActions): LoginState {
  switch (action.type) {
    case LoginActionTypes.LoginAction:
      return {
        loggedIn: true,
        user: action.payload.user
      };
    case LoginActionTypes.LogoutAction:
      return {
        loggedIn: false,
        user: undefined
      };
    default:
      return state;
  }
}
