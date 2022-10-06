package com.crudapp.crud.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crudapp.crud.model.Tutorial;
import com.crudapp.crud.service.TutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {
	
	@Autowired
	TutorialService service;
	
	@GetMapping
	public ResponseEntity<List<Tutorial>> findAll(@RequestParam(required = false) String title){
		try {
			if(title != null && title != "") {
				return ResponseEntity.ok(service.findByTitle(title));
			} else {
				List<Tutorial> tutorials = service.findAll();
				return ResponseEntity.ok(tutorials);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tutorial> findById(@PathVariable(name = "id") Long idTutorial){
		try {
			return ResponseEntity.ok(service.findById(idTutorial).get());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}		
	}
	
	@GetMapping("/published")
	public ResponseEntity<List<Tutorial>> findByPublished(){
		try {
			return ResponseEntity.ok(service.findByPublished());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
//	@GetMapping("/title")
//	public ResponseEntity<List<Tutorial>> findByTitle(@RequestBody String title){
//		try {
//			return ResponseEntity.ok(service.findByTitle(title));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//	}
	
	@PostMapping
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial objTutorial){
		try {
			return ResponseEntity.status(
					HttpStatus.CREATED)
					.body(service.saveTutorial(objTutorial));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Tutorial> updateTutorial(
			@PathVariable(name = "id") Long idTutorial,
			@RequestBody Tutorial objTutorial){
		
		objTutorial.setIdTutorial(idTutorial);
		
		try {
			return ResponseEntity.status(
					HttpStatus.OK)
					.body(service.saveTutorial(objTutorial));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteOneTutorial(@PathVariable(name = "id") Long idTutorial) {
		try {
			service.deleteOneTutorial(idTutorial);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping
	public void deleteAllTutorials() {
		service.deleteAllTutorials();
	}

}
