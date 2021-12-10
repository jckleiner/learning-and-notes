package com.greydev.barebonejpaapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
@NoArgsConstructor

public enum EmployeeType {
	// ordinal -> 0
	FULL_TIME("FULL_TIME"),
	// ordinal -> 1
	PART_TIME("PART_TIME");

	private String value;
}

