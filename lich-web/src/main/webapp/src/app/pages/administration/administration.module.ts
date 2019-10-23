import { ComponentsModule } from './../../common/components/components.module';
import { TableModule } from 'primeng/table';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministrationRoutingModule } from './administration-routing.module';
import { GroupsComponent } from './groups/groups.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { PipeModule } from '../../pipe/pipe.module';
import { GroupFunctionalityComponent } from './groups/group-functionality/group-functionality.component';
import { StoreModule } from '@ngrx/store';
import { groupReducer } from './groups/reducers/groups.reducers';
import { EffectsModule } from '@ngrx/effects';
import { GroupEffects } from './groups/effects/groups.effects';

@NgModule({
  imports: [
    CommonModule,
    AdministrationRoutingModule,
    FormsModule,
    TableModule,
    PipeModule.forRoot(),
    ComponentsModule.forRoot(),

    // reducers
    StoreModule.forFeature('groups', groupReducer),

    // effects
    EffectsModule.forFeature([GroupEffects]),
  ],
  declarations: [
    GroupsComponent,
    GroupFunctionalityComponent,
    UserComponent
  ]
})
export class AdministrationModule { }
