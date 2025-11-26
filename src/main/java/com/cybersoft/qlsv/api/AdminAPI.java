package com.cybersoft.qlsv.api;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.cybersoft.qlsv.converter.RoleConverter;
import com.cybersoft.qlsv.customer.DuplicateFieldException;
import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.repository.UserRepository;
import com.cybersoft.qlsv.service.ProjectService;
import com.cybersoft.qlsv.service.RoleService;
import com.cybersoft.qlsv.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/admin")
public class AdminAPI {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final RoleConverter roleConverter;

   @Autowired
   private RoleService roleService;

   @Autowired
   private ProjectService projectService;

   @Autowired
   private UserService userService;

    AdminAPI(RoleConverter roleConverter, UserRepository userRepository, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

//-----------------------------------------------TASK ENTITY ---------------------------------------------
    @GetMapping("/task-add")
       public String showTaskAdd(Model model) {
        try {
         
            model.addAttribute("listNameProjects", projectService.getAllProject());
            model.addAttribute("listNameUsers", userService.getAllUsers());
        } catch (Exception e) {
             e.printStackTrace();
            model.addAttribute("rolesError", e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        
        return "admin/task-add";
    }

    //-----------------------------------------------USER ENTITY ---------------------------------------------
     @GetMapping("/user-add")
    public String showUserAdd(Model model) {
        try {
            var roles = roleService.getAllRole();
            model.addAttribute("roles", roles);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("rolesError", e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return "admin/user-add";
    }
    @PostMapping("/user-add")
    public String createNewUser(@ModelAttribute UserDTO uDto,RedirectAttributes ra,BindingResult br,Model model) {
       try {
         userService.save(uDto);
         ra.addFlashAttribute("successMsg", "Add User is SUCCESSED!");
         return "redirect:/admin/user-add";
       } catch (DuplicateFieldException e) {
        boolean hasError = false;
        if(userRepository.existsByUserName(uDto.getUserName()) || uDto.getUserName()== null || uDto.getUserName().isBlank()){
            model.addAttribute("userNameError", "UserName is Already Exists or NULL");
            hasError = true;
        }
        if(userRepository.existsByemail(uDto.getEmail()) || uDto.getEmail() == null || uDto.getEmail().isBlank()){
            model.addAttribute("emailError", "Email is Already Exists or NULL");
            hasError = true;
        }
        if(uDto.getPassword() == null || uDto.getPassword().isBlank()){
            model.addAttribute("passwordError", "Password is Null");
            hasError = true;
        }

        if(hasError){
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("user", uDto);
        }
        
       }
       return "admin/user-add";
    }

        @GetMapping("/user-edit/{id}")
    public String showUserEdit(@PathVariable("id") Long id, Model model) {
        UserEntity user = userService.getAllUser(id);
        List<RoleEntity> listRole = roleService.getRoleName();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        
        return "admin/user-edit";
    }
        @PostMapping("/user-edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute UserDTO userDTO,RedirectAttributes ra) {
         userDTO.setId(id);
         userService.save(userDTO);
         ra.addFlashAttribute("successMsg", "Add User is SUCCESSED!");
        return "redirect:/user-table";
    }


//-----------------------------------------------ROLE ENTITY ---------------------------------------------
    @GetMapping("/role-add")
       public String showRoleAdd() {
        return "admin/role-add";
    }

    @PostMapping("/role-add")
    public String createRole(@ModelAttribute RoleDTO rDto ,RedirectAttributes ra) {
         if(roleRepository.existsByName(rDto.getRoleName())){
            ra.addFlashAttribute("errorMsg", "Role name already exists: "+ rDto.getRoleName());
            return "redirect:/admin/role-add";
         }
        try {
            roleService.saveRole(rDto);
            ra.addFlashAttribute("successMsg", "Add Role is SUCCESSED!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", e.getMessage());
           }
        return "redirect:/admin/role-add";
    }

    //-----------------------------------------------GROUPWORK ENTITY ---------------------------------------------
    @GetMapping("/groupwork-add")
       public String showGroupworkAdd() {
        return "admin/groupwork-add";
    }

    //  ------------------------------------Role-Edit----------------------------------------
    @GetMapping("/role-edit/{id}")
    public String getShowRoleEdit(@PathVariable("id") Long id, Model model , Authentication authentication, RedirectAttributes ra) {
        RoleEntity roleEntity = roleService.getOneById(id);
        List<RoleEntity> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        model.addAttribute("role", roleEntity);
        boolean isADMIN = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(!isADMIN){
            ra.addFlashAttribute("errorMsg", "lỗi chưa cấp quyền");
            return "redirect:/404";
        }
  
        return "admin/role-edit";
    }

    @PostMapping("/role-edit/{id}")
    public String postMethodName(@PathVariable("id") Long id, @ModelAttribute RoleDTO rDto,RedirectAttributes ra , Authentication authentication) {
        rDto.setRoleId(id);
        boolean isADMIN = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(!isADMIN){
            ra.addFlashAttribute("errorMsg", "You can't accessed Because permission is "+rDto.getRoleName());
            return "redirect:/404";
        }        
        try {
            rDto = roleService.saveRole(rDto);
            ra.addFlashAttribute("successMsg","Edit is Successed");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Can't accessed Because permission is not ADMIN"+ e.getMessage());
        }
         
        return "redirect:/role-table";
    }

//----------------------------------------------GROUPWORK-DERTAILS-------------------------------------------------

    
}
