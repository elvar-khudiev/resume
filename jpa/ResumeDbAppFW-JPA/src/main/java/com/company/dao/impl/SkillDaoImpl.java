/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author HP
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        return new Skill(rs.getInt("id"), rs.getString("name"));
    }

    @Override
    public List<Skill> getAll() {
        EntityManager em = em();

        String jpql = "select a from Skill a";
        Query query = em.createQuery(jpql, Skill.class);
        List<Skill> list = query.getResultList();

        return list;
    }

    @Override
    public Skill getById(int id) {
        EntityManager em = em();

        Skill skill = em.find(Skill.class, id);

        em.close();
        return skill;
    }

    @Override
    public Skill getByName(String skillName) {
        EntityManager em = em();

        String jpql = "SELECT s FROM Skill s WHERE s.name = :name";
        Query query = em.createQuery(jpql, Skill.class);
        query.setParameter("name", skillName);

        List<Skill> list = query.getResultList();
        em.close();
        
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean add(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO skill (name) VALUES (?);");
        query.setParameter(1, skill.getName());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = em();

        Skill skill = em.find(Skill.class, id);

        em.getTransaction().begin();
        em.remove(skill);
        em.getTransaction().commit();

        em.close();
        return true;
    }
}
