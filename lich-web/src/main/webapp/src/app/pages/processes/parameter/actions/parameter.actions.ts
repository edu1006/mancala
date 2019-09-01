import { Parameter } from './../../../../model/parameter';
import { createAction, props } from '@ngrx/store';
import { PageQuery } from '../../../../common/pagination/page.query';
import { ParameterComponent } from '../parameter.component';

export const parametersCount = createAction(
    '[Parameter API] Parameters Count',
    props<{filter: Parameter}> ()
);

export const parametersCountSuccess = createAction(
    '[Parameter API] Parameters Count Success',
    props<{filter: Parameter, count: number}> ()
);

export const parametersCountError = createAction(
    '[Parameter API] Parameters Count Error'
);

export const parametersPageRequested = createAction(
    '[Parameter API] Parameters Page Requested',
    props<{page: PageQuery}> ()
);

export const parametersPageRequestedSuccess = createAction(
    '[Parameter API] Parameters Page Requested Success',
    props<{parameters: Array<Parameter>}> ()
);

export const parametersPageRequestedError = createAction(
    '[Parameter API] Parameters Page Requested Error'
);

export const parametersSaveSuccess = createAction(
    '[Parameter API] Parameters Save Success',
    props<{parameter: Parameter}> ()
);
