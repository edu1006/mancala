import { LoginRoutingModule } from './login-routing.module';
import { PipeModule } from './../../pipe/pipe.module';
import { LoginComponent } from './component/login.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import * as fromLogin from './reducers/login.reducer';

@NgModule({
    imports: [
      CommonModule,
      FormsModule,
      LoginRoutingModule,
      PipeModule.forRoot(),
      StoreModule.forFeature('login', fromLogin.reducer)
    ],
    declarations: [
        LoginComponent
    ]
  })
export class LoginModule { }
