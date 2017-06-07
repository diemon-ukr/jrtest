package com.diemon.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Diemon on 6/6/2017.
 */
@Entity
public class UserDto {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private Boolean isAdmin;

    public UserDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
