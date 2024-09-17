package com.userimran.service;

import com.userimran.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService  {
    public User registerUser(User user, MultipartFile file) throws Exception;
    public User loginUser(User user);
    public User getUserById(Long id);
    public List<User> getAllUsers();
    public User deleteUserById(Long id);
}
