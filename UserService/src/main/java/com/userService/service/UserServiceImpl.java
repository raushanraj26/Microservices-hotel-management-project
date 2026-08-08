package com.userService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userService.entity.Hotel;
import com.userService.entity.Rating;
import com.userService.entity.User;
import com.userService.exception.ResourceNotFound;
import com.userService.repository.UserRepository;
@Service
public class UserServiceImpl  implements UserService{
@Autowired
private UserRepository userrepository;

@Autowired
private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) {
//		String randomuserid=UUID.randomUUID().toString();
//		user.setId(randomuserid);
		return userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
//		get user from db with the help of user repository
		User user= userrepository.findById(id).orElseThrow( ()->new ResourceNotFound());
		//fetch rating of the above user from rating service
		//http://localhost:8083/ratings/user/{userid}
		Rating[] ratinguser=restTemplate.getForObject("http://RatingService/ratings/user/"+user.getId(), Rating[].class);
		List<Rating> ratings=Arrays.stream(ratinguser).toList();
		//each rating ka hotel fetch kr rhe 
		List<Rating> ratinglist=ratings.stream().map(rating->{
			//api call to hotel service to get hotel
			ResponseEntity<Hotel> forentity=restTemplate.getForEntity("http://HotelService/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel=forentity.getBody();
			//set the hotel to rating
			rating.setHotel(hotel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratinglist);
		return user;
	}



}
