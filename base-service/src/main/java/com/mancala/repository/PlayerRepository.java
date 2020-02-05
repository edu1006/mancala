package com.mancala.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mancala.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
