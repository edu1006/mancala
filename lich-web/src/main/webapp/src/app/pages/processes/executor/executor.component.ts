import { ExecutorService } from './../../../service/executor.service';
import { JobExecutionVo } from './../../../model/job.execution';
import { JobProcess } from './../../../model/job.process';
import { ProcessService } from './../../../service/process.service';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { Component, OnInit, OnDestroy } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { JobExecResultVo } from 'src/app/model/job.exec.result';

@Component({
  selector: 'app-executor',
  templateUrl: './executor.component.html',
  styleUrls: ['./executor.component.css']
})
export class ExecutorComponent extends BaseComponent implements OnInit, OnDestroy {

  jobsProcess: Array<JobProcess>;
  filteredJobs: Array<JobProcess>;

  jobSelected: JobProcess;

  results: Array<JobExecResultVo>;

  private serverUrl = 'http://localhost:8080/ws_lich_socket';
  private stompClient;

  constructor(translateService: TranslateService,
              private processService: ProcessService,
              private executorService: ExecutorService) {
    super(translateService);
    this.initializeWebSocket();
  }

  ngOnInit() {
    this.processService.findEnabled().subscribe(
      res => {
        this.jobsProcess = res;
      }
    );
  }

  ngOnDestroy() {
    this.stompClient.disconnect();
  }

  initializeWebSocket() {
    const ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = null;

    const self = this;
    this.stompClient.connect({}, function(frame) {
      self.stompClient.subscribe('/ws_lich_topic/process_executed', (message) => {
        if (message.body) {
          const results: Array<JobExecResultVo> = JSON.parse(message.body);
          self.updateResult(results);
        }
      });
    });
  }

  updateResult(results: Array<JobExecResultVo>) {
    this.results = results;
  }

  filterJobs(event: any) {
    this.filteredJobs = new Array();
    for (let i = 0; i < this.jobsProcess.length; i++) {
      const job = this.jobsProcess[i];
      if (job.idProcess.toLowerCase().indexOf(event.query.toLowerCase()) === 0) {
        this.filteredJobs.push(job);
      }
    }
  }

  execute() {
    const jobExecutionVo = new JobExecutionVo();
    jobExecutionVo.idJobProcess = this.jobSelected.id;

    this.executorService.execute(jobExecutionVo).subscribe(
      res => this.addMessageSuccess('Processo iniciado com sucesso'),
      error => this.addMessageError('Erro para iniciar processo', error)
    );
  }

}
