package com.cybersoft.qlsv.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.cybersoft.qlsv.entity.TaskEntity;
import com.cybersoft.qlsv.service.TaskService;


@Controller
@RequestMapping("/task")
public class TaskAPI {
    @Autowired
    private TaskService taskService;
    
    @GetMapping("")
    public String showTask(Model model) {
        model.addAttribute("listTask", taskService.getTaskUserProject());
        return "task";
    }


    
    
}
