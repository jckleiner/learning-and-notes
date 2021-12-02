package com.greydev.springbootconfiguration;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


// Getters and setters are required
@Getter
@Setter
@ToString
@Validated
@ConfigurationProperties(prefix = "mail")
public class MailConfigProperties {

	@NotBlank
	private String host;

	@Min(1025)
	@Max(65536)
	private int port;

	// must match an email address format
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
	private String from;

	private List<String> defaultRecipients;
	private List<Double> doubleList;
	private Map<String, Boolean> additionalHeaders;
	private Credentials credentials;

}


