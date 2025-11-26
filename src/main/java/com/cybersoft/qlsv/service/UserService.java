package com.cybersoft.qlsv.service;

import java.util.List;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;

public interface UserService {
    public List<UserEntity> getAllUsers();  //lấy list user

    public List<UserEntity> getUserRole();

    public UserEntity getAllUser (Long id);

    public UserDTO save (UserDTO uDto); // create , update user

    public boolean deleteUser (Long id);
}
