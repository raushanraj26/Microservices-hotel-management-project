package com.hotelService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelService.entity.Hotel;
import com.hotelService.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {

        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(Long id) {

        return hotelRepository.findById(id).orElse(null);
    }

}