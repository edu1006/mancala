import { StepAgentVo } from './../model/step.agent';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Agent } from './../model/agent';
import { Utils } from './../util/utils';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AgentTypeEnum } from '../enums/agent.type.enum';

@Injectable({
  providedIn: 'root'
})
export class AgentService {

  private saveUrl: string;
  private countUrl: string;
  private findUrl: string;
  private findEnableByTypeUrl: string;
  private findForStepUrl: string;

  constructor(private http: HttpClient) {
    this.saveUrl = Utils.getUrlBackend() + '/api/agent';
    this.countUrl = Utils.getUrlBackend() + '/api/agent/count';
    this.findUrl = Utils.getUrlBackend() + '/api/agent/find/';
    this.findEnableByTypeUrl = Utils.getUrlBackend() + '/api/agent/find/enable/';
    this.findForStepUrl = Utils.getUrlBackend() + '/api/agent/find-for-step/';
  }

  save(agent: Agent): Observable<Agent> {
    return this.http.post(this.saveUrl, agent).pipe(
      tap((res: Agent) => res)
    );
  }

  count(filter: Agent): Observable<number> {
    return this.http.post(this.countUrl, filter).pipe(
      tap((res: number) => res)
    );
  }

  find(filter: Agent, page: number, max: number): Observable<Array<Agent>> {
    return this.http.post(this.findUrl + page + '/' + max, filter).pipe(
      tap((res: Array<Agent>) => res)
    );
  }

  findEnableByType(type: AgentTypeEnum): Observable<Array<Agent>> {
    return this.http.get(this.findEnableByTypeUrl + type).pipe(
      tap((res: Array<Agent>) => res)
    );
  }

  findForStep(id: number): Observable<StepAgentVo> {
    return this.http.get(this.findForStepUrl + id).pipe(
      tap((res: StepAgentVo) => res)
    );
  }
}
