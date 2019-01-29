import { ProcessComponent } from './process/process.component';
import { AgentComponent } from './agent/agent.component';
import { AuthGuard } from './../../guard/auth.guard';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
    { path: 'agent', component: AgentComponent, canActivate: [AuthGuard]},
    { path: 'process', component: ProcessComponent, canActivate: [AuthGuard]},
  ];

  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
export class ProcessesRoutingModule { }
