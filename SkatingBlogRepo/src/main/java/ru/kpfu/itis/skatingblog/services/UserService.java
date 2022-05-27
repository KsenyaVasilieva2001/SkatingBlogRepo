package ru.kpfu.itis.skatingblog.services;


import org.springframework.http.ResponseEntity;
import ru.kpfu.itis.skatingblog.dto.AuthDto;
import ru.kpfu.itis.skatingblog.dto.UserDto;
import ru.kpfu.itis.skatingblog.exceptions.DuplicateEntryException;
import ru.kpfu.itis.skatingblog.exceptions.WrongEmailOrPasswordException;
import ru.kpfu.itis.skatingblog.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User findUserById(Long id);
    void saveUser(User user);
    void deleteUser();
    void deleteByEmail(String email);
    Optional<User> findUserByEmail(String email);
    void updateUser(String firstName, String lastName, String email);
    ResponseEntity registerUser(UserDto userDto) throws DuplicateEntryException;
    void login(AuthDto authDto) throws WrongEmailOrPasswordException;
}
