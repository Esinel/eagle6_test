package com.eagle6.testApp.service;

import com.eagle6.testApp.model.User;
import com.eagle6.testApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by stefanos on 06/12/2018.
 */
@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;


    @Cacheable("usersCache")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Cacheable("usersCache")
    public List<User> getAllUsers() {return userRepository.findAll();}

    @Cacheable("usersCache")
    public List<User> getAllUsers(String firstName, String lastName, Long departmentId) {
        return userRepository.findByFirstNameAndLastNameAndDepartmentId(firstName, lastName, departmentId);
    }

    @Cacheable("usersCache")
    public List<User> getAllUsers(String firstName, String lastName){
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }



    public void saveUser(User user) {
        userRepository.save(user);
    }

}
