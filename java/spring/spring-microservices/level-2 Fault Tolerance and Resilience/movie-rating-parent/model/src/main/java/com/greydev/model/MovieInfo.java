package com.greydev.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;


@Data
@NoArgsConstructor // required
public class MovieInfo {

	@JsonProperty("original_title")
	private String title;

	@JsonProperty("overview")
	private String description;
}

