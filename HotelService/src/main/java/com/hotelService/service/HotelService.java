package com.hotelService.service;

import java.util.List;

import com.hotelService.entity.Hotel;

public interface HotelService {

    // Create Hotel
    Hotel createHotel(Hotel hotel);

    // Get All Hotels
    List<Hotel> getAllHotels();

    // Get Hotel By Id
    Hotel getHotel(Long id);

}