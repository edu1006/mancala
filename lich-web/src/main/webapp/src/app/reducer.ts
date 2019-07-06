import { User } from './model/user';
import { ActionReducerMap, createFeatureSelector } from '@ngrx/store';
import { reducerUser } from './pages/home/ngrx-store/reducer';

// Root's Reducers (User Info)

export interface State {
    user: User;
}

export const reducers: ActionReducerMap<State> = {
    user: reducerUser
};

export const getUserState = createFeatureSelector<User>('user');
