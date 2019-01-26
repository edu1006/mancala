
export class SelectModel<T> {

    model: T;
    selected: boolean;

    constructor(model: T, select?: boolean) {
        this.model = model;
        this.selected = (select) ? select : false;
    }

}
