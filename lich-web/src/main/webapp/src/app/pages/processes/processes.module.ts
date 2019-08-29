import { AgentEffects } from './agent/effects/agent.effects';
import { EffectsModule } from '@ngrx/effects';
import { StoreModule } from '@ngrx/store';
import { ComponentsModule } from './../../common/components/components.module';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { ChipsModule } from 'primeng/chips';
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
import { ParameterComponent } from './parameter/parameter.component';
import { MentionModule } from 'angular-mentions/mention';
import { ExecutorComponent } from './executor/executor.component';
import { agentReducer } from './agent/reducers/agent.reducers';

@NgModule({
  imports: [
    CommonModule,
    ProcessesRoutingModule,
    FormsModule,
    TableModule,
    CalendarModule,
    ChipsModule,
    AutoCompleteModule,
    MentionModule,
    PipeModule.forRoot(),
    ComponentsModule.forRoot(),

    // reducers
    StoreModule.forFeature('agents', agentReducer),

    // effects
    EffectsModule.forFeature([AgentEffects])
  ],
  declarations: [
    AgentComponent,
    ProcessComponent,
    QueryComponent,
    DetailComponent,
    NumberDirective,
    IpFormatDirective,
    ParameterComponent,
    ExecutorComponent
  ]
})
export class ProcessesModule { }
