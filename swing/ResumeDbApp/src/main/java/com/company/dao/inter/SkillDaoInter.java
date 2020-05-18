/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Skill;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SkillDaoInter {

    public List<Skill> getAll();
    
    public List<String> getAllName();

    public Skill getById(ResultSet rs);

    public boolean insert(Skill skill);

    public boolean delete(int id);
}
