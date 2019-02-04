
export class FormatadorUtil {

    static onlyNumber(value: any) {
        if (value) {
            value = value.replace(/\D/g, '');
        }
        return value;
    }

}
