import { YesNoEnum } from '../enums/yes.no.enum';
import { StatusEnum } from '../enums/status.enum';

export class Parameter {

    id: number;
    name: string;
    value: string;
    password: YesNoEnum;
    status: StatusEnum;

    constructor() {
        this.id = undefined;
        this.name = null;
        this.value = null;
        this.password = YesNoEnum.NO;
        this.status = StatusEnum.ENABLED;
    }

}
