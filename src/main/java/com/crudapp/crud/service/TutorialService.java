package com.crudapp.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudapp.crud.model.Tutorial;
import com.crudapp.crud.repository.TutorialRepository;

@Service
public class TutorialService {
	
	@Autowired
	TutorialRepository repository;
	
	public List<Tutorial> findAll() throws Exception {
		List<Tutorial> tutorials = repository.findAll();
		if(!tutorials.isEmpty()) return tutorials; 
		throw new Exception("Nenhum tutorial encontrado...");
	}
	
	public Optional<Tutorial> findById(Long idTutorial) throws Exception {
		Tutorial tutorial = repository.findById(idTutorial).orElseThrow(
				() -> new Exception("Tutorial não encontrado para o id::"+idTutorial));
		return Optional.of(tutorial);
	}
	
	public List<Tutorial> findByPublished() throws Exception {
		List<Tutorial> tutorials = repository.findByPublished(true);
		if(!tutorials.isEmpty()) return tutorials; 
		throw new Exception("Nenhum tutorial publicado....");
	}
	
	public List<Tutorial> findByTitle(String title) throws Exception {
		List<Tutorial> tutorials =  repository.findByTitleContainingIgnoreCase(title);
		if(!tutorials.isEmpty()) return tutorials; 
		throw new Exception("Não existe tutorial com esse título");
	}
	
	public Tutorial saveTutorial(Tutorial objTutorial) throws Exception {
		if(objTutorial.getIdTutorial() == null) return repository.save(objTutorial);
		
		Tutorial newTutorial = findById(objTutorial.getIdTutorial()).get();
		
		if(objTutorial.getDescription() != null && objTutorial.getDescription() != "")
			newTutorial.setDescription(objTutorial.getDescription());
		
		if(objTutorial.getTitle() != null && objTutorial.getTitle() != "")
			newTutorial.setTitle(objTutorial.getTitle());
		
		if(objTutorial.getPublished() != null)
			newTutorial.setPublished(objTutorial.getPublished());
		
		return repository.save(newTutorial);
	}
	
	public void deleteOneTutorial(Long idTutorial) throws Exception {
		findById(idTutorial);
		repository.deleteById(idTutorial);
	}
	
	public void deleteAllTutorials() {
		repository.deleteAll();
	}

}
