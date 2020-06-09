/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.EmploymentHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

public interface EmploymentHistoryServiceInter {

    public List<EmploymentHistory> getAll();

    public EmploymentHistory getById(int id);

    public boolean update(EmploymentHistory e);

    public boolean add(EmploymentHistory e);

    public boolean deleteByObject(EmploymentHistory e);

    public boolean delete(int id);
}
