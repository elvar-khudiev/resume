/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.Admin;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");

        Date endDate = null;
        if (rs.getDate("end_date") != null) {
            endDate = rs.getDate("end_date");
        }

        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(id, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        EntityManager em = em();

        String jpql = "select e from EmploymentHistory e";
        Query query = em.createQuery(jpql, EmploymentHistory.class);
        List<EmploymentHistory> list = query.getResultList();

        return list;
    }

    @Override
    public List<EmploymentHistory> getByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(""
                    + "SELECT "
                    + "* "
                    + "FROM "
                    + "employment_history "
                    + "WHERE "
                    + "user_id = ?");

            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result.add(getEmploymentHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public EmploymentHistory getById(int userId) {
        EntityManager em = em();

        EmploymentHistory employmentHistory = em.find(EmploymentHistory.class, userId);

        em.close();
        return employmentHistory;
    }

    @Override
    public boolean add(EmploymentHistory history) {
        EntityManager em = em();

        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO employment_history (header, begin_date, end_date, job_description, user_id) VALUES (?, ?, ?, ?, ?);");
        query.setParameter(1, history.getHeader());
        query.setParameter(2, history.getBeginDate());
        query.setParameter(3, history.getEndDate());
        query.setParameter(4, history.getJobDescription());
        query.setParameter(5, history.getUser().getId());
        query.executeUpdate();

        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean update(EmploymentHistory history) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.merge(history);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean delete(int userId) {
        EntityManager em = em();

        User user = em.find(User.class, userId);

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();

        em.close();
        return true;
    }
}