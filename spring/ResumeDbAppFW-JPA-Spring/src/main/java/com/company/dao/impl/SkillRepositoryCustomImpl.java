/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */
public class SkillRepositoryCustomImpl implements SkillRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Skill> getAll() {
        String jpql = "select a from Skill a";
        Query query = em.createQuery(jpql, Skill.class);
        List<Skill> list = query.getResultList();

        return list;
    }

    @Override
    public boolean add(Skill skill) {
        em.persist(skill);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Skill skill = em.find(Skill.class, id);
        em.remove(skill);

        return true;
    }
}
