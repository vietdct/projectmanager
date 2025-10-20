package com.cybersoft.qlsv.entity;

import org.springframework.scheduling.config.Task;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "assign_task")
public class AssignTaskEntity {
   @EmbeddedId
    private AssignTaskId id;

    // Nói rằng: thành phần userId trong id sẽ *lấy từ* khóa của User
    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    // Tương tự cho taskId
    @MapsId("idTask")
    @ManyToOne
    @JoinColumn(name = "id_task")
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusEntity status; // cột phụ thêm


}
