package com.cybersoft.qlsv.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.cybersoft.qlsv.dto.TaskDTO;
import com.cybersoft.qlsv.entity.TaskEntity;
import com.cybersoft.qlsv.repository.ProjectRepository;
import com.cybersoft.qlsv.repository.TaskRepository;
import com.cybersoft.qlsv.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDTO> getTaskUserProject() {
        List<TaskDTO> listTask = taskRepository.getTaskUserProject();
       return listTask;
    }



}
