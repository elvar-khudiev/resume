/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.UserSkillRepository;
import com.company.entity.UserSkill;
import com.company.service.inter.UserSkillServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
public class UserSkillServiceImpl implements UserSkillServiceInter {

    @Autowired
    private UserSkillRepository userSkillDao;

    @Override
    public List<UserSkill> getAll() {
        return userSkillDao.getAllUserSkill();
    }

    @Override
    public List<UserSkill> getAllByUserId(int userId) {
        return userSkillDao.getAllUserSkillByUserId(userId);
    }

    @Override
    public UserSkill getById(int id) {
        return userSkillDao.getById(id);
    }

    @Override
    public boolean update(UserSkill us) {
        return userSkillDao.update(us);
    }

    @Override
    public boolean add(UserSkill us) {
        return userSkillDao.add(us);
    }

    @Override
    public boolean deleteByObject(UserSkill us) {
        return userSkillDao.deleteByObject(us);
    }

    @Override
    public boolean delete(int id) {
        return userSkillDao.delete(id);
    }
}
