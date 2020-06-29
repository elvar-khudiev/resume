/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;

import java.util.List;

/**
 *
 * @author HP
 */
public interface EmploymentHistoryRepositoryCustom {
    
    public List<EmploymentHistory> getAll();

    public EmploymentHistory getById(int id);

    public boolean add(EmploymentHistory e);
    
    public boolean update(EmploymentHistory e);

    public boolean deleteByObject(EmploymentHistory e);

    public boolean delete(int id);
}
