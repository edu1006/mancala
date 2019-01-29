import { Input } from '@angular/core';
import { OperationEnum } from './../enums/operation.enum';
import { TranslateService } from './../internationalization/translate.service';
import { BaseComponent } from './base.component';

export class BaseOperationComponent extends BaseComponent {

    @Input()
    operation: OperationEnum;
    operationEnum = OperationEnum;

    constructor(translateService: TranslateService) {
        super(translateService);
    }

}
