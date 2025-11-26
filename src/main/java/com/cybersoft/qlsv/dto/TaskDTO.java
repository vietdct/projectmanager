package com.cybersoft.qlsv.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String name;
    private String projectName;
    private String LastName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String statusName;

}
