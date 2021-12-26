package com.greydev.barebonejpaapp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// from javax.persistence.Entity, not hibernate
// @Entity
@Table(name = "EMPLOYEE_DATA")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@Temporal(TemporalType.TIME)
	private Date dateOfBirth;

	private LocalDateTime localDateTime;

	@Enumerated(EnumType.STRING)
	private EmployeeType type;

	// @JoinColumn(name = "bla_id")

	@OneToOne(fetch = FetchType.EAGER)
	private AccessCard accessCard;


	@Override public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", dateOfBirth=" + dateOfBirth +
				", localDateTime=" + localDateTime +
				", type=" + type +
				'}';
	}
}

