import { User } from './../../../model/user';
import { GET_USER } from './action';

export const initialState: User = new User();

export function reducerUser(state = initialState, action: any): User {
    switch (action.type) {
        case GET_USER:
            return Object.assign({}, state, action.payload);
        default:
            return state;
    }
}