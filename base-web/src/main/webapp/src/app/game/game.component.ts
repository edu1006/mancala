import { Component, OnInit, Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PlayerDTO } from '../player';
import { PlayerService } from '../player.service';
import { GameService } from '../game.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { GameDTO } from './game';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  playerOne: PlayerDTO;
  playerTwo: PlayerDTO;
  public gameForm: FormGroup;

  constructor(@Inject(ActivatedRoute) private route: ActivatedRoute,
    @Inject(PlayerService) private playerService: PlayerService,
    @Inject(GameService) private gameService: GameService,
    @Inject(Router) private router: Router
  ) {
    this.gameForm = new FormGroup({});


  }

  ngOnInit() {
    //load player 1
    this.playerService.get(this.route.snapshot.paramMap.get("p1")).then(res => {
      this.playerOne = res;
    });
    //load player 2
    this.playerService.get(this.route.snapshot.paramMap.get("p2")).then(res => {
      this.playerTwo = res;
    });

  }

  onSubmit() {
    let game = new GameDTO();
    game.playerOneId = this.playerOne.id;
    game.playerTwoId = this.playerTwo.id;

    this.gameService.createGame(game).then(res => {
      game.id = res.id;
      if (game.id != null) {
        this.router.navigate(['/gameplay', {gId: game.id,  p1: this.playerOne.id, p2: this.playerTwo.id}]);
      }
    });
  }

}