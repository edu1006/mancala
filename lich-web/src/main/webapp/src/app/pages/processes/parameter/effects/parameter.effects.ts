import { ParameterService } from './../../../../service/parameter.service';
import { Store, select } from '@ngrx/store';
import { Actions, ofType, createEffect } from '@ngrx/effects';
import { Injectable } from '@angular/core';
import { AppState } from '../../../../reducers/index';
import { mergeMap, map, catchError, withLatestFrom } from 'rxjs/operators';
import { of } from 'rxjs';
import { selectParametersFilter } from '../selectors/parameter.selectors';
import { Parameter } from '../../../../model/parameter';
import * as ParameterActions from '../actions/parameter.actions';

@Injectable()
export class ParameterEffects {

    loadParametersCount = createEffect(() => this.actions$
        .pipe(
            ofType(ParameterActions.parametersCount),
            mergeMap((action) => this.parameterService.countByFilter(action.filter)
                .pipe(
                    map((count: number) => ParameterActions.parametersCountSuccess({ filter: action.filter, count })),
                    catchError(err => {
                        console.log(err);
                        return of(ParameterActions.parametersCountError());
                    })
                ))
        )
    );

    loadParametersPage = createEffect(() => this.actions$
        .pipe(
            ofType(ParameterActions.parametersPageRequested),
            withLatestFrom(this.store.pipe(select(selectParametersFilter))),
            mergeMap(([action, filter]) =>
                this.parameterService.findByFilter(filter, action.page.first, action.page.max)
                .pipe(
                    catchError(err => {
                        console.log(err);
                        return of(ParameterActions.parametersPageRequestedError());
                    })
                )),
            map((parameters: Array<Parameter>) => ParameterActions.parametersPageRequestedSuccess({ parameters }))
        )
    );

    /*
    @Effect()
    loadParametersPage = this.actions$
        .pipe(
            ofType<ParametersPageRequested>(ParameterActionTypes.ParametersPageRequested),
            withLatestFrom(this.store.pipe(select(selectParametersFilter))),
            mergeMap(([action, filter]) =>
                this.parameterService.findByFilter(filter, action.payload.page.first, action.payload.page.max)
                .pipe(
                    catchError(err => {
                        console.log(err);
                        return of(new ParametersPageRequestedError());
                    })
                )),
            map((parameters: Array<Parameter>) => new ParametersPageRequestedSuccess({parameters}))
        );
        */

    constructor(private actions$: Actions,
                private store: Store<AppState>,
                private parameterService: ParameterService) {}

}
