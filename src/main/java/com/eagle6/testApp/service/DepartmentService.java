package com.eagle6.testApp.service;

import com.eagle6.testApp.model.Department;
import com.eagle6.testApp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stefanos on 06/12/2018.
 */
@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Cacheable("departmentsCache")
    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    @Cacheable("departmentsCache")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Cacheable("departmentsCache")
    public List<Department> getDepartmentsByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }

    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

}
