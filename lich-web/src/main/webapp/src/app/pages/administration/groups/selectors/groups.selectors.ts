import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromGroupReducers from '../reducers/groups.reducers';
import { GroupState } from '../reducers/groups.reducers';
import { PageQuery } from '../../../../common/pagination/page.query';

export const selectGroupState = createFeatureSelector<GroupState>('groups');

export const selectAllGroups = createSelector(
    selectGroupState,
    fromGroupReducers.selectAll
);

export const selectGroupsCount = createSelector(
    selectGroupState,
    state => state.countGroups
);

export const selectGroupsFilter = createSelector(
    selectGroupState,
    state => state.filter
);

export const selectGroupsPage = (page: PageQuery) => createSelector(
    selectAllGroups,
    groups => {
        return groups.slice(page.first, page.max);
    }
);
