package com.hotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotelService.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}