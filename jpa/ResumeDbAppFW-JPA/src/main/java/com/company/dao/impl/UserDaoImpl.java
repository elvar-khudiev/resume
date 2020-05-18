/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
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
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

//     JPQL
//    @Override
//    public User getByEmail(String email) {
//        String jpql = "select a from User a where a.email = :e";
//
//        Query query = em.createQuery(jpql, User.class);
//        query.setParameter("e", email);
//
//        List<User> list = query.getResultList();
//
//        if(list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }

//     NamedQuery
//    @Override
//    public User getByEmail(String email) {
//        Query query = em.createNamedQuery("User.findByEmail", User.class);
//        query.setParameter("email", email);
//
//        List<User> list = query.getResultList();
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
//        Query query = em.createNativeQuery("select * from user where email = ?", User.class);
//        query.setParameter(1, email);
//
//        List<User> list = query.getResultList();
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

    @Override
    public boolean add(User user) {
        EntityManager em = em();

        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO user (name, surname, email, phone, profile_description, address, birthdate, birthplace_id, nationality_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getSurname());
        query.setParameter(3, user.getEmail());
        query.setParameter(4, user.getPhone());
        query.setParameter(5, user.getProfileDescription());
        query.setParameter(6, user.getAddress());
        query.setParameter(7, user.getBirthDate());
        query.setParameter(8, user.getBirthPlace().getId());
        query.setParameter(9, user.getNationality().getId());
        query.executeUpdate();

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
