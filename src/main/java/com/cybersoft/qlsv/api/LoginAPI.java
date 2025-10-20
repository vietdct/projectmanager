package com.cybersoft.qlsv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/login")
public class LoginAPI {
    @Autowired
    private AuthenticationManager authenManager;

    @GetMapping("")
    public String showLogin() {
        
        return "login";
    }

    @PostMapping("")
    public String PostLogin(@RequestParam String userName, @RequestParam String password) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
                authenManager.authenticate(token);
        return "redirect:/index";
    }
 
    

    
    
}
