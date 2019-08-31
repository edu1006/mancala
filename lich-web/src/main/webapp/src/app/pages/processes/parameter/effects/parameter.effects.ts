import { ParametersCount } from './../actions/parameter.actions';
import { ParameterService } from './../../../../service/parameter.service';
import { Store, select } from '@ngrx/store';
import { Actions, ofType, Effect } from '@ngrx/effects';
import { Injectable } from '@angular/core';
import { AppState } from '../../../../reducers/index';
import { ParameterActionTypes, ParametersCountSuccess, ParametersCountError, ParametersPageRequested, ParametersPageRequestedError, ParametersPageRequestedSuccess } from '../actions/parameter.actions';
import { mergeMap, map, catchError, withLatestFrom } from 'rxjs/operators';
import { of } from 'rxjs';
import { selectParametersFilter } from '../selectors/parameter.selectors';
import { Parameter } from '../../../../model/parameter';

@Injectable()
export class ParameterEffects {

    @Effect()
    loadParameterCount = this.actions$
        .pipe(
            ofType<ParametersCount>(ParameterActionTypes.ParametersCount),
            mergeMap((action) => this.parameterService.countByFilter(action.payload.filter)
                .pipe(
                    map((count: number) => new ParametersCountSuccess({filter: action.payload.filter, count})),
                    catchError(err => {
                        console.log(err);
                        return of(new ParametersCountError());
                    })
                ))
        );

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

    constructor(private actions$: Actions,
                private store: Store<AppState>,
                private parameterService: ParameterService) {}

}
