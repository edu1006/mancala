import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { TranslateService } from '../internationalization/translate.service';
import swal from 'sweetalert';

declare var $: any;

export class BaseComponent {

    @BlockUI() blockUI: NgBlockUI;
    private translateService: TranslateService;

    constructor(translateService: TranslateService) {
        this.translateService = translateService;
    }

    openLoad() {
        this.blockUI.start();
    }

    closeLoad() {
        this.blockUI.stop();
    }

    openModal(id: string) {
        $('#' + id).modal('show');
    }

    closeModal(id: string) {
        $('#' + id).modal('hide');
    }

    addMessageError(message: string, error?: any) {

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

    addMessageSuccess(message: string) {
        swal({
            text: message,
            icon: 'success',
            buttons: [this.getCloseButton(), false]
        });
    }

    private getCloseButton(): string {
        return this.translateService.data['general.button.close'];
    }

    getMessage(key: string): string {
        return this.translateService.data[key];
    }

}
