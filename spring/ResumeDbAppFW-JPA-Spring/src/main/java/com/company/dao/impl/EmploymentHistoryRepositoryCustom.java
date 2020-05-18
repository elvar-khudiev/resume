/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface EmploymentHistoryRepositoryCustom {
    
    public List<EmploymentHistory> getAll();

    public boolean add(EmploymentHistory history);
    
    public boolean update(EmploymentHistory history);
        
    public boolean delete(int id);
}
