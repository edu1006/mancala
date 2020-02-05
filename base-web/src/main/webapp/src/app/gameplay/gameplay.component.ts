import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GamePlayerRequest } from './gameplay';
import { GameplayService } from '../gameplay.service';
import { GamePlayResponse } from './gameplayresponse';

@Component({
  selector: 'app-gameplay',
  templateUrl: './gameplay.component.html',
  styleUrls: ['./gameplay.component.css']
})
export class GameplayComponent implements OnInit {

  gameResponse: GamePlayResponse;


  constructor(
    @Inject(ActivatedRoute) private route: ActivatedRoute,
    @Inject(GameplayService) private gamePlayService: GameplayService,
    @Inject(Router) private router: Router 
  ) {



  }

  ngOnInit() {

    let playerOneId = this.route.snapshot.paramMap.get("p1");
    let playerTwoId = this.route.snapshot.paramMap.get("p2");
    let gameId = this.route.snapshot.paramMap.get("gId");
    let gamePlay: GamePlayerRequest = this.createGameRequest(playerOneId, playerTwoId, gameId);
    console.log("request", gamePlay)
    this.initGamePlay(gamePlay)
  }
  createGameRequest(playerOneId, playerTwoId, gameId): GamePlayerRequest {
    let game: GamePlayerRequest = new GamePlayerRequest();
    game.playerOne.id = playerOneId;
    game.playerTwo.id = playerTwoId;
    game.gameId = gameId;
    return game;
  }

  initGamePlay(game: GamePlayerRequest): any {
    this.gamePlayService.initGamePlay(game).then(res => {

      console.log("game play loaded ", res)
      this.gameResponse = res;
      console.log("game play response", this.gameResponse);
    });
  }


  makeMovePlayerOne(pit, i){
    console.log(this.gameResponse.playerOneId == this.gameResponse.playerTurn.id)
    if(this.gameResponse.playerOneId == this.gameResponse.playerTurn.id){
      //invert index 
      this.makeMove(pit,(i - 5) *-1 )
    }

  }

  makeMovePlayerTwo(pit, i){

    if(this.gameResponse.playerTwoId == this.gameResponse.playerTurn.id){
      this.makeMove(pit, i)
    }
  }

  makeMove(pit, i) {
    console.log("pit", pit);
    console.log("index", i);
    let gamePlayer: GamePlayerRequest = new GamePlayerRequest();
    gamePlayer.pitPosition = i;
    gamePlayer.playerOne.id = this.gameResponse.playerOneId; 
    gamePlayer.playerTwo.id = this.gameResponse.playerTwoId; 

    this.gamePlayService.makeMove(gamePlayer).then(res => {

      console.log("game play loaded ", res)
      this.gameResponse = res;
      console.log("game play response", this.gameResponse);

    });
  }


  goBackToHome(){
    this.router.navigate(['/', {}]);
  }
}
