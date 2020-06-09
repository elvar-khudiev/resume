/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.inter;

import com.company.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author HP
 */

public interface AdminServiceInter {

    public List<Admin> getAll();

    public Admin getById(int id);

    public Admin getByEmail(String email);
    
    public boolean add(Admin admin);

    public boolean delete(int id);
}
