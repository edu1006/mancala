import { Agent } from './../../../../model/agent';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import { createReducer, Action, on } from '@ngrx/store';
import * as AgentActions from '../actions/agent.actions';

export const adapter: EntityAdapter<Agent> = createEntityAdapter<Agent>();

export interface AgentState extends EntityState<Agent> {
    countAgents: number;
}

export const initialAgentState: AgentState = adapter.getInitialState({
    countAgents: 0
});

const initAgentReducer = createReducer(
    initialAgentState,
    on(AgentActions.agentsPageRequested, (state)  => ({
        ...state
    })),
    on(AgentActions.agentsPageRequestedSuccess, (state, {agents}) => {
        return adapter.addMany(agents, {...state});
    }),
    on(AgentActions.agentsPageRequestedError, (state) => ({
        ...state, loading: false
    })),
    on(AgentActions.agentsCountSuccess, (state, {count}) => {
        return {...initialAgentState, countAgents: count};
    }),
    on(AgentActions.agentsSaveSucess, (state, {agent}) => {
        return adapter.upsertOne(agent, state);
    })
);

export function agentReducer(state: AgentState | undefined, action: Action) {
    return initAgentReducer(state, action);
}

export const {
    selectAll,
    selectEntities,
    selectIds,
    selectTotal
} = adapter.getSelectors();
