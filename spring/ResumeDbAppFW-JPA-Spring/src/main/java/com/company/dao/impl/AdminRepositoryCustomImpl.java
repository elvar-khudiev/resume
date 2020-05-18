/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.Admin;
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
public class AdminRepositoryCustomImpl implements AdminRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Admin> getAll() {
        String jpql = "select a from Admin a";
        Query query = em.createQuery(jpql, Admin.class);
        List<Admin> list = query.getResultList();

        return list;
    }

    // CriteriaBuilder
//    @Override
//    public Admin getByEmail(String email) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Admin> q1 = cb.createQuery(Admin.class);
//        
//        Root<Admin> postRoot = q1.from(Admin.class);
//        CriteriaQuery<Admin> q2 = q1
//                .where(cb.equal(postRoot.get("email"), email));
//        
//        Query query = em.createQuery(q2);
//        
//        List<Admin> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        
//        return null;
//    }
    
//     JPQL
//    @Override
//    public Admin getByEmail(String email) {
//        String jpql = "select a from Admin a where a.email = :e";
//        
//        Query query = em.createQuery(jpql, Admin.class);
//        query.setParameter("e", email);
//      
//        List<Admin> list = query.getResultList();
//        
//        if(list.size() == 1) {
//            return list.get(0);
//        }
//        
//        return null;
//    }
    
//     NamedQuery
//    @Override
//    public Admin getByEmail(String email) {
//        Query query = em.createNamedQuery("Admin.findByEmail", Admin.class);
//        query.setParameter("email", email);
//        
//        List<Admin> list = query.getResultList();
//        
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//        
//        return null;
//    }
    
//     Native SQL
    @Override
    public Admin getByEmail(String email) {
        Query query = em.createNativeQuery("select * from admin where email = ?", Admin.class);
        query.setParameter(1, email);
        
        List<Admin> list = query.getResultList();
        
        if (list.size() == 1) {
            return list.get(0);
        }
        
        return null;
    }

    @Override
    public Admin getById(int adminId) {
        Admin admin = em.find(Admin.class, adminId);

        return admin;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean add(Admin admin) {
        admin.setPassword(crypt.hashToString(4, admin.getPassword().toCharArray()));
        em.persist(admin);

        return true;
    }

    @Override
    public boolean delete(int adminId) {
        Admin admin = em.find(Admin.class, adminId);
        em.remove(admin);

        return true;
    }
}
