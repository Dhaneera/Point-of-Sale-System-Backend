package org.CypsoLabs.controller;

import org.CypsoLabs.dto.UsersDto;
import org.CypsoLabs.entity.User;

import org.CypsoLabs.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private UsersService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UsersDto userDto) {
        User user = userService.addUser(userDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UsersDto userDto) {
        User user = userService.updateUser(id, userDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteUser(@PathVariable Long id) {
            try {
                userService.deleteUser(id);
                return ResponseEntity.ok("User deleted successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
            }
        }
}
