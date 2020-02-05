import { Injectable } from '@angular/core';
import { GameDTO } from './game/game';
@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor() { }


  createGame(game: GameDTO): any {
    console.log("game", JSON.stringify(game))
    const url = "http://localhost:8080/api/game";
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
}
