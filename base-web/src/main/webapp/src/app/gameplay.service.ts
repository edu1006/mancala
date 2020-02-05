import { Injectable } from '@angular/core';
import { GamePlayerRequest } from './gameplay/gameplay';

@Injectable({
  providedIn: 'root'
})
export class GameplayService {

  constructor() { }

  initGamePlay(game: GamePlayerRequest) {

    console.log("game", JSON.stringify(game))
    const url = "http://localhost:8080/api/gameplay/";

    const fetchPromise = fetch(url, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'post',
      body: JSON.stringify(game)
    });

    return fetchPromise.then(response => response.json());

  }


  makeMove(game:GamePlayerRequest): any {
    console.log("game", JSON.stringify(game))
    const url = "http://localhost:8080/api/gameplay/";

    const fetchPromise = fetch(url, {
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'put',
      body: JSON.stringify(game)
    });

    return fetchPromise.then(response => response.json());

  }

}
