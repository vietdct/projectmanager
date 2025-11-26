package com.cybersoft.qlsv.service;

import java.util.List;

import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;


public interface RoleService {

    
    public List<RoleEntity> getAllRole(); // lấy ds role 

    public List<RoleEntity> getRoleName();

    public RoleDTO saveRole(RoleDTO rDto);

    public RoleEntity getOneById ( Long id);

    public boolean deleteRole(Long id);




}
