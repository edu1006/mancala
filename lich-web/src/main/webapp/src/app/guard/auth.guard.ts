import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { LoginService } from '../service/login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private jwtHelper: JwtHelperService,
              private loginService: LoginService,
              private router: Router) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    if (!this.isAuthenticated() && !this.isRefreshTokenAtivo()) {
      this.router.navigate(['login']);
      return false;
    }

    return true;
  }

  private isRefreshTokenAtivo(): boolean {
    const refreshToken = localStorage.getItem('refresh_token');
    return !this.jwtHelper.isTokenExpired(refreshToken);
  }

  private isAuthenticated(): boolean {
    const token = localStorage.getItem('access_token');
    return !this.jwtHelper.isTokenExpired(token);
  }

  /*canActivate(): boolean {
    return true;
  }*/
}
