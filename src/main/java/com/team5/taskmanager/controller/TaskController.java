package com.team4.taskmanager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.team4.taskmanager.model.*;
import com.team4.taskmanager.service.BoardService;
import com.team4.taskmanager.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private BoardService boardService;

    @PostMapping("/create")
    public String createTask(@RequestParam Long boardId, @ModelAttribute Task task) {
        Board board = boardService.findById(boardId);
        task.setBoard(board);
        taskService.save(task);
        return "redirect:/boards/" + boardId;
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskService.save(task);
        return "redirect:/boards/" + task.getBoard().getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = taskService.findById(id);
        Long boardId = task.getBoard().getId();
        taskService.delete(id);
        return "redirect:/boards/" + boardId;
    }
    
    @PostMapping("/add-tag/{id}")
    public String addTagToTask(@PathVariable Long id, @RequestParam String tagName) {
        taskService.addTagToTask(id, tagName);
        return "redirect:/boards/" + id;
    }

    @PostMapping("/remove-tag/{taskId}/{tagId}")
    public String removeTagFromTask(@PathVariable Long taskId, @PathVariable Long tagId) {
        taskService.removeTagFromTask(taskId, tagId);
        return "redirect:/boards/" + taskId;
    }
    
    @PostMapping("/change-status/{id}")
    public String changeTaskStatus(@PathVariable Long id, @RequestParam String status) {
        Task task = taskService.findById(id);
        task.setStatus(status);
        taskService.save(task);
        return "redirect:/tasks/board/" + task.getBoard().getId();
    }
}
