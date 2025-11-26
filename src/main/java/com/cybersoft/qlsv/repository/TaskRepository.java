package com.cybersoft.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.qlsv.dto.TaskDTO;
import com.cybersoft.qlsv.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Long> {
       
    
        @Query(value = """
                    Select  new com.cybersoft.qlsv.dto.TaskDTO(t.id, t.name,p.name, u.lastName,p.startDate, p.endDate,st.name)
                    From TaskEntity t
                    Join  t.project p
                    Join  t.status st
                    Join  Assign_Task at on at.task = t
                    join  at.user u  

                """)
            public List<TaskDTO> getTask_User_Project();
    
        
}
