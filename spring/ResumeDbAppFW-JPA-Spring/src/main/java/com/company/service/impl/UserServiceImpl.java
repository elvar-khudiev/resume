/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.UserRepository;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    private UserRepository userDao;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname, nationalityId);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean deleteByObject(User user) {
        return userDao.deleteByObject(user);
    }

    @Override
    public boolean delete(int userId) {
        return userDao.delete(userId);
    }
}
