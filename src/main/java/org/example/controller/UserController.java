package org.example.controller;

import com.github.javafaker.service.RandomService;
import org.example.repository.UserRepository;
import org.example.dto.UserRequestDTO;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    final Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();

    @PostMapping("/create")
    public User create(@RequestBody UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setAccount(dto.getAccount());
        String pass = new RandomService().hex(12);
        user.setLogin(new RandomService().hex(12));
        user.setPassword(encoder.encode(pass));
        user = userRepository.save(user);
        user.setPassword(pass);
        return user;
    }
}
