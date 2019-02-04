import { EnumValue } from './enum.value';

export class JobProcessData {

    periodicities: Array<EnumValue<number>>;
    weekDays: Array<EnumValue<number>>;
    monthDays: Array<number>;

}
