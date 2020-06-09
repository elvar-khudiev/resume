/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.EmploymentHistoryRepository;
import com.company.entity.EmploymentHistory;
import com.company.service.inter.EmploymentHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
public class EmploymentHistoryServiceImpl implements EmploymentHistoryServiceInter {

    @Autowired
    private EmploymentHistoryRepository employmentHistoryRepository;

    @Override
    public List<EmploymentHistory> getAll() {
        return employmentHistoryRepository.getAll();
    }

    @Override
    public EmploymentHistory getById(int id) {
        return employmentHistoryRepository.getById(id);
    }

    @Override
    public boolean update(EmploymentHistory e) {
        return employmentHistoryRepository.update(e);
    }

    @Override
    public boolean add(EmploymentHistory e) {
        return employmentHistoryRepository.add(e);
    }

    @Override
    public boolean deleteByObject(EmploymentHistory e) {
        return employmentHistoryRepository.deleteByObject(e);
    }

    @Override
    public boolean delete(int id) {
        return employmentHistoryRepository.delete(id);
    }
}
