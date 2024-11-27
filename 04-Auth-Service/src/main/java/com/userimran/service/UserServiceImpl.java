package com.userimran.service;

import com.userimran.entity.Role;
import com.userimran.entity.User;
import com.userimran.repository.RoleRepository;
import com.userimran.repository.UserRepository;
import com.userimran.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final PasswordEncoder pwdEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user, MultipartFile file) throws Exception {
        User u = userRepository.findByEmail(user.getEmail());
        if (u == null) {
            if (file != null) {
                String fileName = FileUtils.saveFile(file);
                user.setFilePath(fileName);
            }
            String encodedPwd = pwdEncoder.encode(user.getPassword());
            user.setPassword(encodedPwd);
            List<Role> userRoles = new ArrayList<>();
            Role userRole = roleRepository.findById(2).orElseThrow();
            userRoles.add(userRole);
            user.setRoles(userRoles);
            return userRepository.save(user);
        }
        return u;
    }

    @Override
    public User loginUser(User user) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getPassword());

        Authentication authenticate = null;

        try {
            authenticate = authManager.authenticate(authToken);
            if (authenticate.isAuthenticated()) {
                return userRepository.findByEmail(user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User UpdateUser(Long id, User user) {
        User updateUser = userRepository.findById(id).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User Not Found"));

        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            updateUser.setFirstName(user.getFirstName());
        }
        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            updateUser.setLastName(user.getLastName());
        }
        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            updateUser.setEmail(user.getEmail());
        }
        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            updateUser.setPassword(user.getPassword());
        }
        if (Objects.nonNull(user.getMobile()) && !"".equalsIgnoreCase(user.getMobile())) {
            updateUser.setMobile(user.getMobile());
        }
        if (Objects.nonNull(user.getPincode()) && !"".equalsIgnoreCase(user.getPincode())) {
            updateUser.setPincode(user.getPincode());
        }
        if (Objects.nonNull(user.getCity()) && !"".equalsIgnoreCase(user.getCity())) {
            updateUser.setCity(user.getCity());
        }
        if (Objects.nonNull(user.getState()) && !"".equalsIgnoreCase(user.getState())) {
            updateUser.setState(user.getState());
        }
        if (Objects.nonNull(user.getAddr()) && !"".equalsIgnoreCase(user.getAddr())) {
            updateUser.setAddr(user.getAddr());
        }
        return userRepository.save(updateUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Invalid user id"));
        user.getRoles().clear();
        userRepository.save(user);
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

}
