import { AgentService } from './../../../../service/agent.service';
import { AppState } from './../../../../reducers/index';
import { Store } from '@ngrx/store';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Injectable } from '@angular/core';
import { mergeMap, catchError, map } from 'rxjs/operators';
import { Agent } from '../../../../model/agent';
import { of } from 'rxjs';
import * as AgentActions from '../actions/agent.actions';

@Injectable()
export class AgentEffects {

    loadAgentCount$ = createEffect(() => this.actions$
        .pipe(
            ofType(AgentActions.agentsCount),
            mergeMap((action) => {
                return this.agentService.count(action.filter)
                    .pipe(
                        map((count: number) => AgentActions.agentsCountSuccess({ count }))
                    );
            })
        )
    );

    loadAgentsPage$ = createEffect(() => this.actions$
        .pipe(
            ofType(AgentActions.agentsPageRequested),
            mergeMap((action) => {
                return this.agentService.find(action.filter, action.page.first, action.page.max)
                    .pipe(
                        catchError((err) => {
                            return of(AgentActions.agentsPageRequestedError());
                        })
                    );
            }),
            map((agents: Agent[]) => AgentActions.agentsPageRequestedSuccess({agents}))
        )
    );

    saveAgent$ = createEffect(() => this.actions$
        .pipe(
            ofType(AgentActions.agentsSave),
            mergeMap((action) => {
                return this.agentService.save(action.agent)
                    .pipe(
                        map((agent: Agent) => AgentActions.agentsSaveSucess({ agent })),
                        catchError((err) => {
                            return of(AgentActions.agentsSaveError());
                        })
                    );
            })
        )
    );

    constructor (private actions$: Actions,
                 private store: Store<AppState>,
                 private agentService: AgentService) {}
}
