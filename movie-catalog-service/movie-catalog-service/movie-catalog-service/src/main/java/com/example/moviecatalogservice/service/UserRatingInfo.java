package com.example.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.model.Rating;
import com.example.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class UserRatingInfo {
	@Autowired
	private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class);
	}
	public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new  UserRating();
				userRating.setUserId(userId);
				userRating.setUserRating(Arrays.asList(new Rating("0",0)));
				return userRating;
	}

}
