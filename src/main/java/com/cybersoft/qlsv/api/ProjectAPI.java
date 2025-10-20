package com.cybersoft.qlsv.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.qlsv.service.ProjectService;

import org.hibernate.mapping.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/groupwork")
public class ProjectAPI {
    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String showGr(Model model){
        model.addAttribute("listProject", projectService.getAllProjectList());

        return "groupwork";
    }



   
}
