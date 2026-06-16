package com.alli.proyecto3.rest.auth;

import com.alli.proyecto3.logic.entity.auth.AuthenticationService;
import com.alli.proyecto3.logic.entity.auth.JwtService;
import com.alli.proyecto3.logic.entity.user.LoginResponse;
import com.alli.proyecto3.logic.entity.user.User;
import com.alli.proyecto3.logic.entity.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthRestController(AuthenticationService authenticationService,
                              JwtService jwtService,
                              UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody User user) {
        User authenticatedUser = authenticationService.authenticate(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        foundUser.ifPresent(loginResponse::setAuthUser);

        return ResponseEntity.ok(loginResponse);
    }
}