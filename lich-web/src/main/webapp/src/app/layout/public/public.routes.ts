import { LoginComponent } from './../../public/login/component/login.component';
import { Routes } from '@angular/router';

export const PUBLIC_ROUTES: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    {
        path: 'login',
        loadChildren: './public/login/login.module#LoginModule'
    }
];
