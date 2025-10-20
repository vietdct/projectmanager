package com.cybersoft.qlsv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybersoft.qlsv.implement.RoleServiceImpl;
import com.cybersoft.qlsv.service.RoleService;

import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/role-table")
public class RoleAPI {
    @Autowired
    private RoleService roleService;


    @GetMapping({""})
    public String showRoleTable(Model model) {
        model.addAttribute("listRoleName", roleService.getRoleName());
    return "role-table"; // -> /WEB-INF/views/role-table.jsp
    }


    
    
    
   
    
}
