import { EntityAdapter, createEntityAdapter, EntityState } from '@ngrx/entity';
import { Group } from '../../../../model/group';
import { createReducer, on, Action } from '@ngrx/store';
import * as GroupActions from '../actions/groups.actions';

export const adapter: EntityAdapter<Group> = createEntityAdapter<Group>();

export interface GroupState extends EntityState<Group> {
    filter: Group;
    countGroups: number;
}

export const initialGroupState: GroupState = adapter.getInitialState({
    filter: undefined,
    countGroups: 0,
    entities: []
});

const initGroupReducer = createReducer(
    initialGroupState,
    on(GroupActions.groupsPageRequestedSuccess, (state, {groups}) => {
        return adapter.addMany(groups, {...state});
    }),
    on(GroupActions.groupsCountSuccess, (state, {filter, count}) => {
        return {...initialGroupState, filter, countGroups: count};
    }),
    on(GroupActions.groupsSaveSuccess, (state, {group}) => {
        return adapter.upsertOne(group, state);
    })
);

export function groupReducer(state: GroupState | undefined, action: Action) {
    return initGroupReducer(state, action);
}

export const {
    selectAll,
    selectEntities,
    selectIds,
    selectTotal
} = adapter.getSelectors();
