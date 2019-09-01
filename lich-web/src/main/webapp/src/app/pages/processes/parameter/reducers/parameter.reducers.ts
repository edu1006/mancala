import { ParameterState } from './parameter.reducers';
import { Parameter } from '../../../../model/parameter';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';
import * as ParameterActions from '../actions/parameter.actions';
import { createReducer, on, Action } from '@ngrx/store';

export const adapter: EntityAdapter<Parameter> = createEntityAdapter<Parameter>();

export interface ParameterState extends EntityState<Parameter> {
    filter: Parameter;
    countParameters: number;
}

export const initialParameterState: ParameterState = adapter.getInitialState({
    filter: undefined,
    countParameters: 0,
    entities: []
});

const initParameterReducer = createReducer(
    initialParameterState,
    on(ParameterActions.parametersCountSuccess, (state, {filter, count}) => {
        return {...initialParameterState, filter, countParameters: count};
    }),
    on(ParameterActions.parametersCountError, (state) => {
        return adapter.removeAll({ ...state, countParameters: 0 });
    }),
    on(ParameterActions.parametersPageRequestedSuccess, (state, {parameters}) => {
        return adapter.addMany(parameters, {...state});
    }),
    on(ParameterActions.parametersPageRequestedError, (state) => {
        return adapter.removeAll({ ...state });
    }),
    on(ParameterActions.parametersSaveSuccess, (state, {parameter}) => {
        return adapter.upsertOne(parameter, {...state});
    })
);

export function parameterReducer(state: ParameterState | undefined, action: Action) {
    return initParameterReducer(state, action);
}

/*
export function parameterReducer(state = initialParameterState, action: ParameterActions): ParameterState {
    switch (action.type) {

        case ParameterActionTypes.ParametersCountSuccess:
            return {...initialParameterState, filter: action.payload.filter, countParameters: action.payload.count};

        case ParameterActionTypes.ParametersCountError:
            return adapter.removeAll({...state, countParameters: 0});

        case ParameterActionTypes.ParametersPageRequestedSuccess:
            return adapter.addMany(action.payload.parameters, {...state});

        case ParameterActionTypes.ParametersPageRequestedError:
            return adapter.removeAll({...state});

        default:
            return state;
    }
}
*/
export const {
    selectAll,
    selectEntities,
    selectIds,
    selectTotal
} = adapter.getSelectors();
