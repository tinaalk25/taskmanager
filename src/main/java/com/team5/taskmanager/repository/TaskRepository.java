package com.team4.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team4.taskmanager.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByBoardId(Long boardId);

	List<Task> findByBoardIdAndStatus(Long boardId, String status);
}

