import { Routes } from '@angular/router';
import { HomeComponent } from '../../pages/home/home.component';
import { AuthGuard } from 'src/app/guard/auth.guard';

export const SECURE_ROUTES: Routes = [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    {
        path: 'administration',
        loadChildren: './pages/administration/administration.module#AdministrationModule',
        canActivate: [AuthGuard]
    },
    {
        path: 'processes',
        loadChildren: './pages/processes/processes.module#ProcessesModule',
        canActivate: [AuthGuard]
    }
];
