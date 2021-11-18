package com.greydev.moviecatalogservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.greydev.model.Movie;
import com.greydev.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Component
public class RatingsDataService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackMoviesFromApi")
	public List<Movie> getMoviesFromApi(List<String> movieIDsForUser) {
		return movieIDsForUser.stream()
				.map(movieId -> restTemplate.getForObject(
						"http://movie-info-service/movies/" + movieId, Movie.class))
				.collect(Collectors.toList());
	}


	private List<Movie> getFallbackMoviesFromApi(List<String> movieIDsForUser) {
		System.out.println(" -> getFallbackMoviesFromApi");
		return List.of(new Movie("0", "fallback movie", "no description"));
	}

}
