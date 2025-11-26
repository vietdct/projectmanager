package com.cybersoft.qlsv.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.cybersoft.qlsv.converter.RoleConverter;
import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleConverter roleConverter;
    @Autowired
    private RoleRepository roleRepository;


    RoleServiceImpl(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

// Lấy danh sách role
    @Override           
    public List<RoleEntity> getAllRole() {
        List<RoleEntity> listRole = roleRepository.findAll();
        return listRole;
    }

    @Override
    public List<RoleEntity> getRoleName() {
        List<RoleEntity> listRoleName = roleRepository.getAllRole();
        return listRoleName;
    }

    @Override
    public RoleDTO saveRole(RoleDTO rDto) {
        RoleEntity roleEntity = new RoleEntity();
        if(rDto.getRoleId() == null){
            roleEntity = roleConverter.toRoleEntity(rDto);
        }else{
            RoleEntity oldRole = roleRepository.findOneById(rDto.getRoleId());
            roleEntity = roleConverter.toRoleEntites(rDto,oldRole);
        }
        roleEntity = roleRepository.save(roleEntity);
        return roleConverter.toRoleDTO(roleEntity);
    }

    

    @Override
    public RoleEntity getOneById(Long id) {
      return roleRepository.findOneById(id);
    }


    @Override
    public boolean deleteRole(Long id) {
        RoleEntity role = roleRepository.findOneById(id);
        if(!roleRepository.existsById(role.getId())){
            return false;
        }
        roleRepository.deleteById(role.getId());
       return true;
    }
    
}
