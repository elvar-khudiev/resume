/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SkillRepositoryCustom {

    public List<Skill> getAll();

    public boolean add(Skill skill);

    public boolean delete(int id);
}
