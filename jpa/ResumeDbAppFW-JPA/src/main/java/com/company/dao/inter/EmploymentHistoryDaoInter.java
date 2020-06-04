/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface EmploymentHistoryDaoInter {
    
    public List<EmploymentHistory> getAll();

    public List<EmploymentHistory> getByUserId(int userId);

    public EmploymentHistory getById(int userId);
    
    public boolean add(EmploymentHistory history);
    
    public boolean update(EmploymentHistory history);

    public boolean delete(EmploymentHistory employmentHistory);

    public boolean deleteById(int id);
}
