package com.cybersoft.qlsv.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.UserRepository;
import com.cybersoft.qlsv.service.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getNameUser() {
       List<UserEntity> listNameUser = userRepository.findAll();
       return listNameUser;
    }

    @Override
    public List<UserEntity> getUserRole() {
        List<UserEntity> listUserRole = userRepository.findlistUserRole();
        return listUserRole;
    }

    @Override
    public List<UserEntity> createUser() {
       List<UserEntity> listUser = userRepository.findAll();
       return userRepository.saveAll(listUser);
    }




}
