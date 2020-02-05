package com.mancala.util;

import com.mancala.dto.PlayerDTO;

public class PlayerUtil {
	
	public static PlayerDTO createPlayerRequestForTests(String name)  {
		PlayerDTO request = new PlayerDTO();
		request.setName(name);
		return request ; 
	}

	public static PlayerDTO createPlayerRequestForTests(Integer id)  {
		PlayerDTO request = new PlayerDTO();
		request.setId(id);
		return request ; 
	}

}
