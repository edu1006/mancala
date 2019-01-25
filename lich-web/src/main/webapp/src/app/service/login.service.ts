import { Injectable } from '@angular/core';
import { Utils } from '../util/utils';
import { UserLogin } from '../model/UserLogin';
import { Http, Headers } from '@angular/http';
import { map, tap, share } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { TokenUser } from '../model/token.user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl: string;

  constructor(private http: Http) {
    this.loginUrl = Utils.getUrlBackend() + '/oauth/token';
  }

  public login(userLogin: UserLogin) {
    const password = encodeURIComponent(userLogin.password);

    const headers = new Headers({
      'Authorization': 'Basic ' + btoa('front-web:1234')
    });

    const formData: FormData = new FormData();
    formData.append('grant_type', 'password');
    formData.append('username', userLogin.login);
    formData.append('password', password);

    return this.http.post(this.loginUrl, formData, {headers: headers}).pipe(map(res => res.json()));
  }

  public novoAccessToken(): Observable<TokenUser> {
    const refreshToken = localStorage.getItem('refresh_token');

    const headers = new Headers({
      'Authorization': 'Basic ' + btoa('front-web:1234')
    });

    const formData: FormData = new FormData();
    formData.append('grant_type', 'refresh_token');
    formData.append('refresh_token', refreshToken);

    return this.http.post(this.loginUrl, formData, {headers: headers}).pipe(
      map(
        res => {
          return res.json();
        }
      )
    );
  }
}
