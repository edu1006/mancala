import { CookieService } from 'ngx-cookie-service';
import { TranslateService } from './../../../internationalization/translate.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private translateService: TranslateService,
              private cookieService: CookieService) { }

  ngOnInit() {
  }

  setLanguage(lang: string) {
    this.translateService.use(lang);
    this.cookieService.set('app-language', lang);
  }

}
