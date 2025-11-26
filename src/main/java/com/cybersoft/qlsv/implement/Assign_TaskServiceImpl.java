package com.cybersoft.qlsv.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cybersoft.qlsv.entity.Assign_Task;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.repository.Assign_TaskRepository;
import com.cybersoft.qlsv.service.Assign_TaskService;

public class Assign_TaskServiceImpl implements Assign_TaskService {

    @Autowired
    private Assign_TaskRepository assign_TaskRepository;

    @Override
    public List<Assign_Task> getAllUser() {
     return null;
    }

}
