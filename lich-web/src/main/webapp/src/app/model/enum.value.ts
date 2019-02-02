
export class EnumValue<I> {
    id: I;
    description: string;

    constructor(id?: I, description?: string) {
        this.id = id;
        this.description = description;
    }
}
