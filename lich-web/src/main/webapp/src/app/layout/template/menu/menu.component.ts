import { Component, OnInit, OnDestroy } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { AppState } from '../../../reducers/index';
import { selectFunctionalities } from '../../../public/login/selectors/login.selectors';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { FunctionalityEnum } from '../../../enums/functionality.enum';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnDestroy {

  // functionalities: Observable<number[]>;
  funcsSubscription: any;
  funcs: Array<number>;

  funcsEnum = FunctionalityEnum;

  constructor(private store: Store<AppState>) {}

  ngOnInit() {
    // this.functionalities = this.store.pipe(select(selectFunctionalities));
    // this.funcs = this.connect({
    //   f: this.store.pipe(select(selectFunctionalities))
    // });

    this.funcsSubscription = this.store
      .pipe(select(selectFunctionalities))
      .subscribe(funcs => this.funcs = funcs);
  }

  ngOnDestroy() {
    this.funcsSubscription.unsubscribe();
  }

  checkFunctionality(id: number) {
    /*return this.functionalities.pipe(
      map(functionalities => {
        const filter = functionalities.filter(f => f === id);
        return (filter.length > 0);
      })
    );*/
    return this.funcs.includes(id);
  }

}
