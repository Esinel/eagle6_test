package com.eagle6.testApp.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stefanos on 06/12/2018.
 */
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue
    private Long _id;
    private String firstName;
    private String lastName;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "department_id")
    private Department department;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean hasDepartment(){
        if (this.getDepartment() != null) {
            return true;
        }else{
            return false;
        }
    }

    public boolean hasDepartmentWithFields(){
        if (this.getDepartment().getName() != null && this.getDepartment().getDescription() !=null) {
            return true;
        }else{
            return false;
        }
    }

    public boolean hasFirstAndLastName() {
        if (this.getFirstName() != null && this.getLastName() != null) {
            return true;
        }else{
            return false;
        }
    }
}
