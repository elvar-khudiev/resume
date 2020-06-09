/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

public interface UserRepositoryCustom {

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public List<User> getAll();

    public User getById(int userId);

    public User getByEmail(String email);

    public boolean update(User u);

    public boolean add(User u);

    public boolean delete(int userId);

    public boolean deleteByObject(User user);

    public boolean deleteByEmail(String email);
}
