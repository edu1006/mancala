import { AgentService } from './../../../../service/agent.service';
import { AppState } from './../../../../reducers/index';
import { Store, select } from '@ngrx/store';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { Injectable } from '@angular/core';
import { mergeMap, catchError, map, withLatestFrom } from 'rxjs/operators';
import { Agent } from '../../../../model/agent';
import { of } from 'rxjs';
import * as AgentActions from '../actions/agent.actions';
import { selectAgentsFilter } from '../selectors/agent.selectors';

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
            withLatestFrom(this.store.pipe(select(selectAgentsFilter))),
            mergeMap(([action, filter]) => {
                return this.agentService.find(filter, action.page.first, action.page.max)
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
