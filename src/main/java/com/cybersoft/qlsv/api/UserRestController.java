package com.cybersoft.qlsv.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAllUsers().stream()
                .map(this::toDto)
                .toList();
    }

    private UserDTO toDto(UserEntity u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setUserName(u.getUserName());
        dto.setEmail(u.getEmail());
        dto.setPhone(u.getPhone());
        dto.setFirstName(u.getFirstName());
        dto.setLastName(u.getLastName());

        if (u.getRole() != null) {
            dto.setRoleId(u.getRole().getId());
            dto.setRoleName(u.getRole().getName());
        }
        // KHÔNG set password khi trả về
        return dto;
    }
}
