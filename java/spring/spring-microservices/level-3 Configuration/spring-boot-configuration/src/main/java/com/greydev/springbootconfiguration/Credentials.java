package com.greydev.springbootconfiguration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// Getters and setters are required
@Getter
@Setter
@ToString
public class Credentials {
	private String authMethod;
	private String username;
	private String password;
}


