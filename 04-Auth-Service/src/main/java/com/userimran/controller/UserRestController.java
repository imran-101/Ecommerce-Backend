package com.userimran.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userimran.entity.User;
import com.userimran.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam("user") String userJson,
                                             @RequestParam(value = "file", required = false) MultipartFile image) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userJson, User.class);
        User registered = userService.registerUser(user, image);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listOfUsers = userService.getAllUsers();
        if (listOfUsers.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listOfUsers);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) throws Exception {
        User loginedUser = userService.loginUser(user);
        return new ResponseEntity<>(loginedUser, HttpStatus.OK);
    }

    @PutMapping("/register/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        User updatedUser = userService.UpdateUser(id, user);
        if (updatedUser != null)
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId)  {
        try {
            return ResponseEntity.ok(userService.deleteUserById(userId));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
