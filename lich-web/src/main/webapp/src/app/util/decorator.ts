
export interface Converter {
    [key: string]: (inputValue: any) => any;
}

export function DateType(f: Converter): any {
    console.log(f);
}

export function DateFields(value: Array<string>): any {

}

export class Decorators {

}
