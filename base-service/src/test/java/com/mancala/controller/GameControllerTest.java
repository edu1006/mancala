package com.mancala.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mancala.controller.GameController;
import com.mancala.controller.PlayerController;
import com.mancala.dto.PlayerDTO;
import com.mancala.request.GameRequest;
import com.mancala.response.GameResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameControllerTest implements AbstractTest {


	@Autowired
	GameController gameController;

	@Autowired
	PlayerController playerController;

	@Test
	public void contexLoads1() throws Exception {
		assertThat(gameController).isNotNull();
		assertThat(playerController).isNotNull();
		PlayerDTO pRequest = new PlayerDTO();
		pRequest.setName(PLAYER_NAME);

		playerController.save(pRequest);

		pRequest = new PlayerDTO();
		pRequest.setName(PLAYER_NAME_TWO);
		playerController.save(pRequest);

	}

	@Test
	public void testCreateGame2() {
		GameRequest game = new GameRequest();
		game.setPlayerOneId(PLAYER_ONE_ID);
		game.setPlayerTwoId(PLAYER_TWO_ID);
		GameResponse response = gameController.create(game).getBody();

		assertThat(response.getId()).isNotNull();

	}

	@Test
	public void testGetAllGames4() {
		List<GameResponse> games = gameController.getAll().getBody();

		assertThat(games).isNotNull();
		assertThat(games.isEmpty()).isFalse();

	}

	@Test
	public void testGetGame3() {
		GameResponse response = gameController.get(GAME_ID).getBody();
		assertThat(response).isNotNull();
	}

}
