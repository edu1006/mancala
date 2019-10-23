import { createSelector } from '@ngrx/store';

export const selectLoginState = state => state.login;

export const userLogged = createSelector(
    selectLoginState,
    login => login.user
);

export const isLoggedIn = createSelector(
    selectLoginState,
    login => login.loggedIn
);

export const selectFunctionalities = createSelector(
    selectLoginState,
    login => (login) ? login.f : []
);
