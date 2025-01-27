package com.team4.taskmanager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.team4.taskmanager.model.*;
import com.team4.taskmanager.service.BoardService;
import com.team4.taskmanager.service.TagService;
import com.team4.taskmanager.service.TaskService;

@Controller
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/add-to-task")
    public String addTagToTask(@RequestParam Long taskId, @RequestParam String tagName) {
        taskService.addTagToTask(taskId, tagName);
        return "redirect:/boards/" + taskId; // redirect to the task's board
    }

    @PostMapping("/remove-from-task")
    public String removeTagFromTask(@RequestParam Long taskId, @RequestParam Long tagId) {
        taskService.removeTagFromTask(taskId, tagId);
        return "redirect:/boards/" + taskId; // redirect to the task's board
    }
}


