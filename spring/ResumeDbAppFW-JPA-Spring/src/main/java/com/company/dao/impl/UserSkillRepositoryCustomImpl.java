/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.UserSkill;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserSkillRepositoryCustomImpl implements UserSkillRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<UserSkill> getAllUserSkill() {
        String jpql = "select u from UserSkill u";
        Query query = em.createQuery(jpql, UserSkill.class);
        List<UserSkill> list = query.getResultList();

        return list;
    }

    @Override
    public boolean add(UserSkill userSkill) {
        em.merge(userSkill);
        return true;
    }

    @Override
    public boolean update(UserSkill userSkill) {
        em.merge(userSkill);
        return true;
    }

    @Override
    public boolean delete(int id) {
        UserSkill userSkill = em.find(UserSkill.class, id);
        em.remove(userSkill);

        return true;
    }
}
