package com.cybersoft.qlsv.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class IndexAPI {
    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }
    @GetMapping("/blank")
    public String showBlank() {
        return "blank";
    }
    @GetMapping("/404")
    public String show404() {
        return "404";
    }
        @GetMapping("/groupwork-details")
    public String showGrDetails(){
        return "groupwork-details";
    }
}
