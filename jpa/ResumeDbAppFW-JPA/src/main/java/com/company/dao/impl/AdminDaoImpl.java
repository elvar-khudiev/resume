/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.AdminDaoInter;
import com.company.entity.Admin;

import java.sql.*;
import java.util.ArrayList;
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
public class AdminDaoImpl extends AbstractDAO implements AdminDaoInter {

    @Override
    public List<Admin> getAll() {
        EntityManager em = em();

        String jpql = "select a from Admin a";
        Query query = em.createQuery(jpql, Admin.class);
        List<Admin> list = query.getResultList();
      
        return list;

//        List<Admin> result = new ArrayList<>();
//        try (Connection c = connect()) {
//            String sql = "select "
//                    + "u.*, "
//                    + "n.name as nationality, "
//                    + "c.name as birthplace "
//                    + "from "
//                    + "admin "
//                    + "where 1=1 ";
//
//            PreparedStatement stmt = c.prepareStatement(sql);
//
//            stmt.execute();
//            ResultSet rs = stmt.getResultSet();
//
//            while (rs.next()) {
//                Admin admin = getAdmin(rs);
//                result.add(admin);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
    }

    // CriteriaBuilder
//    @Override
//    public Admin getByEmail(String email) {
//        EntityManager em = em();
//
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
//        EntityManager em = em();
//
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
//        EntityManager em = em();
//
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
        EntityManager em = em();

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
        EntityManager em = em();

        Admin admin = em.find(Admin.class, adminId);

        em.close();
        return admin;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean add(Admin admin) {

        EntityManager em = em();
        em.getTransaction().begin();

        admin.setPassword(crypt.hashToString(4, admin.getPassword().toCharArray()));

        Query query = em.createNativeQuery("INSERT INTO admin (name, surname, email, password) VALUES (?, ?, ?, ?);");
        query.setParameter(1, admin.getName());
        query.setParameter(2, admin.getSurname());
        query.setParameter(3, admin.getEmail());
        query.setParameter(4, admin.getPassword());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(int adminId) {
        EntityManager em = em();

        Admin admin = em.find(Admin.class, adminId);

        em.getTransaction().begin();
        em.remove(admin);
        em.getTransaction().commit();

        em.close();
        return true;
    }
}
