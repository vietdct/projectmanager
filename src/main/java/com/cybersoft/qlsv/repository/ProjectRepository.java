package com.cybersoft.qlsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.qlsv.entity.ProjectEntity;
import com.cybersoft.qlsv.entity.RoleEntity;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
        @Query(value = """
                Select p
                From ProjectEntity p
            """)
        List<ProjectEntity> getAllProject();


}
