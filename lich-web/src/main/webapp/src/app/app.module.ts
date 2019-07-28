import { LoginModule } from './public/login/login.module';
import { LoginEffects } from './public/login/effects/login.effects';
import { EffectsModule } from '@ngrx/effects';
import { ComponentsModule } from './common/components/components.module';
import { CalendarLocale } from './common/calendar/calendar.locale';
import { GlobalData } from './util/global.data';
import { Utils } from './util/utils';
import { TranslateService } from './internationalization/translate.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PublicComponent } from './layout/public/public.component';
import { PagesComponent } from './layout/pages/pages.component';
import { HomeComponent } from './pages/home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './guard/auth.guard';
import { MenuComponent } from './layout/template/menu/menu.component';
import { HeaderComponent } from './layout/template/header/header.component';
import { FooterComponent } from './layout/template/footer/footer.component';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { JwtModule } from '@auth0/angular-jwt';
import { InterceptorHttpService } from './interceptors/interceptor.http.service';
import { TableModule } from 'primeng/table';
import { BlockUIModule } from 'ng-block-ui';
import { CookieService } from 'ngx-cookie-service';
import { PipeModule } from './pipe/pipe.module';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { reducers, metaReducers } from './reducers';
import { environment } from '../environments/environment';

export function tokenGetter() {
  return localStorage.getItem('acess_token');
}

export function setupTranslateFactory(service: TranslateService, cookie: CookieService): Function {
  return () => {
    const lang = cookie.get('app-language');
    service.use((lang && lang.length > 0) ? lang : Utils.getDefaultLanguage());
  };
}

@NgModule({
  declarations: [
    AppComponent,
    PublicComponent,
    PagesComponent,
    HomeComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    AppRoutingModule,
    FormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter
      }
    }),
    BlockUIModule.forRoot(),
    TableModule,
    BrowserAnimationsModule,
    PipeModule.forRoot(),
    ComponentsModule.forRoot(),
    LoginModule.forRoot(),
    StoreDevtoolsModule.instrument({maxAge: 50}),
    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
      }
    }),
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    EffectsModule.forRoot([]),
  ],
  providers: [
    AuthGuard,
    GlobalData,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorHttpService,
      multi: true
    },
    TranslateService,
    {
      provide: APP_INITIALIZER,
      useFactory: setupTranslateFactory,
      deps: [ TranslateService, CookieService ],
      multi: true
    },
    CookieService,
    CalendarLocale
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
