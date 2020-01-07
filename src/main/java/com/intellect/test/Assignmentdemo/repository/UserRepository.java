package com.intellect.test.Assignmentdemo.repository;

import com.intellect.test.Assignmentdemo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);

    @Query(value = "SELECT * FROM USERINFO WHERE MONTH(birthdate) = MONTH(NOW())",nativeQuery = true)
    List<UserEntity> findBirthdayCurrentMonth();
}
