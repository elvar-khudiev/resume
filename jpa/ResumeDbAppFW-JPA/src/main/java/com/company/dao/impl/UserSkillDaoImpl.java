/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    @Override
    public List<UserSkill> getAllUserSkill() {
        EntityManager em = em();

        String jpql = "select u from UserSkill u";
        Query query = em.createQuery(jpql, UserSkill.class);
        List<UserSkill> list = query.getResultList();

        return list;
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
//        List<UserSkill> result = new ArrayList<>();
//        try (Connection c = connect()) {
//            PreparedStatement stmt = c.prepareStatement("SELECT "
//                    + "u.*, "
//                    + "us.id as userSkillId, "
//                    + "s.id, "
//                    + "s.name AS skill_name, "
//                    + "us.power "
//                    + "FROM "
//                    + "user_skill us "
//                    + "LEFT JOIN user u ON us.user_id = u.id "
//                    + "LEFT JOIN skill s ON us.skill_id = s.id "
//                    + "WHERE "
//                    + "us.user_id = ?");
//
//            stmt.setInt(1, userId);
//            stmt.execute();
//            ResultSet rs = stmt.getResultSet();
//
//            while (rs.next()) {
//                UserSkill u = getUserSkill(rs);
//                result.add(u);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
        return null;
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
        query.setParameter(1, userSkill.getUser().getId());
        query.setParameter(2, userSkill.getSkill().getId());
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
    public boolean delete(int id) {
        EntityManager em = em();

        UserSkill userSkill = em.find(UserSkill.class, id);

        em.getTransaction().begin();
        em.remove(userSkill);
        em.getTransaction().commit();

        em.close();
        return true;
    }
}
