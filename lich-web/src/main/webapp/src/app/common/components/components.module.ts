import { FormsModule } from '@angular/forms';
import { PipeModule } from './../../pipe/pipe.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableRowsSelectComponent } from './table-rows-select/table-rows-select.component';

@NgModule({
  declarations: [
    TableRowsSelectComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    PipeModule.forRoot()
  ],
  exports: [
    TableRowsSelectComponent
  ]
})
export class ComponentsModule {
  static forRoot() {
    return {
        ngModule: ComponentsModule,
        providers: []
    };
}
}
