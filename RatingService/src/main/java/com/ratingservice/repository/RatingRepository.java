package com.ratingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratingservice.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    // Get ratings by User Id
    List<Rating> findByUserId(Long userId);

    // Get ratings by Hotel Id
    List<Rating> findByHotelId(Long hotelId);

}