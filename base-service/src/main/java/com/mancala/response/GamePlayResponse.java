package com.mancala.response;

import java.util.List;

import com.mancala.dto.PlayerDTO;

public class GamePlayResponse {

	private List<Integer> playerOnePits; 
	private List<Integer> playerTwoPits; 
	private PlayerDTO playerTurn; 
	private Integer totalSeedsPlayerOne;
	private Integer totalSeedsPlayerTwo;
	private String message;
	private Integer playerOneId; 
	private Integer playerTwoId; 
	private PlayerDTO winner;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public List<Integer> getPlayerOnePits() {
		return playerOnePits;
	}

	public void setPlayerOnePits(List<Integer> playerOnePits) {
		this.playerOnePits = playerOnePits;
	}

	public List<Integer> getPlayerTwoPits() {
		return playerTwoPits;
	}

	public void setPlayerTwoPits(List<Integer> playerTwoPits) {
		this.playerTwoPits = playerTwoPits;
	}

	public PlayerDTO getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(PlayerDTO playerTurn) {
		this.playerTurn = playerTurn;
	}

	public Integer getTotalSeedsPlayerOne() {
		return totalSeedsPlayerOne;
	}

	public void setTotalSeedsPlayerOne(Integer totalSeedsPlayerOne) {
		this.totalSeedsPlayerOne = totalSeedsPlayerOne;
	}

	public Integer getTotalSeedsPlayerTwo() {
		return totalSeedsPlayerTwo;
	}

	public void setTotalSeedsPlayerTwo(Integer totalSeedsPlayerTwo) {
		this.totalSeedsPlayerTwo = totalSeedsPlayerTwo;
	}



	public Integer getPlayerOneId() {
		return playerOneId;
	}

	public void setPlayerOneId(Integer playerOneId) {
		this.playerOneId = playerOneId;
	}

	public Integer getPlayerTwoId() {
		return playerTwoId;
	}

	public void setPlayerTwoId(Integer playerTwoId) {
		this.playerTwoId = playerTwoId;
	}

	public PlayerDTO getWinner() {
		return winner;
	}

	public void setWinner(PlayerDTO winner) {
		this.winner = winner;
	}




}
