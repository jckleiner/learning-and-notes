package com.greydev.barebonejpaapp;

import java.util.Date;

import javax.persistence.*;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity
public class AccessCard {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.NONE)
	private int id;

	private Date issueDate;

	private boolean isActive;

	private String firmwareVersion;

	@OneToOne(fetch = FetchType.EAGER)
	private Employee employee;

}
