package com.cybersoft.qlsv.api;


import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cybersoft.qlsv.converter.UserConverter;
import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.repository.UserRepository;
import com.cybersoft.qlsv.service.RoleService;
import com.cybersoft.qlsv.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user-table")
public class UserAPI {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

// ---------------------------------------------------USER-TABLE------------------------------------------------------
    @GetMapping("")
    public String showUserTable(Model model) {
        model.addAttribute("listUserRole", userService.getUserRole());
        return "user-table";
    }

 

    // @DeleteMapping("/delete/{id}")
    // public String postUserTable(@PathVariable("id") Long id, @ModelAttribute UserDTO uDto, RedirectAttributes ra) {
    //     uDto.setId(id);
    //    userService.deleteUser(uDto.getId());
    //     ra.addFlashAttribute("successMsg", "Delete is Successed !");
    //     return  "redirect:/user-table";
    // }

    @DeleteMapping("/delete/{id}")
    public String postUserTable(@PathVariable("id") Long id, @ModelAttribute UserDTO uDto, RedirectAttributes ra,Authentication authentication) {
        uDto.setId(id);
        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin){
            return "redirect:/404";
        }
        userService.deleteUser(uDto.getId());
        ra.addFlashAttribute("successMsg", "Delete is Successed !");
        return  "redirect:/user-table";
    }
//--------------------------------------------------USER-DETAILS------------------------------------------
    @GetMapping("/user-details")
    public String showUserDetails() {
        return "user-details";
    }


//--------------------------------------------------USER-EDIT------------------------------------------



}
