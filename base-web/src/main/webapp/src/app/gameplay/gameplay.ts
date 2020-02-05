import { PlayerDTO } from '../player';

export class GamePlayerRequest{ 

    constructor(){
        this.playerOne = new PlayerDTO();
        this.playerTwo = new PlayerDTO(); 
    }
    gameId;
    
    
    pitPosition; 
    playerOne:PlayerDTO;
    playerTwo:PlayerDTO;
}