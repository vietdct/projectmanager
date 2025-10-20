package com.cybersoft.qlsv.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/profile")
public class ProfileAPI {
    @GetMapping("")
    public String showProfile() {
        return "profile";
    }
    @RequestMapping("/profile-edit")
    public String showProfileEdit() {
        return "profile-edit";
    }
  
    
}
