package com.userService.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.userService.entity.Hotel;

@FeignClient("HotelService")
public interface HotelServiceFeign {
	@GetMapping("/hotels/{hotelid}")
	Hotel getHotel(@PathVariable ("hotelid") Long hotelid);
	
	

}
