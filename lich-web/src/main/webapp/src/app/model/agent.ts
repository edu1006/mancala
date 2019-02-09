import { AgentTypeEnum } from './../enums/agent.type.enum';
import { StatusEnum } from './../enums/status.enum';

export class Agent {

    id: number;
    version: number;
    name: string;
    address: string;
    port: number;
    status: StatusEnum;
    type: AgentTypeEnum;

    constructor() {
        this.id = undefined;
        this.version = undefined;
        this.name = '';
        this.address = '';
        this.port = undefined;
        this.status = null;
        this.type = null;
    }

}
