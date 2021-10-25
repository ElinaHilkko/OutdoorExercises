package com.hilkko.OutdoorExercise;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hilkko.OutdoorExercise.web.ExerciseController;

@SpringBootTest
class OutdoorExerciseApplicationTests {
	
	@Autowired
	ExerciseController exerciseController;

	@Test
	void contextLoads() {
		assertNotNull(exerciseController);
	}
}
