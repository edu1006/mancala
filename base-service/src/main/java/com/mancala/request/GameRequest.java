package com.mancala.request;

public class GameRequest {
	private	Integer playerOneId;
	private Integer playerTwoId;


	@Override
	public String toString() {
		return "GameRequest [playerOneId=" + getPlayerOneId() + ", playlerTwoId=" + getPlayerTwoId() + "]";
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

	
}
