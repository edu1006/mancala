package com.mancala.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mancala.model.Game;
import com.mancala.model.Player;
import com.mancala.repository.GameRepository;
import com.mancala.response.GameResponse;

@Service
public class GameService {

	@Autowired
	GameRepository repository;

	@Autowired
	PlayerService playerService;

	public GameResponse create(Integer playerOneId, Integer playerTwoId) {
		if (playerOneId == null || playerTwoId == null) {
			throw new IllegalArgumentException("mandatory parameter not pass! ");
		}
		Game game = createGame(playerOneId, playerTwoId);
		repository.save(game);
		return createGameResponse(game, playerOneId, playerTwoId);
	}

	private Game createGame(Integer playerOneId, Integer playerTwoId) {
		Game game = new Game();
		game.setPlayers(setPlayers(playerOneId, playerTwoId));
		return game;
	}

	private GameResponse createGameResponse(Game game, Integer playerOneId, Integer playerTwoId) {
		GameResponse response = new GameResponse();

		response.setId(game.getId());
		response.setIdPlayOne(playerOneId);
		response.setIdPlayTwo(playerTwoId);

		return response;
	}

	private Set<Player> setPlayers(Integer playerOneId, Integer playerTwoId) {
		Set<Player> players = new HashSet<>();
		players.add(playerService.findById(playerOneId));
		players.add(playerService.findById(playerTwoId));
		return players;
	}

	public List<GameResponse> getAll() {
		List<Game> games = repository.findAll();

		List<GameResponse> gamesResponse = new ArrayList<>();
		if (!games.isEmpty()) {

			for (Game game : games) {
				gamesResponse.add(createGameResponse(game));
			}
		}
		return gamesResponse;
	}

	private GameResponse createGameResponse(Game game) {

		GameResponse response = new GameResponse();
		response.setId(game.getId());
		return response;
	}

	public GameResponse get(Integer id) {
		Optional<Game> value = repository.findById(id); 
		if (value.isPresent()) {
			return createGameResponse(value.get());
		}
		throw new NoSuchElementException() ; 
	}

}
