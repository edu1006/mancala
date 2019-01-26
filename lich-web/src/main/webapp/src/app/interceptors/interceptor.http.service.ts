import { Utils } from './../util/utils';
import { CookieService } from 'ngx-cookie-service';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';
import { catchError, switchMap, share, tap, finalize } from 'rxjs/operators';
import { LoginService } from '../service/login.service';
import { TokenUser } from '../model/token.user';
import { NgBlockUI, BlockUI } from 'ng-block-ui';

@Injectable()
export class InterceptorHttpService implements HttpInterceptor {

    @BlockUI() blockUI: NgBlockUI;

    constructor(private router: Router,
                private loginService: LoginService,
                private cookieService: CookieService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        let obs: Observable<any> = null;

        this.blockUI.start();

        if (localStorage.getItem('access_token')) {
            obs = next.handle(request.clone({
                setHeaders: {
                    Authorization: 'Bearer ' + localStorage.getItem('access_token'),
                    'Accept-Language': this.getLanguage(),
                    'Content-Type': 'application/json'
                }
            }));
        } else {
            obs = next.handle(request.clone({
                setHeaders: {
                    'Accept-Language': this.getLanguage(),
                    'Content-Type': 'application/json'
                }
            }));
        }

        return obs.pipe(
            tap(
                (res) => res
            ),
            catchError((error: Error) => {
                if (error instanceof HttpErrorResponse) {
                    switch (error.status) {
                        case 200: {
                                const res = new HttpResponse({
                                    body: null,
                                    headers: error.headers,
                                    status: error.status,
                                    statusText: error.statusText,
                                    url: error.url
                                });
                                return of(res);
                            }
                        case 401: {
                                return this.getAccessToken(request, next);
                            }
                        default: {
                                throw error;
                            }
                    }
                }
            }),
            finalize(() => this.blockUI.stop())
        );
    }

    private getAccessToken(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
        return this.loginService.novoAccessToken().pipe(
            switchMap(
                res => {

                    const tokenUser = res as TokenUser;

                    localStorage.removeItem('access_token');
                    localStorage.removeItem('refresh_token');

                    localStorage.setItem('access_token', tokenUser.access_token);
                    localStorage.setItem('refresh_token', tokenUser.refresh_token);

                    return next.handle(req.clone({
                        setHeaders: {
                            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
                            'Accept-Language': this.getLanguage(),
                            'Content-Type': 'application/json'
                        }
                    }));
                }
            ),
            share()
        );
    }

    private getLanguage(): string {
        const lang = this.cookieService.get('app-language');
        return (lang && lang.length > 0) ? lang : Utils.getDefaultLanguage();
    }

}
