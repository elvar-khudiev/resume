/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.UserRepository;
import com.company.dao.impl.UserRepositoryCustomImpl;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    @Autowired
    private UserRepositoryCustomImpl userDao;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        return userDao.getAll(name, surname, nationalityId);
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
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
    public boolean delete(int userId) {
        return userDao.delete(userId);
    }
}
