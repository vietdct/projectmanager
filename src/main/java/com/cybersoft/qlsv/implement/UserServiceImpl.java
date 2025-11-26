package com.cybersoft.qlsv.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cybersoft.qlsv.converter.UserConverter;
import com.cybersoft.qlsv.customer.DuplicateFieldException;
import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.repository.UserRepository;
import com.cybersoft.qlsv.service.UserService;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public List<UserEntity> getAllUsers() {     //lấy list User
       List<UserEntity> listNameUser = userRepository.findAll();
       return listNameUser;
    }

    @Override
    public List<UserEntity> getUserRole() {     //Lấy list User và Role
        List<UserEntity> listUserRole = userRepository.getAllUser_Role();
        return listUserRole;
    }

    

    @Override
    public UserEntity getAllUser(Long id) {
       UserEntity user = userRepository.findOneById(id);
       return user;
    }



    
    @Override
    public UserDTO save(UserDTO uDto) {    //Create , Update new User
        UserEntity user = new UserEntity();
        
        if(uDto.getId() == null){   // => create user
            if(userRepository.existsByUserName(uDto.getUserName()) || uDto.getUserName() == null || uDto.getUserName().isBlank()){
                throw new DuplicateFieldException( "userNameError","UserName is Already Exits or Null ");
            }
            if(userRepository.existsByemail(uDto.getEmail()) ||uDto.getEmail() == null || uDto.getEmail().isBlank()){
                throw new DuplicateFieldException("emailError", "email is Already Exits or Null");
            }
            if(uDto.getPassword() == null || uDto.getPassword().isBlank()){
                throw new DuplicateFieldException("passwordError", "password is null");
            }
            user = userConverter.toEntity(uDto);
        }else{               // => update user
            UserEntity oldUser = userRepository.findOneById(uDto.getId());
            user = userConverter.toEntities(uDto, oldUser);
            
        }
        RoleEntity role = roleRepository.getOneById(uDto.getRoleId());
        user.setPassword(passwordEncoder.encode(uDto.getPassword()));
        user.setRole(role);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

        @Override
        @Transactional
        public boolean deleteUser(Long id) {

            UserEntity userEntity = userRepository.findOneById(id);
            if(!userRepository.existsById(userEntity.getId())){
                return false;
            }
            userRepository.deleteById(userEntity.getId());
                return true;
            }
            
        }

    //     @Override
    // public UserDTO save(UserDTO uDto) {
    //     RoleEntity role = roleRepository.getOneById(uDto.getRoleId());
    //     UserEntity user = userConverter.toEntity(uDto);
    //     user.setRole(role);
    //     user = userRepository.save(user);
    //     return userConverter.toUserDTO(user);
    // }
   
