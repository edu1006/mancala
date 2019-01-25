import { Group } from './group';

export class User {

    id: number;
    name: string;
    login: string;
    document: string;
    registration: string;
    gender: Gender;
    phone: number;
    phoneCel: number;
    phoneCommercial: number;
    status: number;
    dateInsert: Date;
    groups: Array<Group>;

    constructor() {
        this.id = undefined;
        this.name = '';
        this.login = '';
        this.document = '';
        this.registration = '';
        this.gender = null;
        this.phone = undefined;
        this.phoneCel = undefined;
        this.phoneCommercial = undefined;
        this.status = 1;
        this.dateInsert = undefined;
        this.groups = null;
    }

}
