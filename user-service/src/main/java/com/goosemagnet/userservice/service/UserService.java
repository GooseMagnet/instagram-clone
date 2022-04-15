package com.goosemagnet.userservice.service;

import com.goosemagnet.userservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<User> getUsers(Pageable pageable);

    Optional<User> findUserById(long id);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> create(User user);

    Optional<User> update(User user);

    void delete(long id);
}
