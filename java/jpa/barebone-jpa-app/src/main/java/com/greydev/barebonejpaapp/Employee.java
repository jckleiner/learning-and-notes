package com.greydev.barebonejpaapp;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity // from javax.persistence.Entity, not hibernate
@Table(name = "EMPLOYEE_DATA")
public class Employee {

	// JPA by default maps fields to columns, in nothing specified
	// you can tell it not to, or configure it

	@Id // Specifies the primary key of an entity
	@Setter(AccessLevel.NONE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Temporal(TemporalType.TIME)
	private Date dateOfBirth;

	private LocalDateTime localDateTime;

	@Enumerated(EnumType.STRING)
	private EmployeeType type;

}

