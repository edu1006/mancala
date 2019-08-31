import { ParameterState } from '../reducers/parameter.reducers';
import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromParameterReducers from '../reducers/parameter.reducers';
import { PageQuery } from '../../../../common/pagination/page.query';

export const selectParameterState = createFeatureSelector<ParameterState>('parameters');

export const selectAllParameters = createSelector (
    selectParameterState,
    fromParameterReducers.selectAll
);

export const selectParametersCount = createSelector(
    selectParameterState,
    state => state.countParameters
);

export const selectParametersFilter = createSelector(
    selectParameterState,
    state => state.filter
);

export const selectParametersPage = (page: PageQuery) => createSelector(
    selectAllParameters,
    parameters => {
        return parameters.slice(page.first, page.max);
    }
);
