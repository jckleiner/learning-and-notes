package com.greydev.moviecatalogservice.controller;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.greydev.model.CatalogItem;
import com.greydev.model.Movie;
import com.greydev.model.Rating;
import com.greydev.moviecatalogservice.service.MovieInfoService;
import com.greydev.moviecatalogservice.service.RatingsDataService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import static java.util.Collections.emptyList;


@RestController
public class MovieCatalogController {

	private final MovieInfoService movieInfoService;
	private final RatingsDataService ratingsDataService;


	public MovieCatalogController(MovieInfoService movieInfoService,
			RatingsDataService ratingsDataService) {
		this.movieInfoService = movieInfoService;
		this.ratingsDataService = ratingsDataService;
	}


	private final static Map<String, List<String>> USER_ID_TO_MOVIES = Map.of(
			// userID, list of movie ID's the user rated
			"44", List.of("1", "2"),
			"55", List.of("2", "3"),
			"66", List.of("3")
	);


	@GetMapping("/catalog/{userId}")
	public List<CatalogItem> getMovieCatalogs(@PathVariable String userId) {
		System.out.println("/catalog/" + userId);

		// all movie ID's the user ever rated
		List<String> movieIDsForUser = Optional.ofNullable(USER_ID_TO_MOVIES.get(userId)).orElse(emptyList());

		if (movieIDsForUser.isEmpty()) {
			System.out.println("-> no movies found for user " + userId);
			return Collections.emptyList();
		}

		// fetch the information for every movie from our movie-info-service - ignoring the exception handling aspect
		List<Movie> movies = ratingsDataService.getMoviesFromApi(movieIDsForUser);

		// fetch the rating for every movie from our ratings-data-service - ignoring the exception handling aspect
		List<Rating> ratings = movieInfoService.getRatingsFromApi(movies);

		List<CatalogItem> catalogItems = new ArrayList<>();

		// combine all information into catalog items
		for (Movie movie : movies) {
			Optional<Float> optionalRatingForMovie = ratings.stream()
					.filter(rating -> rating.getMovieId().equals(movie.getMovieId()))
					.map(Rating::getRating)
					.findFirst();
			float rating = optionalRatingForMovie.orElse(0F);
			catalogItems.add(new CatalogItem(movie.getName(), movie.getDescription(), rating));
		}

		return catalogItems;
	}

}

