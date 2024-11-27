package com.userimran.service;

import com.userimran.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService  {
    User registerUser(User user, MultipartFile file) throws Exception;
    User loginUser(User user);
    User getUserById(Long id);
    User UpdateUser(Long id, User user);
    List<User> getAllUsers();
    String deleteUserById(Long id);
}
