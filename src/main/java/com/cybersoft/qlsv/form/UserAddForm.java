package com.cybersoft.qlsv.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class UserAddForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int roleName;

}
