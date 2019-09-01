import { PageQuery } from './../../../../common/pagination/page.query';
import { Agent } from './../../../../model/agent';
import { createAction, props } from '@ngrx/store';

export const agentsCount = createAction(
    '[Agents API] Agents Count',
    props<{filter: Agent}> ()
);

export const agentsCountSuccess = createAction(
    '[Agents API] Agents Count Success',
    props<{filter: Agent, count: number}> ()
);

export const agentsCountError = createAction('[Agents API] Agents Count Error');

export const agentsPageRequested = createAction(
    '[Agents API] Agents Page Requested',
    props<{page: PageQuery}> ()
);

export const agentsPageRequestedSuccess = createAction(
    '[Agents API] Agents Page Requested Success',
    props<{agents: Agent[]}> ()
);

export const agentsPageRequestedError = createAction('[Agents API] Agents Page Requested Error');

export const agentsSaveSucess = createAction(
    '[Agents API] Agents Sabe Success',
    props<{agent: Agent}> ()
);
