import { JobExecutionVo } from './../model/job.execution';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Utils } from '../util/utils';
import { JobExecsResultVo } from '../model/job.execs.result';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExecutorService {

  private executorUrl: string;
  private findLastExecutionsUrl: string;

  constructor(private http: HttpClient) {
    this.executorUrl = Utils.getUrlBackend() + '/api/job_execution';
    this.findLastExecutionsUrl = Utils.getUrlBackend() + '/api/job_execution/last_executions';
  }

  execute(jobExecutionVo: JobExecutionVo): Observable<any> {
    return this.http.put(this.executorUrl, jobExecutionVo);
  }

  findLasExecutions() {
    return this.http.get(this.findLastExecutionsUrl).pipe(
      tap((res: JobExecsResultVo) => res)
    );
  }
}
