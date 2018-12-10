package com.eagle6.testApp.controller;

import com.eagle6.testApp.model.Department;
import com.eagle6.testApp.service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by stefanos on 06/12/2018.
 */
@RestController
@RequestMapping("/api")
@Api(value = "Departments", description = "REST endpoint for Departments", tags = {"Departments"})
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;


    // GET by Id
    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);

        if ( null != department ) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // GET departments
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> getDepartments(@RequestParam(value = "name", required = false) String name) {

        if (name !=  null) {
            List<Department> departments = departmentService.getDepartmentsByName(name);
            if (departments.size() > 0){
                return new ResponseEntity<>(departments, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(departments, HttpStatus.NOT_FOUND);
            }
        }else{
            List<Department> departments = departmentService.getAllDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }

    }


    // CREATE
    @RequestMapping(value = "/departments", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        if (department.hasNameAndDescription()){
            //add to DB
            departmentService.saveDepartment(department);
            return new ResponseEntity<>(department,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); // no passed arguments -> not creating empty object
        }

    }


}
