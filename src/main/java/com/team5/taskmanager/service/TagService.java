package com.team4.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.taskmanager.model.Tag;
import com.team4.taskmanager.repository.TagRepository;



	@Service
	public class TagService {

	    @Autowired
	    private TagRepository tagRepository;

	    public Tag save(Tag tag) {
	        return tagRepository.save(tag);
	    }

	    public Tag findById(Long id) {
	        return tagRepository.findById(id).orElse(null);
	    }

	    public List<Tag> findAll() {
	        return tagRepository.findAll();
	    }

	    public Tag findByName(String name) {
	        return tagRepository.findByName(name);
	    }

		
	}

