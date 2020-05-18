/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserSkillDaoInter {
    
    public List<UserSkill> getAllUserSkill();
    
    public List<UserSkill> getAllSkillByUserId(int userId);

    public UserSkill getById(int userId);

    public boolean add(UserSkill userSkill);
    
    public boolean update(UserSkill userSkill);
    
    public boolean delete(int id);
}
