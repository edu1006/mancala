import { createAction, props } from '@ngrx/store';
import { Group } from '../../../../model/group';
import { PageQuery } from '../../../../common/pagination/page.query';

export const groupsCount = createAction(
    '[Group] Groups Count',
    props<{filter: Group}>()
);

export const groupsCountSuccess = createAction(
    '[Group] Groups Count Success',
    props<{filter: Group, count: number}>()
);

export const groupsCountError = createAction(
    '[Group] Groups Count Error'
);

export const groupsPageRequested = createAction(
    '[Group] Groups Page Requested',
    props<{page: PageQuery}>()
);

export const groupsPageRequestedSuccess = createAction(
    '[Group] Groups Page Requested Success',
    props<{groups: Group[]}>()
);

export const groupsPageRequestedError = createAction(
    '[Group] Groups Page Requested Error'
);

export const groupsSaveSuccess = createAction(
    '[Group] Groups Save Success',
    props<{group: Group}>()
);
