package com.cybersoft.qlsv.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;



    @Override
    public List<RoleEntity> getAllRole() {
        List<RoleEntity> listRole = roleRepository.findAll();
        System.out.println("DB roles size = " + listRole.size());
        return listRole;
    }

    @Override
    public List<RoleEntity> getRole() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRole'");
    }

    @Override
    public List<RoleEntity> getRoleName() {
        List<RoleEntity> listRoleName = roleRepository.getRoleName();
        return listRoleName;
    }
}
