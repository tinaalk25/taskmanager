package com.team4.taskmanager.service;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import com.team4.taskmanager.model.Board;
import com.team4.taskmanager.model.Tag;
import com.team4.taskmanager.model.Task;
import com.team4.taskmanager.repository.BoardRepository;
import com.team4.taskmanager.repository.TagRepository;
import com.team4.taskmanager.repository.TaskRepository;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;



@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TagRepository tagRepository;

    public Board createBoard(String name) {
        Board board = new Board();
        board.setName(name);
        return boardRepository.save(board);
    }

    public Task createTask(Long boardId, String title, String description, LocalDate deadline, String status) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setStatus(status);
        task.setBoard(board);
        return taskRepository.save(task);
    }

    public void addTagToTask(Long taskId, String tagName) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        Tag tag = tagRepository.findByName(tagName);
        
        if(tag == null) {
          tag = new Tag(tagName);
        }
        task.getTags().add(tag);
        taskRepository.save(task);
    }

	public List<Board> findAll() {
		// TODO Auto-generated method stub
		 return boardRepository.findAll();
	}

	public void save(Board board) {
		boardRepository.save(board);
		
	}

	public Board findById(Long id) {
		return boardRepository.findById(id).orElse(null);

	}
}
