package com.cybersoft.qlsv.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.service.RoleService;
import com.cybersoft.qlsv.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleRestController {
private final RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAll() {
        return roleService.getAllRole().stream()
                .map(this::toDto)
                .toList();
    }
    private RoleDTO toDto(RoleEntity r) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleName(r.getName());
        dto.setDescription(r.getDescription());
        dto.setRoleId(r.getId());

        return dto;
    }
}
