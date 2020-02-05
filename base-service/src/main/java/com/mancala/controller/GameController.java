package com.mancala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mancala.request.GameRequest;
import com.mancala.response.GameResponse;
import com.mancala.service.GameService;

@RestController()
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	GameService service;

	@PostMapping
	public ResponseEntity<GameResponse> create(@RequestBody GameRequest game) {
		return  ResponseEntity.ok().body(service.create(game.getPlayerOneId(), game.getPlayerTwoId())) ; 

	}

	@GetMapping
	public ResponseEntity<List<GameResponse>> getAll() {
		return   ResponseEntity.ok().body(service.getAll()) ; 

	}

	@GetMapping(path = "/id")
	public ResponseEntity<GameResponse> get(@PathVariable Integer id) {
	
		return  ResponseEntity.ok().body(service.get(id)) ; 
	}

}
