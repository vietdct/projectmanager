package com.cybersoft.qlsv.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.service.ProjectService;
import com.cybersoft.qlsv.service.RoleService;
import com.cybersoft.qlsv.service.UserService;




@Controller
@RequestMapping("/admin")
public class AdminAPI {


   @Autowired
   private RoleService roleService;

   @Autowired
   private ProjectService projectService;

   @Autowired
   private UserService userService;


    @GetMapping("/task-add")
       public String showTaskAdd(Model model) {
        try {
         
            model.addAttribute("listprojects", projectService.getAllProject());
            model.addAttribute("listNameUser", userService.getNameUser());
        } catch (Exception e) {
             e.printStackTrace();
            model.addAttribute("rolesError", e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        return "admin/task-add";
    }

    // @GetMapping("/user-add")
    // public String showUserAdd(Model model) {

    //     model.addAttribute("roles", roleService.getAllRole());
    //     return "admin/user-add";
    // }
     @GetMapping("/user-add")
    public String showUserAdd(Model model) {
        try {
            var roles = roleService.getAllRole();
            System.out.println("roles size = " + roles.size());
            model.addAttribute("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("rolesError", e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return "admin/user-add";
    }



    

    @GetMapping("/role-add")
       public String showRoleAdd() {
        return "admin/role-add";
    }

    @GetMapping("/groupwork-add")
       public String showGroupworkAdd() {
        return "admin/groupwork-add";
    }
}
