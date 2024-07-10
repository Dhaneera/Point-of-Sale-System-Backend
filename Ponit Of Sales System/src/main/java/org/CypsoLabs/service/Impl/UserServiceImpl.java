package org.CypsoLabs.service.Impl;

import org.CypsoLabs.dto.UsersDto;
import org.CypsoLabs.entity.Role;
import org.CypsoLabs.entity.User;
import org.CypsoLabs.repository.RoleRepository;
import org.CypsoLabs.repository.UsersRepository;
import org.CypsoLabs.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addUser(UsersDto userDto) {
        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            throw new RuntimeException("Role USER not found");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UsersDto userDto) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UsersDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .roles(user.getRoles().stream()
                                .map(role -> roleRepository.findById(role.getId()).orElseThrow(() -> new RuntimeException("Role not found")).getName())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }


}
