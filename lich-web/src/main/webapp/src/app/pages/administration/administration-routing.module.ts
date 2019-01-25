import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GroupsComponent } from './groups/groups.component';
import { AuthGuard } from '../../guard/auth.guard';
import { UserComponent } from './user/user.component';

const routes: Routes = [
  { path: 'user', component: UserComponent, canActivate: [AuthGuard]},
  { path: 'group', component: GroupsComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdministrationRoutingModule { }
