/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import com.company.dao.impl.AdminRepository;
import com.company.entity.Admin;
import com.company.service.inter.AdminServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author HP
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminServiceInter {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    @Override
    public Admin getById(int userId) {
        return adminRepository.getById(userId);
    }

    @Override
    public Admin getByEmail(String email) {
        return adminRepository.getByEmail(email);
    }

    @Override
    public boolean add(Admin admin) {
        return adminRepository.add(admin);
    }

    @Override
    public boolean delete(int userId) {
        return adminRepository.delete(userId);
    }
}
