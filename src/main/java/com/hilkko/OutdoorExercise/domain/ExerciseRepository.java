package com.hilkko.OutdoorExercise.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository <Exercise, Long> {
	List<Exercise> findBySport(String sport);
}
