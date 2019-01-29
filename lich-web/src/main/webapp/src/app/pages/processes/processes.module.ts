import { PipeModule } from './../../pipe/pipe.module';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ProcessesRoutingModule } from './processes-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgentComponent } from './agent/agent.component';
import { ProcessComponent } from './process/process.component';

@NgModule({
  imports: [
    CommonModule,
    ProcessesRoutingModule,
    FormsModule,
    TableModule,
    PipeModule.forRoot()
  ],
  declarations: [
    AgentComponent,
    ProcessComponent
  ]
})
export class ProcessesModule { }
