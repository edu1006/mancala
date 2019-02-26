import { EnumValue } from './enum.value';
import { Parameter } from './parameter';

export class JobProcessData {

    periodicities: Array<EnumValue<number>>;
    weekDays: Array<EnumValue<number>>;
    monthDays: Array<number>;
    parameters: Array<Parameter>;

}
