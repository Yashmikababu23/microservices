package com.example.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviecatalogservice.model.CatalogItem;
import com.example.moviecatalogservice.model.UserRating;
import com.example.moviecatalogservice.service.MovieInfo;
import com.example.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
    MovieInfo movieInfo;
	@Autowired
	UserRatingInfo userRatingInfo;
	

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating ratings = userRatingInfo.getUserRating(userId);
		return ratings.getUserRating().stream()
				.map(rating ->  movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());

	}
	

	
}
