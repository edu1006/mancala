import { StatusEnum } from '../enums/status.enum';
import { TypeExecutionEnum } from '../enums/type.execution.enum';
import { PeriodicityEnum } from '../enums/periodicity.enum';
import { YesNoEnum } from '../enums/yes.no.enum';
import { StepProcess } from './step.process';

export class JobProcess {

    id: number;
    version: number;
    idProcess: string;
    description: string;
    status: StatusEnum;
    typeExecution: TypeExecutionEnum;
    periodicity: PeriodicityEnum;
    periodicityStartDate: Date;
    periodicityEndDate: Date;
    timeExecution: string; // colocar no backend
    weekDay: number;
    monthDay: number;
    xValue: number;
    innerJob: YesNoEnum;
    innerParallel: YesNoEnum;
    tags: string;
    stepsProcesses: Array<StepProcess>;

    constructor() {
        this.id = undefined;
        this.version = undefined;
        this.idProcess = null;
        this.description = null;
        this.status = StatusEnum.ENABLED;
        this.typeExecution = null;
        this.periodicity = null;
        this.periodicityStartDate = null;
        this.periodicityEndDate = null;
        this.timeExecution = '00:00';
        this.weekDay = null;
        this.monthDay = null;
        this.xValue = undefined;
        this.innerJob = YesNoEnum.NO;
        this.innerParallel = YesNoEnum.NO;
        this.tags = null;
        this.stepsProcesses = new Array<StepProcess>();
    }

}
