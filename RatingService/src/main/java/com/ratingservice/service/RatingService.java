package com.ratingservice.service;

import java.util.List;

import com.ratingservice.entity.Rating;

public interface RatingService {

    // Create Rating
    Rating createRating(Rating rating);

    // Get All Ratings
    List<Rating> getAllRatings();

    // Get Rating By Id
    Rating getRating(Long id);

    // Get Ratings By User Id
    List<Rating> getRatingsByUserId(Long userId);

    // Get Ratings By Hotel Id
    List<Rating> getRatingsByHotelId(Long hotelId);

}