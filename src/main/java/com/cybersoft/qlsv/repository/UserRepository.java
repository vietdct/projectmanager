package com.cybersoft.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybersoft.qlsv.dto.UserDTO;
import com.cybersoft.qlsv.entity.UserEntity;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
 

   boolean existsByUserName(String userName);
   UserEntity findByUserName(String userName);

    @Query(value = """
                   select u 
                   from UserEntity u 
                   join fetch u.role  
         """)
         List<UserEntity> findlistUserRole ();
   

}

