import { LoginRoutingModule } from './login-routing.module';
import { PipeModule } from './../../pipe/pipe.module';
import { LoginComponent } from './component/login.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
      CommonModule,
      FormsModule,
      LoginRoutingModule,
      PipeModule.forRoot()
    ],
    declarations: [
        LoginComponent
    ]
  })
export class LoginModule { }
