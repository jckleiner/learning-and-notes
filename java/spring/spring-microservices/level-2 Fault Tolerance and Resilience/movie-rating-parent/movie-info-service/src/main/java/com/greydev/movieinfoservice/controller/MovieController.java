package com.greydev.movieinfoservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.greydev.model.Movie;
import com.greydev.model.Rating;


@RestController
public class MovieController {

	private final static Map<String, Movie> MOVIE_ID_TO_MOVIES = Map.of(
			// userID, list of movie ID's the user rated
			"1", new Movie("1", "The Matrix", "a simulation movie"),
			"2", new Movie("2", "Lord of the Rings", "a middle earth movie"),
			"3", new Movie("3", "Interstellar", "a sci-fi movie")
	);

	@Value("${app.instanceName}")
	private String instanceName;

	@Value("${spring.application.name}")
	private String appName;


	@GetMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable String movieId) {
		System.out.println("/movies/" + movieId + " called. App: " + appName + ", instance: " + instanceName);

		return MOVIE_ID_TO_MOVIES.get(movieId);
	}
}

