/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.UserSkill;

import java.util.List;

/**
 *
 * @author HP
 */

public interface UserSkillServiceInter {

    public List<UserSkill> getAll();

    public List<UserSkill> getAllByUserId(int userId);

    public UserSkill getById(int id);

    public boolean update(UserSkill us);

    public boolean add(UserSkill us);

    public boolean deleteByObject(UserSkill us);

    public boolean delete(int id);
}
