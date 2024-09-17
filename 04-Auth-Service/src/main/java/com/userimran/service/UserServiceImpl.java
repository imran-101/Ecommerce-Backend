package com.userimran.service;

import com.userimran.entity.User;
import com.userimran.repository.RoleRepository;
import com.userimran.repository.UserRepository;
import com.userimran.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    @Override
    public User registerUser(User user, MultipartFile file) throws Exception {
        String fileName = FileUtils.saveFile(file.getName(), file);
        user.setFilePath(fileName);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User loginUser(User user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());
        Authentication authenticate = authManager.authenticate(authToken);
        if (authenticate.isAuthenticated()) {
            return userRepository.findByEmail(user.getEmail());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid email or password");
        }

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Invalid user id"));
        userRepository.deleteById(id);
        return user;
    }

}
