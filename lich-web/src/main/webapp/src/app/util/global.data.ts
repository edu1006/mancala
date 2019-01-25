import { Injectable } from '@angular/core';
import { User } from '../model/user';

@Injectable()
export class GlobalData {

    setUser(user: User) {
        sessionStorage.setItem('user-data', JSON.stringify(user));
    }

    getUser(): User {
        const jsonUser = sessionStorage.getItem('user-data');
        return JSON.parse(jsonUser);
    }

}
