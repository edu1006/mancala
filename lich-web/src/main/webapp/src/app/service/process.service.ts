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

  constructor(private http: HttpClient) {
    this.jobProcessDataUrl = Utils.getUrlBackend() + '/api/job_process/data';
  }

  getJobProcessData(): Observable<JobProcessData> {
    return this.http.get(this.jobProcessDataUrl).pipe(
      tap((res: JobProcessData) => res)
    );
  }
}
