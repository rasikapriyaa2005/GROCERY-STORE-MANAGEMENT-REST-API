package com.hello.rasikapriya.Repositories;

import com.hello.rasikapriya.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @NonNull
    @Override
    Page<User> findAll(@NonNull Pageable pageable);
}
