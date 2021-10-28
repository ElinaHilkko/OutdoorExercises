package com.hilkko.OutdoorExercise.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hilkko.OutdoorExercise.domain.Exercise;
import com.hilkko.OutdoorExercise.domain.ExerciseRepository;
import com.hilkko.OutdoorExercise.domain.Person;
import com.hilkko.OutdoorExercise.domain.PersonRepository;

@Controller
public class ExerciseController {
	@Autowired
	private ExerciseRepository eRepository;
	@Autowired
	private PersonRepository pRepository;
	
	@GetMapping("/outdoorexercise")
	public String outdoorExercise(Model model) {
		model.addAttribute("exercises", eRepository.findAll());
		return "outdoorexercise";
	}
	
	@GetMapping("/addexercise")
	public String addExercise(Model model) {
		model.addAttribute("exercise", new Exercise());
		model.addAttribute("persons", pRepository.findAll());
		return "addexercise";
	}
	
	@PostMapping("/saveexercise")
	public String save(@Valid Exercise exercise, BindingResult bindingresult, Model model) {
		if (bindingresult.hasErrors()) {
			model.addAttribute("persons", pRepository.findAll());
			return "addexercise";
		}
		eRepository.save(exercise);
		return "redirect:outdoorexercise";
	}	
	
	@GetMapping("/deleteexercise/{exerciseId}")
	public String deleteExercise(@PathVariable("exerciseId") Long exerciseId, Model model) {
		eRepository.deleteById(exerciseId);
		return "redirect:../outdoorexercise";
	}
	
	@GetMapping("/editexercise/{exerciseId}")
	public String editExercise(@PathVariable("exerciseId") Long exerciseId, Model model) {
	model.addAttribute("exercise", eRepository.findById(exerciseId));
	model.addAttribute("persons", pRepository.findAll());
	return "editexercise";
	}
	
	@GetMapping("/showpersons")
	public String showPerson(Model model) {
		model.addAttribute("persons", pRepository.findAll());
		return "showpersons";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addperson")
	public String addPerson(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "addperson";
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/saveperson")
	public String save(@Valid Person person, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addperson";
		}
		pRepository.save(person);
		return "redirect:showpersons";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/deleteperson/{personId}")
	public String deletePerson(@PathVariable("personId") Long personId, Model model) {
		pRepository.deleteById(personId);
		return "redirect:../showpersons";
	}
	@GetMapping("/back")
	public String back(Model model) {
		model.addAttribute("exercises", eRepository.findAll());
		return "redirect:outdoorexercise";
	}
}
