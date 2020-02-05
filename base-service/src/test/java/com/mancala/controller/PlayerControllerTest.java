package com.mancala.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mancala.controller.PlayerController;
import com.mancala.dto.PlayerDTO;
import com.mancala.util.PlayerUtil;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlayerControllerTest implements AbstractTest {

	@Autowired
	PlayerController controller;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void testSavePlayer() {
		ResponseEntity<PlayerDTO> response = controller.save(PlayerUtil.createPlayerRequestForTests(PLAYER_NAME));
		assertThat(response).isNotNull();
		assertThat(response.getBody().getId()).isNotNull();
		assertThat(response.getBody().getName()).isNotNull();
		assertThat(response.getBody().getName().contentEquals("p1")).isNotNull();

	}

	@Test
	public void testGetPlayer() {
		ResponseEntity<PlayerDTO> response = controller.get(PLAYER_ID);
		assertThat(response).isNotNull();
		assertThat(response.getBody().getId()).isNotNull();
		assertThat(response.getBody().getName()).isNotNull();
		assertThat(response.getBody().getName().contentEquals("p1")).isNotNull();

	}

}
