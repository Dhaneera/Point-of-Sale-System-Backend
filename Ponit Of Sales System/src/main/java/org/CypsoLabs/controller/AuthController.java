package org.CypsoLabs.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.CypsoLabs.config.security.JwtTokenGenerator;
import org.CypsoLabs.config.security.SecurityConstance;
import org.CypsoLabs.dto.AuthResponseDto;
import org.CypsoLabs.dto.LoginDto;
import org.CypsoLabs.dto.RegisterDto;
import org.CypsoLabs.entity.Role;
import org.CypsoLabs.entity.User;
import org.CypsoLabs.repository.RoleRepository;
import org.CypsoLabs.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenGenerator jwtTokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator=jwtTokenGenerator;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto>login(@RequestBody LoginDto loginDto,HttpServletResponse response){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String accessToken = jwtTokenGenerator.generateAccessToken(authenticate);
        String refreshToken = jwtTokenGenerator.generateRefreshToken(authenticate);

        User user = usersRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());


        Cookie refreshTokenCookie = new Cookie("refreshToken",refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");// Make the cookie available to the entire application
        refreshTokenCookie.setMaxAge((int) SecurityConstance.JWT_REFRESH_EXPIRATION/1000);

        response.addCookie(refreshTokenCookie);


        return new ResponseEntity<>(new AuthResponseDto(accessToken,refreshToken,roles),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (usersRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("User name is taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));  // Encode the password

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Collections.singletonList(role));

        usersRepository.save(user);

        return new ResponseEntity<>("User register Success!", HttpStatus.OK);
    }

}