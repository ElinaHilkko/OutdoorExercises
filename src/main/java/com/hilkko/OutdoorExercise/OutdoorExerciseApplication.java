package com.hilkko.OutdoorExercise;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hilkko.OutdoorExercise.domain.Exercise;
import com.hilkko.OutdoorExercise.domain.ExerciseRepository;
import com.hilkko.OutdoorExercise.domain.Person;
import com.hilkko.OutdoorExercise.domain.PersonRepository;
import com.hilkko.OutdoorExercise.domain.User;
import com.hilkko.OutdoorExercise.domain.UserRepository;


@SpringBootApplication
public class OutdoorExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OutdoorExerciseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner exercisesDemo(ExerciseRepository eRepository, PersonRepository pRepository, UserRepository uRepository) {
		return (args) -> {
			System.out.println("save a few example data");
			
			Stream.of("Maija","Matti","Kati").forEach(name -> {
				pRepository.save(new Person(name));
			});
			eRepository.save(new Exercise ("hiihto", "20.2.2021", 45, 9.5, pRepository.findByName("Maija").get(0)));
			eRepository.save(new Exercise ("juoksu", "5.3.2021", 30, 8.9, pRepository.findByName("Matti").get(0)));
			System.out.println("fetch all exercises");
			for (Exercise exercise : eRepository.findAll()) {
				System.out.println(exercise.toString());
			}
			
			User user1 = new User("user", "$2a$10$QoRjWK0QqsTP/7rWpTVE9ucGE/AlqPfcAcbtaI3x1mfOp4cEe2B4q", "USER");
			User user2 = new User("admin", "$2a$10$dEnHkVMj22P7RtK.OZm6e.YXfIv53qbuXOyVYNDEgYEuWWu3MIb.W", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
		};
	}
}
