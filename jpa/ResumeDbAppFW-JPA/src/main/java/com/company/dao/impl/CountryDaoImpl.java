/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Admin;
import com.company.entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    @Override
    public List<Country> getAll() {
        EntityManager em = em();

        String jpql = "select c from Country c";
        Query query = em.createQuery(jpql, Country.class);
        List<Country> list = query.getResultList();

        return list;
    }

    @Override
    public Country getById(int id) {
        EntityManager em = em();

        Country country = em.find(Country.class, id);

        em.close();
        return country;
    }

    @Override
    public boolean update(Country c) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.merge(c);

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(Country country) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.remove(em.find(Country.class, country.getId()));

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.remove(em.find(Country.class, id));

        em.getTransaction().commit();
        em.close();
        return true;
    }
}
