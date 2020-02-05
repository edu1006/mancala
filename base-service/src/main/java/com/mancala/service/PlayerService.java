package com.mancala.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mancala.dto.PlayerDTO;
import com.mancala.model.Player;
import com.mancala.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository repository;

	public PlayerDTO save(PlayerDTO request) {
		return convertToResponse(repository.save(createPlayer(request)));
	}

	private PlayerDTO convertToResponse(Player player) {
		PlayerDTO response = new PlayerDTO();
		response.setName(player.getName());
		response.setId(player.getId());
		return response;
	}

	private Player createPlayer(PlayerDTO request) {
		Player player = new Player();
		player.setName(request.getName());
		return player;
	}

	public PlayerDTO getPlayer(Integer id) {
		Optional<Player> value = repository.findById(id);
		if (value.isPresent()) {
			return convertToResponse(value.get());
		}
		throw new NoSuchElementException();

	}

	public Player findById(Integer id) {
		Optional<Player> value = repository.findById(id);
		if (value.isPresent()) {
			return value.get();
		}
		throw new NoSuchElementException();
	}
}
