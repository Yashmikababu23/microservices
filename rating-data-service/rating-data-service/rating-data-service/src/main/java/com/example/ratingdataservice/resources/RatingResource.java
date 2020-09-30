package com.example.ratingdataservice.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingdataservice.model.Rating;
import com.example.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {
	@Autowired
	private DbSetting dbSetting;
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId")String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId")String userId) {
		UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
	}
	@RequestMapping("/config")
	public String getDetails() {
		return dbSetting.getConnection() +dbSetting.getPort()+dbSetting.getHost();		
	}
}


