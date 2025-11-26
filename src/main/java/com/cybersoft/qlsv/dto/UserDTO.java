package com.cybersoft.qlsv.dto;

import com.cybersoft.qlsv.entity.RoleEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String firstName;
    private String lastName;
    private Long  roleId;
    private String roleName;
    
}
