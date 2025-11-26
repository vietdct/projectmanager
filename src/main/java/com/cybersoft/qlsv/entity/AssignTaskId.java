package com.cybersoft.qlsv.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignTaskId implements Serializable {
    private Long user;
    private Long task;


}
