import { JobExecutionVo } from './../model/job.execution';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utils } from '../util/utils';

@Injectable({
  providedIn: 'root'
})
export class ExecutorService {

  private executorUrl: string;

  constructor(private http: HttpClient) {
    this.executorUrl = Utils.getUrlBackend() + '/api/job_execution';
  }

  execute(jobExecutionVo: JobExecutionVo): Observable<any> {
    return this.http.put(this.executorUrl, jobExecutionVo);
  }
}
