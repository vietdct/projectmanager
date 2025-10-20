package com.cybersoft.qlsv.api;


import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.service.RoleService;
import com.cybersoft.qlsv.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller

public class UserAPI {
    @Autowired
    private UserService userService;

    @GetMapping("/user-table")
    public String showUserTable(Model model) {
        model.addAttribute("listUserRole", userService.getUserRole());
        return "user-table";
    }

    @GetMapping("/user-table/user-details")
    public String showUserDetails() {
        return "user-details";
    }

    @PostMapping("")
    public String createUser(@RequestBody UserEntity user, Model model) {
       List<UserEntity> listUserEntity = userService.createUser();
        model.addAttribute("listUserEntity", listUserEntity);
        return "user-add";
    }
    
    // @PostMapping("/register")
    // public ResponseEntity<?> registerCustom(@RequestBody UserDTO userDTO) {
    //    if(userService.existByUser(userDTO)){
    //      return new ResponseEntity<>("UserName is Taken", HttpStatus.BAD_REQUEST);
    //    }
    //    UserEntity user = new UserEntity();
    //    user.setUserName(userDTO.getUserName());
    //    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    //     List<UserRoleEntity> uRoleEntity =  userRoleRepository.findById(userDTO.getIdRole())
    //                 .orElseThrow(() -> new RuntimeException("Role not found"));
       
    //    user.setUserRoleEntities(uRoleEntity);

    //    UserEntity savedUser =  userRepository.save(user);

    //    UserDTO uDtoSaved = new UserDTO();
    //    uDtoSaved.setUserName(savedUser.getUserName());
    //    uDtoSaved.setPassword(savedUser.getPassword());
    //    uDtoSaved.setIdRole(savedUser.getRoleEntity().getId());


    //     return new ResponseEntity<>("Register is Successs", HttpStatus.OK);
    // }
    



    
    
    
    
}
