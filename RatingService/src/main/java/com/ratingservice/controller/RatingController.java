package com.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ratingservice.entity.Rating;
import com.ratingservice.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    // Create Rating
    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {

        return ratingService.createRating(rating);
    }

    // Get All Ratings
    @GetMapping
    public List<Rating> getAllRatings() {

        return ratingService.getAllRatings();
    }

    // Get Rating By Id
    @GetMapping("/{id}")
    public Rating getRating(@PathVariable Long id) {

        return ratingService.getRating(id);
    }

    // Get Ratings By User Id
    @GetMapping("/users/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable Long userId) {

        return ratingService.getRatingsByUserId(userId);
    }

    // Get Ratings By Hotel Id
    @GetMapping("/hotels/{hotelId}")
    public List<Rating> getRatingsByHotelId(@PathVariable Long hotelId) {

        return ratingService.getRatingsByHotelId(hotelId);
    }

}