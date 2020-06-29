/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Skill;

import java.util.List;

/**
 *
 * @author HP
 */

public interface SkillServiceInter {

    public List<Skill> getAll();

    public Skill getById(int id);

    public boolean add(Skill s);

    public boolean update(Skill skill);

    public boolean deleteByObject(Skill s);

    public boolean delete(int id);
}
