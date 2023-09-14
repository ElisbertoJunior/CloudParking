package br.com.elisbjr.cloudparking.controllers;

import br.com.elisbjr.cloudparking.entity.User;
import br.com.elisbjr.cloudparking.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid User user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        if(repository.findByLogin(user.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user.getLogin(), encryptedPassword, user.getRole());

        repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}