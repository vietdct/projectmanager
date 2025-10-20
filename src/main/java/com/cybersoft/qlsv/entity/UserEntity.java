package com.cybersoft.qlsv.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "username")
	private String userName;

	@Column(name = "email")
	private String email;

    @Column(name = "password")
    private String password;

	@Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

	@Column(name = "phone")
	private String phone;

	@ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

	@OneToMany(mappedBy = "user")
	private Set<AssignTaskEntity> assignTasks;


}