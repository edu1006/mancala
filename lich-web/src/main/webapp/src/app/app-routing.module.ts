import { NgModule } from '@angular/core';
import { PagesComponent, SECURE_ROUTES } from './layout/pages';
import { PublicComponent, PUBLIC_ROUTES } from './layout/public';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guard/auth.guard';


const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: '', component: PublicComponent, data: { title: 'Public Views' }, children: PUBLIC_ROUTES },
    { path: '', component: PagesComponent, canActivate: [AuthGuard], data: { title: 'Secure Views' }, children: SECURE_ROUTES },
    { path: '**', redirectTo: 'login' }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
