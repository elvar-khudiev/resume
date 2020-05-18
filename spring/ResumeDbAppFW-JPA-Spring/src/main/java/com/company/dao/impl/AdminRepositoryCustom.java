/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Admin;
import com.company.entity.User;

import java.util.List;

/**
 *
 * @author HP
 */
public interface AdminRepositoryCustom {

    public List<Admin> getAll();

    public Admin getByEmail(String email);

    public Admin getById(int userId);

    public boolean add(Admin admin);

    public boolean delete(int id);
}
