package com.application.blog.controller;

import com.application.blog.dto.JwtAuthResponse;
import com.application.blog.dto.SignInDTO;
import com.application.blog.dto.SignUpDTO;
import com.application.blog.entity.Role;
import com.application.blog.entity.User;
import com.application.blog.repository.RoleRepository;
import com.application.blog.repository.UserRepository;
import com.application.blog.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/auth/")
@Api(value = "Authorization Controller to SignUp / SignIn")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signIn")
    @ApiOperation(value = "SignIn Rest end point", notes = "Login / SignIn page to authorize users")
    public ResponseEntity<JwtAuthResponse> signInAuth(@RequestBody SignInDTO signDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signDTO.getUserNameEmail(), signDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // Get Token from provider class
        String token = jwtTokenProvider.generateToken(authenticate);

        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    @PostMapping("/signUp")
    @ApiOperation(value = "SignUp Rest end point", notes = "Register / SignUp page to create a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Creation successful"),
            @ApiResponse(code = 400, message = "User exists already")
    })
    public ResponseEntity<?> signUpAuth(@RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsByUserName(signUpDTO.getUserName())) {
            return new ResponseEntity<>("UserName is already taken", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmailId(signUpDTO.getEmailId())) {
            return new ResponseEntity<>("EmailId is already taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUserName(signUpDTO.getUserName());
        user.setEmailId(signUpDTO.getEmailId());
        user.setName(signUpDTO.getName());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(role));

        userRepository.save(user);
        return new ResponseEntity<>("Sign Up Successful", HttpStatus.CREATED);
    }
}
