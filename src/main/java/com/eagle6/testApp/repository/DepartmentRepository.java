package com.eagle6.testApp.repository;

import com.eagle6.testApp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Created by stefanos on 06/12/2018.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByNameIgnoreCase(String name);

}
