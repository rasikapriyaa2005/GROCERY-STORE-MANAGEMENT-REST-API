package com.hello.rasikapriya.Services;

import com.hello.rasikapriya.Models.User;
import com.hello.rasikapriya.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> addUsers(List<User> users) {
    List<User> savedUsers = new ArrayList<>();
    for (User user : users) {
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }
        savedUsers.add(userRepository.save(user));
    }
    return savedUsers;
}


    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        userRepository.updateUser(id, updatedUser.getName(), updatedUser.getEmail(), updatedUser.getPassword());
        return updatedUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }

    public Page<User> getUsersWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersSorted(String field) {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<User> getUsersWithPaginationAndSorting(int pageNumber, int pageSize, String field) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field));
        return userRepository.findAll(pageable);
    }
}
