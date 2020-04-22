/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author HP
 */

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        String jpql = "select u from User u where 1 = 1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += "and u.name= :name ";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += "and u.surname= :surname ";
        }

        if (nationalityId != null) {
            jpql += "and u.nationality.id = :nid ";
        }

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }

        if (nationalityId != null) {
            query.setParameter("name", nationalityId);
        }
        return query.getResultList();
    }

    @Override
    public User getById(int userId) {
        User user = em.find(User.class, userId);
        return user;
    }

    @Override
    public User getByName(String name) {
        Query query = em.createNativeQuery("select * from user where name = ?", User.class);
        query.setParameter(1, name);

        List<User> list = query.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
        query.setParameter(1, email);

        List<User> list = query.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        em.merge(user);
        return true;
    }

    private static BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();

    @Override
    public boolean add(User u) {
        u.setPassword(crypt.encode(u.getPassword()));
        em.persist(u);
        return true;
    }

    @Override
    public boolean delete(int userId) {
        User user = em.find(User.class, userId);
        em.remove(user);
        return true;
    }
}
