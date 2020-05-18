/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
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
    public Country getById(int userId) {
        Country country = em.find(Country.class, userId);
        return country;
    }

    @Override
    public boolean update(Country c) {
        em.merge(c);
        return true;
    }

    @Override
    public boolean delete(int userId) {
        Country country = em.find(Country.class, userId);
        em.remove(country);

        return true;
    }
}
