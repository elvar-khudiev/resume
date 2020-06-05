/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import com.company.main.Context;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author HP
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    
    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        EntityManager em = em();

        String jpql = "select u from User u where 1 = 1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += "and u.name= :name ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += "and u.surname= :surname ";
        }
        if (nationalityId != null) {
            jpql += "and u.nationalityId.id= :nid";
        }
        jpql = jpql + " ORDER BY id ASC";

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }
        if (nationalityId != null && nationalityId != 0) {
            query.setParameter("nid", nationalityId);
        }
        return query.getResultList();
    }

    @Override
    public List<User> getAll() {
        EntityManager em = em();
        List<User> list = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.close();
        return list;
    }
    
    @Override
    public User getById(int userId) {
        EntityManager em = em();

        User user = em.find(User.class, userId);

        em.close();
        return user;
    }

    // CriteriaBuilder
    @Override
    public User getByEmail(String email) {
        EntityManager em = em();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);

        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1
                .where(cb.equal(postRoot.get("email"), email));

        Query query = em.createQuery(q2);

        List<User> list = query.getResultList();
        em.close();
        
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }
//     JPQL
//    @Override
//    public User getByEmail(String email) {
//        EntityManager em = em();
//        
//        String jpql = "select a from User a where a.email = :e";
//
//        Query query = em.createQuery(jpql, User.class);
//        query.setParameter("e", email);
//
//        List<User> list = query.getResultList();
//        em.close();
//        
//        if(list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }
//     NamedQuery
//    @Override
//    public User getByEmail(String email) {
//        EntityManager em = em();
//        
//        Query query = em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
//
//        List<User> list = query.getResultList();
//        em.close();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }
    //     Native SQL
//    @Override
//    public User getByEmail(String email) {
//        EntityManager em = em();
//        
//        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
//        query.setParameter(1, email);
//
//        List<User> list = query.getResultList();
//        em.close();
//
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        return null;
//    }
    @Override
    public boolean update(User user) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

        em.close();
        return true;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean add(User user) {
        user.setPassword(crypt.hashToString(4, user.getPassword().toCharArray()));

        EntityManager em = em();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO"
                + "user(name, surname, email, phone, password, profile_description, address,"
                + "birthdate, birthplace_id, nationality_id, authority_id)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getSurname());
        query.setParameter(3, user.getEmail());
        query.setParameter(4, user.getPhone());
        query.setParameter(5, user.getPassword());
        query.setParameter(6, user.getProfileDescription());
        query.setParameter(7, user.getAddress());
        query.setParameter(8, user.getBirthdate());
        query.setParameter(9, user.getBirthplaceId().getId());
        query.setParameter(10, user.getNationalityId().getId());
        query.setParameter(11, user.getAuthorityId().getId());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(User user) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(User.class, user.getId()));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean deleteById(int userId) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(User.class, userId));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean deleteByEmail(String email) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(User.class, getByEmail(email).getId()));
        em.getTransaction().commit();

        em.close();
        return true;
    }
}
