import { JobProcess } from './../model/job.process';
import { tap } from 'rxjs/operators';
import { Utils } from './../util/utils';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobProcessData } from '../model/job.process.data';

@Injectable({
  providedIn: 'root'
})
export class ProcessService {

  private jobProcessDataUrl: string;
  private saveUrl: string;

  constructor(private http: HttpClient) {
    this.jobProcessDataUrl = Utils.getUrlBackend() + '/api/job_process/data';
    this.saveUrl = Utils.getUrlBackend() + '/api/job_process';
  }

  getJobProcessData(): Observable<JobProcessData> {
    return this.http.get(this.jobProcessDataUrl).pipe(
      tap((res: JobProcessData) => res)
    );
  }

  save(jobProcess: JobProcess): Observable<JobProcess> {
    return this.http.put(this.saveUrl, jobProcess).pipe(
      tap((res: JobProcess) => res)
    );
  }
}
