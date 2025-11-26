package com.cybersoft.qlsv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
    RoleEntity findByName(String name);

    @Query( value = """
                SELECT r
                FROM RoleEntity r
            """)
    List<RoleEntity> getAllRole(); 

    RoleEntity findOneByName(String name);

    RoleEntity getOneById(Long id);

    RoleEntity findOneById(Long id);

    boolean existsByName (String name);

    

}
