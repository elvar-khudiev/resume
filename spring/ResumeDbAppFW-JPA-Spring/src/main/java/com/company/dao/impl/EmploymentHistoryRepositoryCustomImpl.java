/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
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
public class EmploymentHistoryRepositoryCustomImpl implements EmploymentHistoryRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EmploymentHistory> getAll() {
        return em.createQuery("select e from EmploymentHistory e", EmploymentHistory.class).getResultList();
    }

    @Override
    public EmploymentHistory getById(int id) {
        return em.find(EmploymentHistory.class, id);
    }

    @Override
    public boolean add(EmploymentHistory history) {
        Query query = em.createNativeQuery("INSERT INTO employment_history (header, begin_date, end_date, job_description, user_id) VALUES (?, ?, ?, ?, ?);");
        query.setParameter(1, history.getHeader());
        query.setParameter(2, history.getBeginDate());
        query.setParameter(3, history.getEndDate());
        query.setParameter(4, history.getJobDescription());
        query.setParameter(5, history.getUserId().getId());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(EmploymentHistory history) {
        em.merge(history);
        return true;
    }

    @Override
    public boolean deleteByObject(EmploymentHistory e) {
        em.remove(em.find(EmploymentHistory.class, e.getId()));
        return true;
    }

    @Override
    public boolean delete(int id) {
        EmploymentHistory e = em.find(EmploymentHistory.class, id);
        em.remove(e);
        return true;
    }
}