package com.team4.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.taskmanager.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
