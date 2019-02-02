import { StatusEnum } from '../enums/status.enum';
import { TypeExecutionEnum } from '../enums/type.execution.enum';
import { PeriodicityEnum } from '../enums/periodicity.enum';
import { YesNoEnum } from '../enums/yes.no.enum';
import { StepProcess } from './step.process';

export class JobProcess {

    id: number;
    idProcess: string;
    description: string;
    status: StatusEnum;
    typeExecution: TypeExecutionEnum;
    periodicity: PeriodicityEnum;
    weekDay: number;
    monthDay: number;
    xValue: number;
    innerJob: YesNoEnum;
    innerParallel: YesNoEnum;
    tags: string;
    stepsProcesses: Array<StepProcess>;

    constructor() {
        this.id = undefined;
        this.idProcess = null;
        this.description = null;
        this.status = StatusEnum.ENABLED;
        this.typeExecution = null;
        this.periodicity = null;
        this.weekDay = undefined;
        this.monthDay = undefined;
        this.xValue = undefined;
        this.innerJob = YesNoEnum.NO;
        this.innerParallel = YesNoEnum.NO;
        this.tags = null;
        this.stepsProcesses = new Array();
    }

}
