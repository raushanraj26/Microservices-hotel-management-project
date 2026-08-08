package com.userService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private Long id;

    private Long userId;

    private Long hotelId;

    private int rating;

    private String feedback;
    private Hotel hotel;

    
}