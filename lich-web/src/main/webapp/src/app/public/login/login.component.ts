import { UserService } from './../../service/user.service';
import { GlobalData } from './../../util/global.data';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from 'src/app/model/UserLogin';
import { LoginService } from 'src/app/service/login.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: UserLogin;

  constructor(private router: Router,
              private loginService: LoginService,
              private userService: UserService,
              private globalData: GlobalData) {
    this.userLogin = new UserLogin();
  }

  ngOnInit() {
  }

  login() {
    this.loginService.login(this.userLogin).subscribe(
      res => {
        this.loadUserData(res);
      },
      error => {
        swal({
          text: 'Erro ao executar o login',
          icon: 'error',
          buttons: ['Fechar', false]
        });
      }
    );
  }

  private loadUserData(token: any) {
    localStorage.setItem('access_token', token.access_token);
    localStorage.setItem('refresh_token', token.refresh_token);

    this.userService.getLoggedUser().subscribe(
      res => {
        this.globalData.setUser(res);
        this.router.navigate(['/home']);
      },
      error => {
        swal({
          text: 'Erro ao executar o login',
          icon: 'error',
          buttons: ['Fechar', false]
        });
      }
    );
  }

}
