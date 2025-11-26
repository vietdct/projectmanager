package com.cybersoft.qlsv.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cybersoft.qlsv.entity.ProjectEntity;
import com.cybersoft.qlsv.repository.ProjectRepository;
import com.cybersoft.qlsv.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectEntity> getAllProject() {
        List<ProjectEntity> listProject= projectRepository.findAll(); 
        return listProject;
    }


}
