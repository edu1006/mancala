package com.mancala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mancala.dto.GamePlayDTO;
import com.mancala.response.GamePlayResponse;
import com.mancala.service.GamePlayService;

@RestController()
@RequestMapping("/api/gameplay")
public class GamePlayController {

	@Autowired
	GamePlayService service;
	
	@PostMapping( name = "start",  consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GamePlayResponse> startGame(@RequestBody GamePlayDTO gamePlay) {
		return ResponseEntity.ok().body(service.startGame(gamePlay)); 
	}

	@PutMapping( name = "makeMove",   consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GamePlayResponse> makeMove(@RequestBody GamePlayDTO gamePlayRequest) {
		return ResponseEntity.ok().body(service.makeMove (gamePlayRequest) ); 

	}

}
