/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.UserSkill;
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
public class UserSkillRepositoryCustomImpl implements UserSkillRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<UserSkill> getAllUserSkill() {
        String jpql = "select u from UserSkill u";
        Query query = em.createQuery(jpql, UserSkill.class);
        return query.getResultList();
    }

    @Override
    public List<UserSkill> getAllUserSkillByUserId(int userId) {
        Query query = em.createNamedQuery("UserSkill.findByUserId", UserSkill.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public UserSkill getById(int userId) {
        return em.find(UserSkill.class, userId);
    }

    @Override
    public boolean add(UserSkill userSkill) {
        Query query = em.createNativeQuery("INSERT INTO user_skill (user_id, skill_id, power) VALUES (?, ?, ?);");
        query.setParameter(1, userSkill.getUserId().getId());
        query.setParameter(2, userSkill.getSkillId().getId());
        query.setParameter(3, userSkill.getPower());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(UserSkill userSkill) {
        em.merge(userSkill);
        return true;
    }

    @Override
    public boolean deleteByObject(UserSkill userSkill) {
        em.remove(em.find(UserSkill.class, userSkill.getId()));
        return true;
    }

    @Override
    public boolean delete(int id) {
        em.remove(em.find(UserSkill.class, id));
        return true;
    }
}
