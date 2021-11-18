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
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackRatingsFromApi")
	public List<Rating> getRatingsFromApi(List<Movie> movies) {
		return movies.stream()
				.map(movie -> restTemplate.getForObject(
						"http://ratings-data-service/rating/" + movie.getMovieId(), Rating.class))
				.collect(Collectors.toList());
	}

	private List<Rating> getFallbackRatingsFromApi(List<Movie> movies) {
		System.out.println(" -> getFallbackRatingsFromApi");
		return List.of(new Rating("0", 0));
	}

}
