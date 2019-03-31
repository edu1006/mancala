import { JobProcess } from './../../../model/job.process';
import { ProcessService } from './../../../service/process.service';
import { TranslateService } from './../../../internationalization/translate.service';
import { BaseComponent } from './../../base.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-executor',
  templateUrl: './executor.component.html',
  styleUrls: ['./executor.component.css']
})
export class ExecutorComponent extends BaseComponent implements OnInit {

  jobsProcess: Array<JobProcess>;
  filteredJobs: Array<JobProcess>;

  jobSelected: JobProcess;

  constructor(translateService: TranslateService,
              private processService: ProcessService) {
    super(translateService);
  }

  ngOnInit() {
    this.processService.findEnabled().subscribe(
      res => {
        this.jobsProcess = res;
      }
    );
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

}
