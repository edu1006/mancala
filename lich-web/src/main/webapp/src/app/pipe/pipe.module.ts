import { NgModule } from '@angular/core';
import { TranslatePipe } from './translate/translate.pipe';

@NgModule({
    imports: [],
    declarations: [
        TranslatePipe
    ],
    exports: [
        TranslatePipe
    ]
})
export class PipeModule {

    static forRoot() {
        return {
            ngModule: PipeModule,
            providers: []
        };
    }
}
