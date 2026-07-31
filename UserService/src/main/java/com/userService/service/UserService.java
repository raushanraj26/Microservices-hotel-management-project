package com.userService.service;


import java.util.List;

import com.userService.entity.User;

public interface UserService {
User saveUser(User user);
List<User> getAllUser();
User getUserById(Long id);

}
