/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.AdminDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Admin;

/**
 *
 * @author HP
 */
public class Main {

    public static void main(String[] args) throws Exception {

        AdminDaoInter adminDao = Context.instanceAdminDao();
        adminDao.add(new Admin(1, "Admin1", "Admin01", "admin1@mail.ru", "12345"));
    }
}
