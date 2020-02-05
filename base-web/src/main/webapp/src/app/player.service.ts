import { Injectable } from '@angular/core';
import { PlayerDTO } from './player';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {
  constructor() { }


  save(player: PlayerDTO): any {
    console.log(JSON.stringify(player))
    const url = "http://localhost:8080/api/player";
    const fetchPromise = fetch(url, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'post',
      body: JSON.stringify(player)
    });

    return fetchPromise.then(response => response.json()).
      catch(error => {
        //código http  - timeout; 
        return 408;
      }
      );


  }


  get(id: any): any {
    const url = "http://localhost:8080/api/player/"+id;

    const fetchPromise = fetch(url);

    return fetchPromise.then(response => response.json());

  }

}
