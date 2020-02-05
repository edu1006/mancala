package com.mancala.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mancala.dto.GamePlayDTO;
import com.mancala.dto.PlayerDTO;
import com.mancala.model.Board;
import com.mancala.response.GamePlayResponse;

@Service
public class GamePlayService {

	@Autowired
	Board board;

	@Autowired
	PlayerService playerService;

	public GamePlayResponse startGame(GamePlayDTO gamePlayRequest) {

		GamePlayDTO gamePlayDTO = loadGamePlayDTO(gamePlayRequest);
		board.inicializeBoard(gamePlayDTO);
		return createGamePlayResponse(board, gamePlayDTO);
	}

	private GamePlayDTO loadGamePlayDTO(GamePlayDTO gameRequest) {
		GamePlayDTO game = new GamePlayDTO();
		game.setGameId(gameRequest.getGameId());
		game.setPlayerOne(playerService.getPlayer(gameRequest.getPlayerOne().getId()));
		game.setPlayerTwo(playerService.getPlayer(gameRequest.getPlayerTwo().getId()));
		return game;
	}

	public GamePlayResponse makeMove(GamePlayDTO gamePlayRequest) {
		board.makeMove(gamePlayRequest.getPitPosition());

		if(board.isGameEnding()) {
			return createResponseWinner(board, gamePlayRequest); 
		}
		return createGamePlayResponse(board, gamePlayRequest);

	}


	private GamePlayResponse createResponseWinner(Board board, GamePlayDTO gamePlayRequest) {
		GamePlayResponse game = createGamePlayResponse(board, gamePlayRequest);
		game.setWinner(board.getPlayerWin());
		return game;
	}

	private GamePlayResponse createGamePlayResponse(Board board, GamePlayDTO gamePlayDTO) {
		GamePlayResponse game = new GamePlayResponse();
		game.setPlayerOneId(gamePlayDTO.getPlayerOne().getId() );
		game.setPlayerTwoId(gamePlayDTO.getPlayerTwo().getId() );
		
		game.setPlayerOnePits(getListPits(board, gamePlayDTO.getPlayerOne()));
		game.setPlayerTwoPits(getListPits(board, gamePlayDTO.getPlayerTwo()));
		
		game.setPlayerTurn(board.getPlayerTurn());
		game.setTotalSeedsPlayerOne(board.getTotalSeedsPerPleayer().get(gamePlayDTO.getPlayerOne()));
		game.setTotalSeedsPlayerTwo(board.getTotalSeedsPerPleayer().get(gamePlayDTO.getPlayerTwo()));
		
		return game;
	}

	private List<Integer> getListPits(Board board, PlayerDTO player) {
		return Arrays.stream(board.getPitsPerPlayer().get(player)).boxed().collect(Collectors.toList());
	}

}
