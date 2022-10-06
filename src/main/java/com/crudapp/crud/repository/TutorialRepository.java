package com.crudapp.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudapp.crud.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

	List<Tutorial> findByPublished(Boolean published);
	
	List<Tutorial> findByTitleContainingIgnoreCase(String title);
	
}
