package com.home.service;

import com.home.entity.User;

import java.util.List;

/**
 * Created by Nazar on 25.06.2017.
 */
public interface UserService {

    User addUser(User user);

    void delete(int id);

    User getById(int id);

    User editUser(User user);

    List<User> getAll();

    List<User> getOrderedUsers();

}
