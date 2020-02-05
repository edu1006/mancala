package com.mancala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mancala.dto.PlayerDTO;
import com.mancala.service.PlayerService;

@RequestMapping("/api/player")
@Controller
public class PlayerController {

	@Autowired
	PlayerService service; 
	

    @GetMapping(value = "/{id}")
	public ResponseEntity<PlayerDTO> get (@PathVariable(value = "id") Integer id) {
		return ResponseEntity.ok().body(service.getPlayer(id)); 
    }
    
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlayerDTO> save(@RequestBody PlayerDTO request) {
    	PlayerDTO response = service.save(request); 
    	return  ResponseEntity.ok(response) ; 
	}

    
}
