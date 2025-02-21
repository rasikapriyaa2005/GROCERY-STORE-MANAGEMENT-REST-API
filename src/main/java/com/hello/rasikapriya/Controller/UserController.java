package com.hello.rasikapriya.Controller;

import com.hello.rasikapriya.Models.User;
import com.hello.rasikapriya.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> users) {
        List<User> savedUsers = userService.addUsers(users);
        return ResponseEntity.ok(savedUsers);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}")
    public ResponseEntity<Page<User>> getUsersWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(userService.getUsersWithPagination(pageNumber, pageSize));
    }

    @GetMapping("/sortBy/{field}")
    public ResponseEntity<List<User>> getUsersSorted(@PathVariable String field) {
        return ResponseEntity.ok(userService.getUsersSorted(field));
    }

    @GetMapping("/page/{pageNumber}/size/{pageSize}/sortBy/{field}")
    public ResponseEntity<Page<User>> getUsersWithPaginationAndSorting(
            @PathVariable int pageNumber, 
            @PathVariable int pageSize, 
            @PathVariable String field) {
        return ResponseEntity.ok(userService.getUsersWithPaginationAndSorting(pageNumber, pageSize, field));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
