package com.eagle6.testApp.repository;

import com.eagle6.testApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.data.domain.Example;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by stefanos on 06/12/2018.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u JOIN u.department AS d WHERE (:firstName IS null OR LOWER(u.firstName) LIKE :firstName) AND (:lastName IS null OR LOWER(u.lastName) LIKE :lastName) AND (:departmentId IS null OR d._id = :departmentId)")
    List<User> findByFirstNameAndLastNameAndDepartmentId(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("departmentId") Long departmentId);

    @Query("SELECT u from User u WHERE (?1 IS null OR LOWER(u.firstName) LIKE ?1) AND (?2 IS null OR LOWER(u.lastName) LIKE ?2)")
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

}
