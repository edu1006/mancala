package com.mancala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mancala.model.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

}
