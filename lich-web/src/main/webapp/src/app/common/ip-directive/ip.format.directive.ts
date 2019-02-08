import { ElementRef, HostListener, Directive } from '@angular/core';

@Directive({
    selector: 'input[appIpFormat]'
})
export class IpFormatDirective {

    constructor(private _el: ElementRef) {}

    @HostListener('input', ['$event'])
    onInputChange(event) {
        const initialValue = this._el.nativeElement.value;
        this._el.nativeElement.value = initialValue.replace(/[^0-9.]*/g, '');

        if (initialValue !== this._el.nativeElement.value) {
            event.stopPropagation();
        }
    }

}
