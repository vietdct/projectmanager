package com.cybersoft.qlsv.service;

import java.util.List;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;

public interface UserService {
    public List<UserEntity> getNameUser();

    public List<UserEntity> getUserRole();

    public List<UserEntity> createUser();
}
