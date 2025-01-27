package com.team4.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.taskmanager.model.Tag;
import com.team4.taskmanager.model.Task;
import com.team4.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TagService tagService;

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findByBoardId(Long boardId) {
        return taskRepository.findByBoardId(boardId);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
    
    public Task addTagToTask(Long taskId, String tagName) {
        Task task = findById(taskId);
        Tag tag = tagService.findByName(tagName);
        if (tag == null) {
            tag = new Tag();
            tag.setName(tagName);
            tagService.save(tag);
        }
        task.getTags().add(tag);
        return save(task);
    }

    public Task removeTagFromTask(Long taskId, Long tagId) {
        Task task = findById(taskId);
        task.getTags().removeIf(tag -> tag.getId().equals(tagId));
        return save(task);
    }
    public List<Task> findByStatus(Long boardId, String status) {
        return taskRepository.findByBoardIdAndStatus(boardId, status);
    }
}

