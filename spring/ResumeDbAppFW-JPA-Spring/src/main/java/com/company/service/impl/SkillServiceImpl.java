/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.SkillRepository;
import com.company.entity.Skill;
import com.company.service.inter.SkillServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
public class SkillServiceImpl implements SkillServiceInter {

    @Autowired
    private SkillRepository skillDao;

    @Override
    public List<Skill> getAll() {
        return skillDao.getAll();
    }

    @Override
    public Skill getById(int id) {
        return skillDao.getById(id);
    }

    @Override
    public boolean update(Skill s) {
        return skillDao.update(s);
    }

    @Override
    public boolean add(Skill s) {
        return skillDao.add(s);
    }

    @Override
    public boolean deleteByObject(Skill s) {
        return skillDao.deleteByObject(s);
    }

    @Override
    public boolean delete(int id) {
        return skillDao.delete(id);
    }
}
