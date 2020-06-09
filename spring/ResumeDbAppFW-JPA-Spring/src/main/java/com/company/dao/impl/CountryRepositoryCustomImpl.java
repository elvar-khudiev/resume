/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
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
public class CountryRepositoryCustomImpl implements CountryRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Country> getAll() {
        String jpql = "select c from Country c";
        Query query = em.createQuery(jpql, Country.class);
        List<Country> list = query.getResultList();

        return list;
    }

    @Override
    public Country getById(int id) {
        Country c = em.find(Country.class, id);
        return c;
    }

    @Override
    public boolean update(Country c) {
        em.merge(c);
        return true;
    }

    @Override
    public boolean add(Country c) {
        Query query = em.createNativeQuery("INSERT INTO country (name, nationality) VALUES (?, ?);");
        query.setParameter(1, c.getName());
        query.setParameter(2, c.getNationality());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean deleteByObject(Country c) {
        em.remove(em.find(Country.class, c.getId()));
        return true;
    }

    @Override
    public boolean delete(int id) {
        em.remove(em.find(Country.class, id));
        return true;
    }
}
