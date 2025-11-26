package com.cybersoft.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cybersoft.qlsv.entity.UserEntity;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
 

   boolean existsByUserName(String userName);
   UserEntity findByUserName(String userName);


            @Query("""
               SELECT u
               FROM UserEntity u
               JOIN FETCH u.role r
               
            """)
            public List<UserEntity> getAllUser_Role();
   
   UserEntity findOneById(Long id);

   UserEntity findRoleNameById(Long id);

   Boolean existsByemail (String email);

   UserEntity findByEmail(String email);

  @Query("select u.userName from UserEntity u where u.role.id = :roleId order by u.id asc")
    List<String> findUserNamesByRoleId(@Param("roleId") Long roleId);
   
    long countByRole_Id(Long roleId);

}

