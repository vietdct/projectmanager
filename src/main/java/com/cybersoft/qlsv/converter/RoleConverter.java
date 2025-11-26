package com.cybersoft.qlsv.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;

@Component
public class RoleConverter {
    


    public RoleDTO toRoleDTO(RoleEntity role){
        RoleDTO rDto = new RoleDTO();
        rDto.setRoleId(role.getId());
        rDto.setRoleName(role.getName());
        rDto.setDescription(role.getDescription());
        return rDto;
    }

    public RoleEntity toRoleEntity(RoleDTO rDto){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(rDto.getRoleId());
        roleEntity.setName(rDto.getRoleName());
        roleEntity.setDescription(rDto.getDescription());
        return roleEntity;
    }

        public RoleEntity toRoleEntites(RoleDTO rDto,RoleEntity roles){
        roles.setName(rDto.getRoleName());
        roles.setDescription(rDto.getDescription());
        return roles;
    }
}
