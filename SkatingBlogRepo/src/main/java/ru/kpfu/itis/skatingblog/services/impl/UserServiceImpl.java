package ru.kpfu.itis.skatingblog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.skatingblog.dto.AuthDto;
import ru.kpfu.itis.skatingblog.dto.UserDto;
import ru.kpfu.itis.skatingblog.exceptions.DuplicateEntryException;
import ru.kpfu.itis.skatingblog.exceptions.WrongEmailOrPasswordException;
import ru.kpfu.itis.skatingblog.models.User;
import ru.kpfu.itis.skatingblog.models.enums.Role;
import ru.kpfu.itis.skatingblog.models.enums.Status;
import ru.kpfu.itis.skatingblog.repositories.UserRepository;
import ru.kpfu.itis.skatingblog.security.JwtTokenProvider;
import ru.kpfu.itis.skatingblog.services.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
    /*
    @PersistenceContext
    private EntityManager em;

     */
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);

    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void deleteByEmail(String email) {

    }



    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return Optional.of(user.orElse(new User()));
    }

    @Override
    public void updateUser(String newFirstName, String newLastName, String email) {

    }

    @Transactional
    @Override
    public ResponseEntity registerUser(UserDto userDto) throws DuplicateEntryException {
        Optional<User> userOptional = findUserByEmail(userDto.getEmail());
        if (!userOptional.isPresent()) {
            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setStatus(Status.ACTIVE);
            user.setRole(Role.USER);
            user.setEmail(userDto.getEmail());
            user.setPassword(encodePassword(userDto.getPassword()));
            saveUser(user);
           // return Optional.of(user);
            return new ResponseEntity(HttpStatus.OK);
        } else
            throw new DuplicateEntryException("There is an account with that email address: "
                    + userDto.getEmail());


    }

    @Override
    public void login(AuthDto authDto) throws WrongEmailOrPasswordException {
        /*
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(),
                authDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
        */
        Optional<User> user = findUserByEmail(authDto.getEmail());
        if (user.isPresent()) {
            if (!user.get().getPassword().equals(authDto.getPassword())) {
                throw new WrongEmailOrPasswordException();
            }
        } else throw new WrongEmailOrPasswordException();
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
