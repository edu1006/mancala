import { Action } from '@ngrx/store';

export const GET_USER = '[user] info';

export class GetUser implements Action {
    readonly type = GET_USER;

    constructor(public payload ?: any) {
    }
}
