package com.hilkko.OutdoorExercise;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hilkko.OutdoorExercise.domain.Exercise;
import com.hilkko.OutdoorExercise.domain.ExerciseRepository;
import com.hilkko.OutdoorExercise.domain.PersonRepository;

@SpringBootTest
public class ExerciseRepositoryTest {
	
	@Autowired
	ExerciseRepository eRepository;
	PersonRepository pRepository;
	
	@Test
	public void findBySportShouldReturnSport() {
		List<Exercise> exercises = eRepository.findBySport("hiihto");
		Assertions.assertThat(exercises.get(0).getSport()).isEqualTo("hiihto");
	}
	
	@Test
	public void findBySportShouldReturnSize() {
		List<Exercise> exercises = eRepository.findBySport("hiihto");
		Assertions.assertThat(exercises).hasSize(1);
	}
	
	@Test
	public void addExercise() {
		Exercise exercise = new Exercise("suunnistus", "15.5.2021", 60, 5.9);
		eRepository.save(exercise);
		assertNotNull(exercise.getExerciseId());

	}
	
	@Test
	public void deleteExercise() {
		List<Exercise> exercises = eRepository.findBySport("juoksu");
		eRepository.deleteById(exercises.get(0).getExerciseId());
		exercises = eRepository.findBySport("juoksu");
		Assertions.assertThat(exercises).hasSize(0);
	}
}
