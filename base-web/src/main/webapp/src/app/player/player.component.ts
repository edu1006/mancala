import { Component, OnInit, inject, Inject } from '@angular/core';
import {  FormGroup, FormControl, Validators } from '@angular/forms';
import { PlayerDTO } from '../player';
import { PlayerService } from '../player.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  public playerForm: FormGroup;
  player: PlayerDTO;
  constructor(@Inject(PlayerService) private playerService: PlayerService,
    @Inject(Router) private router: Router) {


    this.playerForm = new FormGroup({
      playerOneName: new FormControl('', Validators.required),
      playerTwoName: new FormControl('', Validators.required)

    });
  }

  ngOnInit() {
  }


  onSubmit() {

    console.log("player one", this.playerForm.value.playerOneName)
    console.log("player two", this.playerForm.value.playerTwoName)

    if(this.playerForm.value.playerOneName && this.playerForm.value.playerTwoName){
      this.createPlayer();
    }else{
      alert("fill name!"); 
    }
  }
  createPlayer() {
    //save and load player with id
    let playerOne: PlayerDTO = new PlayerDTO();
    playerOne.name = this.playerForm.value.playerOneName;


    let playerTwo: PlayerDTO = new PlayerDTO();
    playerTwo.name = this.playerForm.value.playerTwoName;

    this.playerService.save(playerOne).then(res => {
      playerOne = res
      console.log("res id", playerOne.id)
      if (playerOne.id != null) {
        //saving p2
        console.log("p2 saving")
        this.playerService.save(playerTwo).then(res => {
          playerTwo = res;
          console.log("res playerTwo", playerTwo)
          if (playerTwo.id != null) {
            console.log("all users save")
            this.router.navigate(['/game', { p1: playerOne.id, p2: playerTwo.id}]);
          }

        })

      }

    }
    );
  }
}
