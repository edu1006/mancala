import { createSelector } from '@ngrx/store';

export const selectLoginState = state => state.login;

export const userLogged = createSelector(
    selectLoginState,
    login => login.user
);
