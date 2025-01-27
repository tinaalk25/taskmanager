package com.team4.taskmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;



import com.team4.taskmanager.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Tag findByName(String name);

}
