/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.*;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.Skill;
import com.company.entity.UserSkill;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author HP
 */
public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Salam World :o");
        UserDaoInter userDao = new UserDaoImpl();
        System.out.println(userDao.getById(3));
    }
}
