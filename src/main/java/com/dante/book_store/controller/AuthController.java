package com.dante.book_store.controller;

import com.dante.book_store.config.UserPrincipal;
import com.dante.book_store.config.auth.TokenProvider;
import com.dante.book_store.dto.UserDto;
import com.dante.book_store.entity.Role;
import com.dante.book_store.entity.User;
import com.dante.book_store.enums.ERole;
import com.dante.book_store.repository.RoleRepository;
import com.dante.book_store.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    public AuthController(
            AuthenticationManager _authenticationManager,
            UserRepository _userRepository,
            RoleRepository _roleRepository,
            PasswordEncoder _passwordEncoder,
            TokenProvider _tokenProvider
    ) {
        this.authenticationManager = _authenticationManager;
        this.userRepository = _userRepository;
        this.roleRepository = _roleRepository;
        this.passwordEncoder = _passwordEncoder;
        this.tokenProvider = _tokenProvider;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody UserDto userDto) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        return tokenProvider.generateToken((UserPrincipal) authentication.getPrincipal());
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        for (Long roleId : userDto.getRoleIds()) {
            Role userRole = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }
        user.setRoles(roles);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "User registered successfully!";
    }
}
