import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Utils } from '../util/utils';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private homeMessageUrl: string;

  constructor(private http: HttpClient) {
    this.homeMessageUrl = Utils.getUrlBackend() + '/home';
  }

  public message() {
    return this.http.get(this.homeMessageUrl);
  }
}
