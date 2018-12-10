package com.eagle6.testApp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefanos on 06/12/2018.
 */
@Entity
public class Department implements Serializable{

    @Id
    @GeneratedValue
    private Long _id;
    private String name;
    private String description;


    public Department() {
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasNameAndDescription() {
        if (this.getName() != null && this.getDescription() != null){
            return true;
        }else{
            return false;
        }
    }

}
