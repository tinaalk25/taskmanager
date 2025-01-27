package com.team4.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.taskmanager.model.Board;
import com.team4.taskmanager.service.BoardService;

@Controller
	@RequestMapping("/boards")
	public class BoardController {

	    @Autowired
	    private BoardService boardService;

	    @GetMapping
	    public String getAllBoards(Model model) {
	        model.addAttribute("boards", boardService.findAll());
	        return "boards/list";
	    }

	    @GetMapping("/create")
	    public String createBoardForm(Model model) {
	        model.addAttribute("board", new Board());
	        return "boards/create";
	    }

	    @PostMapping("/create")
	    public String createBoard(@ModelAttribute Board board) {
	        boardService.save(board);
	        return "redirect:/boards";
	    }

	    @GetMapping("/{id}")
	    public String getBoardById(@PathVariable Long id, Model model) {
	        Board board = boardService.findById(id);
	        model.addAttribute("board", board);
	        model.addAttribute("tasks", board.getTasks());
	        return "boards/view";
	    }
}
