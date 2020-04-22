/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Country;
import com.company.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
public interface CountryServiceInter {

    public List<Country> getAll();

    public Country getById(int userId);
    
    public boolean update(Country u);

    public boolean delete(int id);
}
