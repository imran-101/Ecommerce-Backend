package com.userimran.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userimran.entity.User;
import com.userimran.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam("user") String userJson,
                                             @RequestParam("file") MultipartFile image) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);
        User registered = userService.registerUser(user, image);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws Exception {
        User loginedUser = userService.loginUser(user);
        return new ResponseEntity<>(loginedUser, HttpStatus.OK);
    }
}
