/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CountryRepositoryCustom {

    public List<Country> getAll();

    public Country getById(int userId);

    public boolean update(Country u);

    public boolean delete(int id);
}
