/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.User;
import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserSkillRepositoryCustom {
    
    public List<UserSkill> getAllUserSkill();
    
    public boolean add(UserSkill userSkill);
    
    public boolean update(UserSkill userSkill);
    
    public boolean delete(int id);
}
