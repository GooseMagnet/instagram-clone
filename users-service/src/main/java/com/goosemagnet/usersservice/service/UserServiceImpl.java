package com.goosemagnet.usersservice.service;

import com.goosemagnet.usersservice.dao.UserDao;
import com.goosemagnet.usersservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserServiceImpl implements com.goosemagnet.usersservice.service.UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userDao.getUsers(pageable);
    }

    @Override
    public Optional<User> findUserById(long id) {
        if (id < 0) {
            return Optional.empty();
        }
        return userDao.findUserById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> create(User user) {
        hashPasswordAndPersist(user, userDao::create);
        return userDao.findUserByEmail(user.getEmail());
    }

    @Override
    @Transactional
    public Optional<User> update(User user) {
        hashPasswordAndPersist(user, userDao::update);
        return userDao.findUserById(user.getId());
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    private void hashPasswordAndPersist(User user, Consumer<User> dbOperation) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        dbOperation.accept(user.withPassword(encodedPassword));
    }
}
