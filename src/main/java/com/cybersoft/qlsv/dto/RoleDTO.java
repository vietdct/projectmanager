package com.cybersoft.qlsv.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class RoleDTO {
    private String roleName;
    private Long roleId;
    private String description;
}
