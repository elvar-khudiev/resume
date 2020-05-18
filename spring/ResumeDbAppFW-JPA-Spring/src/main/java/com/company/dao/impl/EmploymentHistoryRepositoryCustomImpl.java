/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */

@Transactional
public class EmploymentHistoryRepositoryCustomImpl implements EmploymentHistoryRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<EmploymentHistory> getAll() {
        String jpql = "select e from EmploymentHistory e";
        Query query = em.createQuery(jpql, EmploymentHistory.class);
        List<EmploymentHistory> list = query.getResultList();

        return list;
    }

    @Override
    public boolean add(EmploymentHistory history) {
        em.persist(history);
        return true;
    }

    @Override
    public boolean update(EmploymentHistory history) {
        em.merge(history);
        return true;
    }

    @Override
    public boolean delete(int userId) {
        User user = em.find(User.class, userId);
        em.remove(user);

        return true;
    }
}