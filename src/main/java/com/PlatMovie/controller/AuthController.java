package com.PlatMovie.controller;


import com.PlatMovie.Config.TokenService;
import com.PlatMovie.Mapper.UserMapper;
import com.PlatMovie.controller.request.UserRequest;
import com.PlatMovie.controller.response.LoginResponse;
import com.PlatMovie.controller.response.UserResponse;
import com.PlatMovie.entity.User;
import com.PlatMovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PlatMovie/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){
        User savedUser = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }
    @PostMapping("/loginr")
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest userRequest){
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(userRequest.email(), userRequest.password());
        Authentication authenticate = authenticationManager.authenticate(userAndPass);

        User user = (User) authenticate.getPrincipal();

        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));

    }
}
