package com.goosemagnet.userservice.controller;

import com.goosemagnet.userservice.model.User;
import com.goosemagnet.userservice.model.dto.UserDto;
import com.goosemagnet.userservice.service.UserService;
import com.goosemagnet.userservice.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDto> getUsers(Pageable pageable) {
        return userService.getUsers(pageable).map(UserDto::new);
    }

    @GetMapping("/{usernameOrId}")
    public User findUserById(@PathVariable("usernameOrId") String usernameOrId) {
        return userService.findUserByUsername(usernameOrId)
                .or(() -> parseInt(usernameOrId).flatMap(userService::findUserById))
                .orElseThrow(() -> new NotFoundException("User [" + usernameOrId + "] not found"));
    }

    @PostMapping
    public User create(@RequestBody UserDto user) {
        return userService.create(fromDto(user))
                .orElseThrow(() -> new RuntimeException("Unable to create user"));
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") long id, @RequestBody UserDto user) {
        return userService.update(fromDto(user).withId(id))
                .orElseThrow(() -> new NotFoundException("User not found with id " + user.getId()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }

    private User fromDto(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getDateCreated(),
                userDto.getAvatarPath(),
                userDto.getIsPrivate(),
                userDto.getDescription()
        );
    }

    private Optional<Long> parseInt(String userId) {
        try {
            return Optional.of(Long.parseLong(userId));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
