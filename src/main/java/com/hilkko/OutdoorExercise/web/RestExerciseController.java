package com.hilkko.OutdoorExercise.web;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilkko.OutdoorExercise.domain.Exercise;
import com.hilkko.OutdoorExercise.domain.ExerciseRepository;
import com.hilkko.OutdoorExercise.domain.Person;
import com.hilkko.OutdoorExercise.domain.PersonRepository;

@RestController
public class RestExerciseController {
	@Autowired
	private ExerciseRepository eRepository;
	@Autowired
	private PersonRepository pRepository;
	
	@GetMapping("/exercises")
	public List<Exercise> exerciseRest(){
		return (List<Exercise>) eRepository.findAll();
	}
	
	@GetMapping("/exercise/{exerciseId}") 
	public Optional<Exercise> findExerciseRest(@PathVariable("exerciseId") Long exerciseId){
		return eRepository.findById(exerciseId);
	}
	
	@PostMapping("/addexercise")
	public String addExerciseRest(@RequestBody Exercise newExercise) {
		eRepository.save(newExercise);
		return newExercise.toString();
	}
	
	@PutMapping("/exercise/{exerciseId}")
	public Optional<Exercise> editExerciseRest(@RequestBody Exercise newExercise, @PathVariable("exerciseId") Long exerciseId){
	    return eRepository.findById(exerciseId)
	      .map(exercise -> {
	    	  exercise.setSport(newExercise.getSport());
	    	  exercise.setDate(newExercise.getDate());
	    	  exercise.setMinute(newExercise.getMinute());
	    	  exercise.setDistance(newExercise.getDistance());
	        return eRepository.save(exercise);
	      });
	  }

	@DeleteMapping("/exercise/{exerciseId}")
	void deleteExerciseRest(@PathVariable("exerciseId") Long exerciseId){
		eRepository.deleteById(exerciseId);
	}
	
	@GetMapping("/persons")
	public List<Person> personRest(){
		return (List<Person>) pRepository.findAll();
	}
	
	@GetMapping("/person/{personId}") 
		public Optional<Person> findPersonRest(@PathVariable("personId") Long personId){
		return pRepository.findById(personId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value = "/addperson")
	public String addPersonRest(@RequestBody Person newPerson) {
		pRepository.save(newPerson);
		return newPerson.toString();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/person/{personId}")
	public Optional<Person> editPersonRest(@RequestBody Person newPerson, @PathVariable("personId") Long personId){
	    return pRepository.findById(personId)
	      .map(person -> {
	    	  person.setName(newPerson.getName());
	        return pRepository.save(person);
	      });
	  }

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/person/{personId}")
	void deletePersonRest(@PathVariable("personId") Long personId) {
		pRepository.deleteById(personId);
	}
}