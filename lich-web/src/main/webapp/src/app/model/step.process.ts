import { TypeStepProcessEnum } from './../enums/type.step.process';
import { YesNoEnum } from '../enums/yes.no.enum';
import { StatusEnum } from '../enums/status.enum';
export class StepProcess {

    id: number;
    idStep: string;
    order: number;
    type: TypeStepProcessEnum;
    scriptBatch: string;
    commandBatch: string;
    scriptBash: string;
    commandBash: string;
    jarPath: string;
    restUrl: string;
    restLinkAttribute: string;
    restStatusAttribute: string;
    restLogAttribute: string;
    restStatusValueOk: string;
    restStatusValueError: string;
    logPath: string;
    logName: string;
    continueIfError: YesNoEnum;
    askToContinue: YesNoEnum;
    askToContinueMessage: string;
    timeSleep: number;
    pathOrigin: string;
    pathDestiny: string;
    idJobProcess: number;
    idAgent: number;
    idInnerJobProcess: number;
    status: StatusEnum;

    constructor() {
        this.id = undefined;
        this.idStep = null;
        this.order = undefined;
        this.type = null;
        this.scriptBatch = null;
        this.commandBatch = null;
        this.scriptBash = null;
        this.commandBash = null;
        this.jarPath = null;
        this.restUrl = null;
        this.restLinkAttribute = null;
        this.restStatusAttribute = null;
        this.restLogAttribute = null;
        this.restStatusValueOk = null;
        this.restStatusValueError = null;
        this.logPath = null;
        this.logName = null;
        this.continueIfError = YesNoEnum.NO;
        this.askToContinue = YesNoEnum.NO;
        this.askToContinueMessage = null;
        this.timeSleep = undefined;
        this.pathOrigin = undefined;
        this.pathDestiny = undefined;
        this.idJobProcess = undefined;
        this.idAgent = undefined;
        this.idInnerJobProcess = undefined;
        this.status = StatusEnum.ENABLED;
    }

}
