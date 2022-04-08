package com.goosemagnet.usersservice.dao;

import com.goosemagnet.usersservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDao {

    Page<User> getUsers(Pageable pageable);

    Optional<User> findUserById(long id);

    Optional<User> findUserByEmail(String email);

    void create(User user);

    void update(User user);

    void delete(long id);
}
