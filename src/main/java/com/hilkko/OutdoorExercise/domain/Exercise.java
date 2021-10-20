package com.hilkko.OutdoorExercise.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long exerciseId;
	@Size(min=2, max=30)
	private String sport;
	@Pattern(regexp = "^(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})$")
	private String date;
	@Min(value = 1)
	@Max(value = 1000)
	private int minute;
	@Min(value = 0)
	@Max(value = 1000)
	private double distance;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "personId")
	private Person person;
	
	public Exercise() {
	}

	public Exercise(@Size(min = 2, max = 30) String sport,
			@Pattern(regexp = "^(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})$") String date,
			@Min(1) @Max(1000) int minute, @Min(0) @Max(1000) double distance, Person person) {
		super();
		this.sport = sport;
		this.date = date;
		this.minute = minute;
		this.distance = distance;
		this.person = person;
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Exercise [exerciseId=" + exerciseId + ", sport=" + sport + ", date=" + date + ", minute=" + minute
				+ ", distance=" + distance + ", person=" + person + "]";
	}
	
}
