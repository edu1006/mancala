import { IpFormatDirective } from './../../common/ip-directive/ip.format.directive';
import { PipeModule } from './../../pipe/pipe.module';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ProcessesRoutingModule } from './processes-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgentComponent } from './agent/agent.component';
import { ProcessComponent } from './process/process.component';
import { QueryComponent } from './process/query/query.component';
import { DetailComponent } from './process/detail/detail.component';
import { CalendarModule } from 'primeng/calendar';
import { NumberDirective } from '../../common/number-directive/number.only.directive';

@NgModule({
  imports: [
    CommonModule,
    ProcessesRoutingModule,
    FormsModule,
    TableModule,
    CalendarModule,
    PipeModule.forRoot()
  ],
  declarations: [
    AgentComponent,
    ProcessComponent,
    QueryComponent,
    DetailComponent,
    NumberDirective,
    IpFormatDirective
  ]
})
export class ProcessesModule { }
