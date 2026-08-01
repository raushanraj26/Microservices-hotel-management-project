package com.ratingservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratingservice.entity.Rating;
import com.ratingservice.exception.ResourceNotFound;
import com.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {

        return ratingRepository.findAll();
    }

    @Override
    public Rating getRating(Long id) {

        return ratingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Rating not found with id : " + id));
    }

    @Override
    public List<Rating> getRatingsByUserId(Long userId) {

        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(Long hotelId) {

        return ratingRepository.findByHotelId(hotelId);
    }

}