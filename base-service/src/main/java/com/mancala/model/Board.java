package com.mancala.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.mancala.dto.GamePlayDTO;
import com.mancala.dto.PlayerDTO;
import com.mancala.util.BoardEnum;
/**
 * Singleton responsible for maneging board game 
 */
@Component
public class Board {

	private static final Integer INITIAL_VALUE_SEEDS = 0;
	private static final int EMPTY_SEEDS = 0;
	private static final Integer AUX_NUMBER_ONE = 1;
	private static final int AUX_NUMBER_ZERO = 0;
	private HashMap<PlayerDTO, int[]> pitsPerPlayer;
	private PlayerDTO playerTurn;
	private Map<PlayerDTO, Integer> totalSeedsPerPleayer;

	public Board() {
		this.pitsPerPlayer = new HashMap<>();
		this.totalSeedsPerPleayer = new HashMap<>();
	}

	/**
	 * Inicialize game play with default values
	 * 
	 * @param gamePlay
	 */
	public void inicializeBoard(GamePlayDTO gamePlay) {
		// active player one to play
		getPitsPerPlayer().clear();
		this.totalSeedsPerPleayer.clear();
		gamePlay.getPlayerOne().setActive(Boolean.TRUE);
		this.playerTurn = gamePlay.getPlayerOne();
		getPitsPerPlayer().put(gamePlay.getPlayerOne(), inicializePitsPerPLayer());
		getPitsPerPlayer().put(gamePlay.getPlayerTwo(), inicializePitsPerPLayer());

		totalSeedsPerPleayer.put(gamePlay.getPlayerOne(), INITIAL_VALUE_SEEDS);
		totalSeedsPerPleayer.put(gamePlay.getPlayerTwo(), INITIAL_VALUE_SEEDS);

	}

	/**
	 * 
	 * 
	 * {@link BoardEnum}
	 * @return
	 */
	private int[] inicializePitsPerPLayer() {
		int[] pits = new int[BoardEnum.BOARD_SIZE.getValue()];
		for (int i = 0; i < pits.length; i++) {
			pits[i] = BoardEnum.SEEDS_PER_PIT.getValue();
		}
		return pits;
	}

	public Map<PlayerDTO, int[]> getPitsPerPlayer() {
		return pitsPerPlayer;
	}

	public PlayerDTO getPlayerTurn() {
		return playerTurn;
	}

	/**
	 * Make move using pitPosition clicked by user. 
	 * This method has the player who click in session. 
	 * 
	 * @param pitPosition
	 */
	public void makeMove(Integer pitPosition) {
		int totalSeeds = makeFirstMoviment(pitPosition);
		int finalPosition = pitPosition + totalSeeds;
		int playerIdStartedPlayer = getPlayerTurn().getId();
		pitPosition++;
		while (totalSeeds > EMPTY_SEEDS) {
			totalSeeds = arrangePitsOnBoard(pitPosition, totalSeeds, playerIdStartedPlayer);
			totalSeeds--;
			if (totalSeeds >= 1
					|| totalSeeds <= EMPTY_SEEDS && finalPosition < this.pitsPerPlayer.get(getPlayerTurn()).length) {
				changePlayerTurn();
			}
			pitPosition = 0;
		}
	}

	private int makeFirstMoviment(Integer pitPosition) {
		int totalPits = getTotalPitsPerPlayerPosition(pitPosition);
		this.getPitsPerPlayer().get(playerTurn)[pitPosition] = 0;
		return totalPits;
	}

	private int getTotalPitsPerPlayerPosition(Integer pitPosition) {
		return pitsPerPlayer.get(playerTurn)[pitPosition];
	}

	/**
	 * reorge seeds on board 
	 * 
	 * @param pitPosition
	 * @param totalSeeds
	 * @param playerIdStartedPlayer
	 * @return
	 */
	private int arrangePitsOnBoard(int pitPosition, int totalSeeds, int playerIdStartedPlayer) {
		int[] pitsPlayerTurn = pitsPerPlayer.get(playerTurn);
		for (int i = pitPosition; i < pitsPlayerTurn.length && totalSeeds > EMPTY_SEEDS; i++, totalSeeds--) {
			pitsPlayerTurn[pitPosition] = pitsPlayerTurn[pitPosition++] + AUX_NUMBER_ONE;
		}
		increaseBigPit(pitPosition, pitsPlayerTurn, totalSeeds);
		verifiedSeedsAnotherPlayer(pitPosition, pitsPlayerTurn, playerIdStartedPlayer);
		return totalSeeds;
	}

	private void verifiedSeedsAnotherPlayer(int pitPosition, int[] pitsPlayerTurn, int playerIdStartedPlayer) {
		if (getPlayerTurn().getId() != playerIdStartedPlayer) {
			return;
		}
		if (--pitPosition < pitsPlayerTurn.length && pitsPlayerTurn[pitPosition] == AUX_NUMBER_ONE) {
			int seeds = getPitAnotherPlayerToGetIt(pitPosition);
			if (seeds > AUX_NUMBER_ZERO) {
				totalSeedsPerPleayer.put(playerTurn, totalSeedsPerPleayer.get(playerTurn) + seeds + AUX_NUMBER_ONE);
				this.pitsPerPlayer.get(getPlayerTurn())[pitPosition] = EMPTY_SEEDS;
			}
		}
	}

	private void increaseBigPit(int pitPosition, int[] pitsPlayerTurn, int totalSeeds) {
		if (pitPosition == pitsPlayerTurn.length && totalSeeds > AUX_NUMBER_ZERO) {
			totalSeedsPerPleayer.put(playerTurn, totalSeedsPerPleayer.get(playerTurn) + AUX_NUMBER_ONE);
		}
	}

	private int getPitAnotherPlayerToGetIt(int pitPosition) {
		int invertedPosition = (pitPosition - 5) * -1;
		int seeds = this.pitsPerPlayer.get(getInversePlayer())[invertedPosition];
		if (seeds > EMPTY_SEEDS) {
			this.pitsPerPlayer.get(getInversePlayer())[invertedPosition] = EMPTY_SEEDS;
		}
		return seeds;
	}

	private PlayerDTO getInversePlayer() {
		for (Entry<PlayerDTO, int[]> entry : pitsPerPlayer.entrySet()) {
			if (!entry.getKey().getId().equals(getPlayerTurn().getId())) {
				return entry.getKey();
			}
		}
		throw new NoSuchElementException();
	}

	private void changePlayerTurn() {
		for (Entry<PlayerDTO, int[]> entry : pitsPerPlayer.entrySet()) {
			if (!entry.getKey().getId().equals(getPlayerTurn().getId())) {
				playerTurn = entry.getKey();
				break;
			}
		}
	}

	public Map<PlayerDTO, Integer> getTotalSeedsPerPleayer() {
		return totalSeedsPerPleayer;
	}

	public void setTotalSeedsPerPleayer(Map<PlayerDTO, Integer> totalSeedsPerPleayer) {
		this.totalSeedsPerPleayer = totalSeedsPerPleayer;
	}

	public boolean isGameEnding() {
		for (Entry<PlayerDTO, int[]> entry : pitsPerPlayer.entrySet()) {
			if (Arrays.stream(entry.getValue()).sum() == 0) {
				return Boolean.TRUE;
			}

		}
		return Boolean.FALSE;
	}

	public PlayerDTO getPlayerWin() {
		if (checkForUserWithoutSeeds()) {
			if(totalSeedsPerPleayer.get(getPlayerTurn()) > totalSeedsPerPleayer.get(getInversePlayer()) ) {
				return getPlayerTurn();
			}else {
				return getInversePlayer(); 
			}
		}
		return null; 
	}

	private boolean checkForUserWithoutSeeds() {
		for (Entry<PlayerDTO, int[]> entry : pitsPerPlayer.entrySet()) {
			if (Arrays.stream(entry.getValue()).sum() == 0) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE; 
	}

}
