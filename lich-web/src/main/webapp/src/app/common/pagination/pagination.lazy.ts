
export class PaginationLazy<F, T> {

    filter: F;
    totalRecords: number;
    records: Array<T>;

    countFunction: Function;
    findFunction: Function;

    constructor(filter: F) {
        this.filter = filter;
    }

}
