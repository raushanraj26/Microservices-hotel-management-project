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

import com.userService.config.HotelServiceFeign;
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
@Autowired
private HotelServiceFeign hotelServicefeign;
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

	
	
//	----------------------------isme rating and hotel , dono ko RestTemplate ke help se fetch kar rahe hai----------
//	@Override
//	public User getUserById(Long id) {
////		get user from db with the help of user repository
//		User user= userrepository.findById(id).orElseThrow( ()->new ResourceNotFound());
//		//fetch rating of the above user from rating service
//		//http://localhost:8083/ratings/user/{userid}
//		Rating[] ratinguser=restTemplate.getForObject("http://RatingService/ratings/user/"+user.getId(), Rating[].class);
//		List<Rating> ratings=Arrays.stream(ratinguser).toList();
//		//each rating ka hotel fetch kr rhe 
//		List<Rating> ratinglist=ratings.stream().map(rating->{
//			//api call to hotel service to get hotel
//			ResponseEntity<Hotel> forentity=restTemplate.getForEntity("http://HotelService/hotels/"+rating.getHotelId(), Hotel.class);
//			Hotel hotel=forentity.getBody();
//			//set the hotel to rating
//			rating.setHotel(hotel);
//			//return the rating
//			return rating;
//		}).collect(Collectors.toList());
//		user.setRatings(ratinglist);
//		return user;
//	}
	
	
//	--------------------isme rating ->restTemplate and hotel-> Feignclient ke help se fetch kar rahe hai--------
//	feignclient ko smjhne ke liye use kar rhe hai, FeignClient +RestTemplate  client ko use kr rhe--------
	@Override
	public User getUserById(Long id) {
//		get user from db with the help of user repository
		User user= userrepository.findById(id).orElseThrow( ()->new ResourceNotFound());
		//fetch rating of the above user from rating service
		//http://localhost:8083/ratings/user/{userid}

		Rating[] ratinguser=restTemplate.getForObject("http://RatingService/ratings/users/"+user.getId(), Rating[].class);
		List<Rating> ratings=Arrays.stream(ratinguser).toList();
		//each rating ka hotel fetch kr rhe 
		List<Rating> ratinglist=ratings.stream().map(rating->{
			//api call to hotel service to get hotel through using feign client
			Hotel hotel=hotelServicefeign.getHotel(rating.getHotelId());
			//set the hotel to rating
			rating.setHotel(hotel);
			//return the rating
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratinglist);
		return user;
	}



}
