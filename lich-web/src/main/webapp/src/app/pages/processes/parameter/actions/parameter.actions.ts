import { Parameter } from './../../../../model/parameter';
import { Action } from '@ngrx/store';
import { PageQuery } from '../../../../common/pagination/page.query';

export enum ParameterActionTypes {
    ParametersCount = '[Parameter API] Parameters Count',
    ParametersCountSuccess = '[Parameter API] Parameters Count Success',
    ParametersCountError = '[Parameter API] Parameters Count Error',

    ParametersPageRequested = '[Parameter API] Parameters Page Requested',
    ParametersPageRequestedSuccess = '[Parameter API] Parameters Page Requested Success',
    ParametersPageRequestedError = '[Parameter API] Parameters Page Requested Error'
}

export class ParametersCount implements Action {
    readonly type = ParameterActionTypes.ParametersCount;
    constructor(public payload: {filter: Parameter}) {}
}

export class ParametersCountSuccess implements Action {
    readonly type = ParameterActionTypes.ParametersCountSuccess;
    constructor(public payload: {filter: Parameter, count: number}) {}
}

export class ParametersCountError implements Action {
    readonly type = ParameterActionTypes.ParametersCountError;
}

export class ParametersPageRequested implements Action {
    readonly type = ParameterActionTypes.ParametersPageRequested;
    constructor(public payload: {page: PageQuery}) {}
}

export class ParametersPageRequestedSuccess implements Action {
    readonly type = ParameterActionTypes.ParametersPageRequestedSuccess;
    constructor(public payload: {parameters: Array<Parameter>}) {}
}

export class ParametersPageRequestedError implements Action {
    readonly type = ParameterActionTypes.ParametersPageRequestedError;
}

export type ParameterActions =
    ParametersCount
    | ParametersCountSuccess
    | ParametersCountError
    | ParametersPageRequested
    | ParametersPageRequestedSuccess
    | ParametersPageRequestedError;
