import { mergeMap, map, catchError, withLatestFrom } from 'rxjs/operators';
import { TranslateService } from './../../../../internationalization/translate.service';
import { BaseEffects } from './../../../base.effects';
import { Injectable } from '@angular/core';
import { createEffect, Actions, ofType } from '@ngrx/effects';
import { Store, select } from '@ngrx/store';
import { AppState } from 'src/app/reducers';
import { GroupService } from '../../../../service/group.service';
import * as GroupActions from '../actions/groups.actions';
import { of } from 'rxjs';
import { selectGroupsFilter } from '../selectors/groups.selectors';
import { Group } from '../../../../model/group';

@Injectable()
export class GroupEffects extends BaseEffects {

    loadGroupCount$ = createEffect(() => this.actions$.pipe(
        ofType(GroupActions.groupsCount),
        mergeMap((action) => {
            return this.groupService.count(action.filter)
                .pipe(
                    map((count: number) => GroupActions.groupsCountSuccess({ filter: action.filter, count })),
                    catchError(err => {
                        super.addMessageError(err);
                        return of(GroupActions.groupsCountError());
                    })
                );
        })
    ));

    loadGroupPage$ = createEffect(() => this.actions$.pipe(
        ofType(GroupActions.groupsPageRequested),
        withLatestFrom(this.store.pipe(select(selectGroupsFilter))),
        mergeMap(([action, filter]) => {
            return this.groupService.find(filter, action.page.first, action.page.max)
                .pipe(
                    catchError((err) => {
                        super.addMessageError(err);
                        return of(GroupActions.groupsPageRequestedError());
                    })
                );
        }),
        map((groups: Group[]) => GroupActions.groupsPageRequestedSuccess({ groups }))
    ));

    constructor(private actions$: Actions,
                private store: Store<AppState>,
                private groupService: GroupService,
                translateService: TranslateService) {
        super(translateService);
    }
}
