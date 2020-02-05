import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayerComponent } from './player/player.component';
import { GameComponent } from './game/game.component';
import { GameplayComponent } from './gameplay/gameplay.component';


const routes: Routes = [
  { path: '', component: PlayerComponent },
  { path: 'game', component: GameComponent },
  { path: 'gameplay', component: GameplayComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
