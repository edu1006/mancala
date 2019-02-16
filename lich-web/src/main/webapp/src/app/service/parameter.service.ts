import { Observable } from 'rxjs';
import { Parameter } from './../model/parameter';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utils } from '../util/utils';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ParameterService {

  private countUrl: string;
  private findUrl: string;
  private saveUrl: string;

  constructor(private http: HttpClient) {
    this.countUrl = Utils.getUrlBackend() + '/api/parameter/count';
    this.findUrl = Utils.getUrlBackend() + '/api/parameter/find/';
    this.saveUrl = Utils.getUrlBackend() + '/api/parameter';
  }

  countByFilter(filter: Parameter): Observable<number> {
    return this.http.post(this.countUrl, filter).pipe(
      tap((res: number) => res)
    );
  }

  findByFilter(filter: Parameter, page: number, max: number): Observable<Array<Parameter>> {
    return this.http.post(this.findUrl + page + '/' + max, filter).pipe(
      tap((res: Array<Parameter>) => res)
    );
  }

  save(parameter: Parameter): Observable<Parameter> {
    return this.http.put(this.saveUrl, parameter).pipe(
      tap((res: Parameter) => res)
    );
  }
}
