/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    @Override
    public List<EmploymentHistory> getAll() {
        EntityManager em = em();

        Query query = em.createQuery("select e from EmploymentHistory e", EmploymentHistory.class);
        List<EmploymentHistory> list = query.getResultList();

        em.close();
        return list;
    }

    @Override
    public List<EmploymentHistory> getByUserId(int userId) {
        EntityManager em = em();

        Query query = em.createNamedQuery("EmploymentHistory.findByUserId");
        query.setParameter("userId", userId);
        List<EmploymentHistory> list = query.getResultList();

        em.close();
        return list;
    }

    @Override
    public EmploymentHistory getById(int id) {
        EntityManager em = em();

        EmploymentHistory employmentHistory = em.find(EmploymentHistory.class, id);

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
        query.setParameter(5, history.getUserId().getId());
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
    public boolean delete(EmploymentHistory employmentHistory) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(EmploymentHistory.class, employmentHistory.getId()));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(EmploymentHistory.class, id));
        em.getTransaction().commit();

        em.close();
        return true;
    }
}