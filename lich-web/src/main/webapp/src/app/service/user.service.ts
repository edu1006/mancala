import { Utils } from './../util/utils';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private loggedUserUrl: string;
  private saveUrl: string;
  private countUrl: string;
  private findUrl: string;

  constructor(private http: HttpClient) {
    this.loggedUserUrl = Utils.getUrlBackend() + '/api/user/logged';
    this.saveUrl = Utils.getUrlBackend() + '/api/user';
    this.countUrl = Utils.getUrlBackend() + '/api/user/count';
    this.findUrl = Utils.getUrlBackend() + '/api/user/find';
  }

  getLoggedUser() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.get(this.loggedUserUrl, {headers: headers}).pipe(
      tap((res: User) => res)
    );
  }

  save(user: User): Observable<User> {
    return this.http.put(this.saveUrl, user).pipe(
      tap((res: User) => res)
    );
  }

  count(filter: User): Observable<number> {
    return this.http.post(this.countUrl, filter).pipe(
      tap((res: number) => res)
    );
  }

  find(filter: User, page: number, max: number) {
    return this.http.post(this.findUrl + '/' + page + '/' + max, filter).pipe(
      tap((res: Array<User>) => res)
    );
  }
}
