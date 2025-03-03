package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserById(Long id);

    @Modifying
    @Transactional
    @Query("INSERT INTO User(name, email, password) VALUES (:name, :email, :password)")
    void createUser(String name, String email, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password WHERE u.id = :id")
    void updateUser(Long id, String name, String email, String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteUserById(Long id);

    Page<User> findAll(Pageable pageable);
}
