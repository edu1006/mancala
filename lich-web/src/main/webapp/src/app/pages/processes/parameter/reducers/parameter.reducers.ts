import { ParameterActions, ParameterActionTypes } from '../actions/parameter.actions';
import { Parameter } from '../../../../model/parameter';
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';

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

export const {
    selectAll,
    selectEntities,
    selectIds,
    selectTotal
} = adapter.getSelectors();
