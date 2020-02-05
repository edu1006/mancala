package com.mancala.util;

public enum BoardEnum {

//    PITS_PER_PLAYER(7),
	PLAYER_ONE_BOARD_INITIAL_POSITION(6),
	
	SEEDS_PER_PIT(6),
    BOARD_SIZE(6),
    BOARD_SIZE_ARRAY_COUNT(5); 

    private final int value;

    BoardEnum(final int pits) {
        this.value = pits;
    }

    public int getValue() {
        return value;
    }

	public static int getIndexInverted(int pitPosition) {
		return (pitPosition - BOARD_SIZE_ARRAY_COUNT.getValue()) *-1; 
	}


}
