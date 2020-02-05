package com.mancala.dto;

public class GamePlayDTO {

	private PlayerDTO playerOne;
	private PlayerDTO playerTwo;
	private Integer gameId;
	private Integer pitPosition; 
	
	public PlayerDTO getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(PlayerDTO playerOne) {
		this.playerOne = playerOne;
	}
	public PlayerDTO getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(PlayerDTO playerTwo) {
		this.playerTwo = playerTwo;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getPitPosition() {
		return pitPosition;
	}
	public void setPitPosition(Integer pitPosition) {
		this.pitPosition = pitPosition;
	} 
	
	
	
}
