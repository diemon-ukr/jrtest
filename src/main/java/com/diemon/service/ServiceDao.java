package com.diemon.service;

import com.diemon.entity.User;
import com.diemon.entity.UserDto;

import java.util.List;

/**
 * Created by Diemon on 6/6/2017.
 */
public interface ServiceDao {

    public boolean save(Object obj);

    public boolean delete(Object obj);

    public boolean truncateDb();

    public User getUserById(Integer id);

    public List<User> getUsers();

    public List<UserDto> getUserQueryByIsAdmin(Boolean isAdmin);
}