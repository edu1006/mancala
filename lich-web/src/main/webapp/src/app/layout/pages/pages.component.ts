import { Component, OnInit } from '@angular/core';
import * as SideNav from '../../../assets/vendor/js/sidenav.js';

import * as $ from 'jquery';
import swal from 'sweetalert';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})
export class PagesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.loadSideNavMenu();
  }

  private loadSideNavMenu() {
    $('#layout-sidenav').each(function() {
      // tslint:disable-next-line:no-unused-expression
      new SideNav.SideNav(this, {
        orientation: $(this).hasClass('sidenav-horizontal') ? 'horizontal' : 'vertical'
      });
    });
  }

}
