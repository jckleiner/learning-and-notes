package com.greydev.springbootconfiguration;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter // no need for setters, since all are immutable
@ConfigurationProperties(prefix = "mail.credentials")
@ConstructorBinding
@Validated
public class ImmutableConfigProperties {

	@NotBlank
	private final String authMethod;
	@NotBlank
	private final String username;
	@NotBlank
	private final String password;


	public ImmutableConfigProperties(
			String authMethod,
			String username,
			String password) {
		this.authMethod = authMethod;
		this.username = username;
		this.password = password;
	}

}


