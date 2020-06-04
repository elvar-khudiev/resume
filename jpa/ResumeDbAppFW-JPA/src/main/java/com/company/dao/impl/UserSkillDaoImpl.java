/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.UserSkill;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllUserSkill() {
        EntityManager em = em();

        Query query = em.createQuery("select u from UserSkill u", UserSkill.class);
        List<UserSkill> list = query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<UserSkill> getAllUserSkillByUserId(int userId) {
        EntityManager em = em();

        Query query = em.createNamedQuery("UserSkill.findByUserId", UserSkill.class);
        query.setParameter("userId", userId);
        List<UserSkill> list = query.getResultList();

        em.close();
        return list;
    }

    @Override
    public UserSkill getById(int userId) {
        EntityManager em = em();

        UserSkill userSkill = em.find(UserSkill.class, userId);

        em.close();
        return userSkill;
    }

    @Override
    public boolean add(UserSkill userSkill) {
        EntityManager em = em();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO user_skill (user_id, skill_id, power) VALUES (?, ?, ?);");
        query.setParameter(1, userSkill.getUserId().getId());
        query.setParameter(2, userSkill.getSkillId().getId());
        query.setParameter(3, userSkill.getPower());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean update(UserSkill userSkill) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.merge(userSkill);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean delete(UserSkill userSkill) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(UserSkill.class, userSkill.getId()));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(UserSkill.class, id));
        em.getTransaction().commit();

        em.close();
        return true;
    }
}
