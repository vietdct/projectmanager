package com.cybersoft.qlsv.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybersoft.qlsv.dto.TaskDTO;


public interface TaskService {
    public List<TaskDTO> getTaskUserProject();

}
