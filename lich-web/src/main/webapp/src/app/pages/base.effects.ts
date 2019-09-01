import swal from 'sweetalert';
import { TranslateService } from '../internationalization/translate.service';

export class BaseEffects {

    translateService: TranslateService;

    constructor(translateService: TranslateService) {
        this.translateService = translateService;
    }

    addMessageError(error?: any) {

        let message: string;

        if (error && error.hasOwnProperty('error')) {
            const innerError = error.error;
            if (innerError && innerError.hasOwnProperty('message')) {
                message = innerError.message;
            }
        }

        if (!message) {
            message = this.translateService.data['general.error'];
        }

        swal({
            text: message,
            icon: 'error',
            buttons: [this.getCloseButton(), false]
        });
    }

    private getCloseButton(): string {
        return this.translateService.data['general.button.close'];
    }
}
