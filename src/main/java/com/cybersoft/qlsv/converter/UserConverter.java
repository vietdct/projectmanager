package com.cybersoft.qlsv.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.UserEntity;

@Component
public class UserConverter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity toEntity(UserDTO userDTO){
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

       
        return user;
    }
    public UserDTO toUserDTO(UserEntity userEntity){
        UserDTO udto = new UserDTO();
        if(userEntity.getId() != null ){     
            udto.setId(userEntity.getId());
        }
        
        udto.setUserName(userEntity.getUserName());
        udto.setEmail(userEntity.getEmail());
        // udto.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        udto.setPhone(userEntity.getPhone());
        udto.setFirstName(userEntity.getFirstName());
        udto.setLastName(userEntity.getLastName());
        udto.setRoleName(userEntity.getRole().getName());
        return udto;
    }


        public UserEntity toEntities(UserDTO userDTO, UserEntity user){
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        // user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        
        return user;
    }
}
