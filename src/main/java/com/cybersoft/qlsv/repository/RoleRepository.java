package com.cybersoft.qlsv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.cybersoft.qlsv.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer>{

    List<RoleEntity> findByName(String name);

    @Query( value = """
                SELECT r
                FROM RoleEntity r
            """)
    List<RoleEntity> getRoleName();
    
}
