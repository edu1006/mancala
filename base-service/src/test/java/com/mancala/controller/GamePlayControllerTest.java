package com.mancala.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mancala.controller.GameController;
import com.mancala.controller.GamePlayController;
import com.mancala.controller.PlayerController;
import com.mancala.dto.GamePlayDTO;
import com.mancala.dto.PlayerDTO;
import com.mancala.request.GameRequest;
import com.mancala.response.GamePlayResponse;
import com.mancala.util.PlayerUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GamePlayControllerTest implements AbstractTest {


	@Autowired
	GameController gameController;

	@Autowired
	PlayerController playerController; 

	@Autowired
	GamePlayController gamePlayController; 

	
	@Test
	public void contexLoads1() throws Exception {
		testControllersIsNotNull();
		createPlayers();
		createGame();
	}

	private void testControllersIsNotNull() {
		assertThat(gameController).isNotNull();
		assertThat(playerController).isNotNull();
		assertThat(gamePlayController).isNotNull(); 
				
	}

	public void createPlayers() throws Exception {
		playerController.save(PlayerUtil.createPlayerRequestForTests("p1")); 
		playerController.save(PlayerUtil.createPlayerRequestForTests("p2")); 

	}
	public void createGame() { 
		GameRequest game = new GameRequest();
		game.setPlayerOneId(PLAYER_ONE_ID);
		game.setPlayerTwoId(PLAYER_TWO_ID);
		gameController.create(game ); 
	}

	@Test
	public void gamePlayTest() { 
		GamePlayDTO gamePlay = createGamePlay(); 
		gamePlay.setPlayerOne(new PlayerDTO());
		gamePlay.setPlayerTwo(new PlayerDTO());
		gamePlay.getPlayerOne().setId(PLAYER_ONE_ID);
		gamePlay.getPlayerTwo().setId(PLAYER_TWO_ID);
		ResponseEntity<GamePlayResponse> response = gamePlayController.startGame(gamePlay); 
		assertGamePlayResponse(response.getBody()) ; 

	}

	private void assertGamePlayResponse(GamePlayResponse response) {
		assertThat(response).isNotNull();  
		assertThat(response.getPlayerOneId()).isNotNull();  
		assertThat(response.getPlayerTwoId()).isNotNull();  

		assertThat(response.getPlayerTwoPits()  ).isNotNull();  
		assertThat(response.getPlayerOnePits()).isNotNull();  
		
	}

	private GamePlayDTO createGamePlay() {
		GamePlayDTO gamePlay = new GamePlayDTO() ;
		gamePlay.setPlayerOne(PlayerUtil.createPlayerRequestForTests(PLAYER_ONE_ID));
		gamePlay.setPlayerTwo(PlayerUtil.createPlayerRequestForTests(PLAYER_TWO_ID));
		return gamePlay;
	}
	@Test
	public void makeMove() { 
		GamePlayDTO game = new GamePlayDTO() ;
		game.setGameId(1);
		game.setPitPosition(0);

		game.setPlayerOne(PlayerUtil.createPlayerRequestForTests(1));
		game.setPlayerTwo(PlayerUtil.createPlayerRequestForTests(2));
		ResponseEntity<GamePlayResponse> response = gamePlayController.makeMove(game ); 
		assertGamePlayResponse(response.getBody());
		
	}
}
