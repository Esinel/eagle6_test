package com.eagle6.testApp.controller;


import com.eagle6.testApp.model.User;
import com.eagle6.testApp.service.DepartmentService;
import com.eagle6.testApp.service.UserService;
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
@RequestMapping("/api") // didnt put /api/users for better readability in controller methods
@Api(value = "Users", description = "REST endpoint for Users", tags = {"Users"}) // modifying Swagger UI page
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;


    // GET by id
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // GET users (query params: departmentId & firstName & lastName)
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "firstName", required = false) String firstName ,
            @RequestParam(value = "lastName", required = false) String lastName
    ){
        List<User> users;

        if (departmentId == null && firstName == null && lastName == null) { //no args
            users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            // with args
            if (firstName != null) firstName = firstName.toLowerCase();
            if (lastName != null) lastName = lastName.toLowerCase();

            if (departmentId != null) {
                users = userService.getAllUsers(firstName, lastName, departmentId); // WITH JOIN
            }else{
                users = userService.getAllUsers(firstName, lastName); // NO JOIN
            }


            if (users.size() > 0) {
                return new ResponseEntity<>(users, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
            }

        }
    }


    // CREATE
    @RequestMapping(value = "/users", method = RequestMethod.POST , consumes = "application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){
        //request args passed correctly?
        if (user.hasFirstAndLastName()) {
           if (user.hasDepartment()){
               if (user.hasDepartmentWithFields()){ //lets not save if department params are empty
                   departmentService.saveDepartment(user.getDepartment());
               }else{
                   return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
               }
           }
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);

        }else{
            //args not passed correctly
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


}
