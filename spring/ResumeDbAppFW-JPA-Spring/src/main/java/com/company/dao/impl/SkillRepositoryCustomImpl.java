/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */

@Repository
@Transactional
public class SkillRepositoryCustomImpl implements SkillRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Skill> getAll() {
        return em.createQuery("select s from Skill s", Skill.class).getResultList();
    }

    @Override
    public Skill getById(int id) {
        return em.find(Skill.class, id);
    }

    @Override
    public boolean add(Skill s) {
        Query query = em.createNativeQuery("INSERT INTO skill (name) VALUES (?);");
        query.setParameter(1, s.getName());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Skill s) {
        em.merge(s);
        return true;
    }

    @Override
    public boolean deleteByObject(Skill s) {
        em.remove(em.find(Skill.class, s.getId()));
        return true;
    }

    @Override
    public boolean delete(int id) {
        em.remove(em.find(Skill.class, id));
        return true;
    }
}
