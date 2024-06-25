package org.CypsoLabs.service;

import org.CypsoLabs.dto.UsersDto;
import org.CypsoLabs.entity.User;

import java.util.List;

public interface UsersService {

     User addUser(UsersDto userDto);
    User updateUser(Long id, UsersDto userDto);
    void deleteUser(Long id);

}
