package com.mancala.response;

public class GameResponse {
	private Integer id;
	private Integer idPlayOne;
	private Integer idPlayTwo;

	public Integer getIdPlayTwo() {
		return idPlayTwo;
	}

	public void setIdPlayTwo(Integer idPlayTwo) {
		this.idPlayTwo = idPlayTwo;
	}

	public Integer getIdPlayOne() {
		return idPlayOne;
	}

	public void setIdPlayOne(Integer idPlayOne) {
		this.idPlayOne = idPlayOne;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GameResponse [id=" + id + ", idPlayOne=" + idPlayOne + ", idPlayTwo=" + idPlayTwo + "]";
	}

}
