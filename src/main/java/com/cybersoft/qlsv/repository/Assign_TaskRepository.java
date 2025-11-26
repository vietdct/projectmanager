package com.cybersoft.qlsv.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cybersoft.qlsv.dto.AssignTask;
import com.cybersoft.qlsv.entity.AssignTaskId;
import com.cybersoft.qlsv.entity.Assign_Task;
import java.util.List;
import java.util.Optional;


public interface Assign_TaskRepository  extends JpaRepository<Assign_Task,AssignTaskId>{
    List<AssignTask> findByUser_Id(Long userId);

    // thêm các method hay dùng:
    List<AssignTask> findByTask_Id(Long taskId);
    Optional<AssignTask> findByUser_IdAndTask_Id(Long userId, Long taskId);

    // nếu muốn load kèm 3 đầu để tránh Lazy:
    @EntityGraph(attributePaths = {"user","task","status"})
    List<AssignTask> findAllByUser_Id(Long userId);

}
