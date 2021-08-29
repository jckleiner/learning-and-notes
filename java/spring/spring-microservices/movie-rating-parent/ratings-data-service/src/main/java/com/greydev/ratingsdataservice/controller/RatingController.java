package com.greydev.ratingsdataservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.greydev.model.Rating;


@RestController
public class RatingController {

	private final static Map<String, Rating> MOVIE_ID_TO_RATINGS = Map.of(
			// userID, list of movie ID's the user rated
			"1", new Rating("1", 4.9F),
			"2", new Rating("2", 4.3F),
			"3", new Rating("3", 4.8F)
	);

	@GetMapping("/rating/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		System.out.println("/rating/" + movieId);

		return MOVIE_ID_TO_RATINGS.get(movieId);
	}
}


