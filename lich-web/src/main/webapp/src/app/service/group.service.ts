import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Utils } from '../util/utils';
import { Group } from '../model/group';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private saveUrl: string;
  private countUrl: string;
  private findUrl: string;
  private findEnabledUrl: string;

  constructor(private http: HttpClient) {
    this.saveUrl = Utils.getUrlBackend() + '/api/group';
    this.countUrl = Utils.getUrlBackend() + '/api/group/count';
    this.findUrl = Utils.getUrlBackend() + '/api/group/find/';
    this.findEnabledUrl = Utils.getUrlBackend() + '/api/group/find/enabled';
  }

  save(group: Group) {
    return this.http.put(this.saveUrl, group);
  }

  count(filter: Group) {
    return this.http.post(this.countUrl, filter);
  }

  find(filter: Group, page: number, max: number): Observable<Array<Group>> {
    return this.http.post(this.findUrl + page + '/' + max, filter).pipe(
      tap((res: Array<Group>) => res)
    );
  }

  findEnabled(): Observable<Array<Group>> {
    return this.http.get(this.findEnabledUrl).pipe(
      tap((res: Array<Group>) => res)
    );
  }
}
