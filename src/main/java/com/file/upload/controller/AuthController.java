package com.file.upload.controller;

import com.file.upload.auth.JwtTokenProvoider;
import com.file.upload.model.User;
import com.file.upload.model.request.UserRequest;
import com.file.upload.model.response.UserResponse;
import com.file.upload.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(value = "JWT Api Documentation")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvoider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvoider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login method")
    public String login(@RequestBody UserRequest userRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer" + jwtToken;
    }

    @PostMapping("/register")
    @ApiOperation(value = "User save method")
    public UserResponse save(User user){
        return userService.register(user);
    }

}
