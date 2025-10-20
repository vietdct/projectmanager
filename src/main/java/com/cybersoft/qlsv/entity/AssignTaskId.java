package com.cybersoft.qlsv.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AssignTaskId implements Serializable {
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "id_task")
    private Long idTask;

}
