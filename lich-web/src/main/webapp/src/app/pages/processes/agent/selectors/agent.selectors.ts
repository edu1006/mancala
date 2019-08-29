import { PageQuery } from './../../../../common/pagination/page.query';
import { AgentState } from './../reducers/agent.reducers';
import { createFeatureSelector, createSelector } from '@ngrx/store';
import * as fromAgentReducers from '../reducers/agent.reducers';

export const selectAgentState = createFeatureSelector<AgentState>('agents');

export const selectAllAgents = createSelector(
    selectAgentState,
    fromAgentReducers.selectAll
);

export const selectAgentsCount = createSelector(
    selectAgentState,
    state => state.countAgents
);

export const selectAgentsPage = (page: PageQuery) => createSelector(
    selectAllAgents,
    agents => {
        return agents.slice(page.first, page.max);
    }
);
