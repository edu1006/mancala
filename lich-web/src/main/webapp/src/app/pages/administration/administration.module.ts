import { TableModule } from 'primeng/table';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdministrationRoutingModule } from './administration-routing.module';
import { GroupsComponent } from './groups/groups.component';
import { UserComponent } from './user/user.component';
import { FormsModule } from '@angular/forms';
import { PipeModule } from '../../pipe/pipe.module';

@NgModule({
  imports: [
    CommonModule,
    AdministrationRoutingModule,
    FormsModule,
    TableModule,
    PipeModule.forRoot()
  ],
  declarations: [
    GroupsComponent,
    UserComponent
  ]
})
export class AdministrationModule { }
