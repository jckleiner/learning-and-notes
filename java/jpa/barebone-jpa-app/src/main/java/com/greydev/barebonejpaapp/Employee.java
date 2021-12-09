package com.greydev.barebonejpaapp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity // from javax.persistence.Entity, not hibernate
@Table(name = "EMPLOYEE_DATA")
public class Employee {

	// JPA by default maps fields to columns, in nothing specified
	// you can tell it not to, or configure it

	@Id // Specifies the primary key of an entity
	private int id;

	private String name;
}

