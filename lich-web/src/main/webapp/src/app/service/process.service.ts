import { JobProcessStatusVo } from './../model/job.process.status';
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
  private countUrl: string;
  private findUrl: string;
  private loadByIdUrl: string;
  private updateStatusUrl: string;
  private findInnerJobsUrl: string;
  private findEnabledUrl: string;

  constructor(private http: HttpClient) {
    this.jobProcessDataUrl = Utils.getUrlBackend() + '/api/job_process/data';
    this.saveUrl = Utils.getUrlBackend() + '/api/job_process';
    this.countUrl = Utils.getUrlBackend() + '/api/job_process/count';
    this.findUrl = Utils.getUrlBackend() + '/api/job_process/find/';
    this.loadByIdUrl = Utils.getUrlBackend() + '/api/job_process/';
    this.updateStatusUrl = Utils.getUrlBackend() + '/api/job_process/update-status';
    this.findInnerJobsUrl = Utils.getUrlBackend() + '/api/job_process/find-inner-jobs';
    this.findEnabledUrl = Utils.getUrlBackend() + '/api/job_process/find-enabled';
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

  count(filter: JobProcess): Observable<number> {
    return this.http.post(this.countUrl, filter).pipe(
      tap((res: number) => res)
    );
  }

  find(filter: JobProcess, page: number, max: number): Observable<Array<JobProcess>> {
    return this.http.post(this.findUrl + page + '/' + max, filter).pipe(
      tap((res: Array<JobProcess>) => res)
    );
  }

  loadById(id: number): Observable<JobProcess> {
    return this.http.get(this.loadByIdUrl + id).pipe(
      tap((res: JobProcess) => {
        // load dates
        if (res.periodicityStartDate) {
          res.periodicityStartDate = new Date(res.periodicityStartDate);
        }

        if (res.periodicityEndDate) {
          res.periodicityEndDate = new Date(res.periodicityEndDate);
        }
        return res;
      })
    );
  }

  updateStatus(jobProcessStatusVo: JobProcessStatusVo) {
    return this.http.post(this.updateStatusUrl, jobProcessStatusVo);
  }

  findInnerJobs(): Observable<Array<JobProcess>> {
    return this.http.get(this.findInnerJobsUrl).pipe(
      tap((res: Array<JobProcess>) => res)
    );
  }

  findEnabled(): Observable<Array<JobProcess>> {
    return this.http.get(this.findEnabledUrl).pipe(
      tap((res: Array<JobProcess>) => res)
    );
  }
}
