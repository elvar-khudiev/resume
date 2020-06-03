/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.*;
import com.company.entity.*;

/**
 *
 * @author HP
 */
public class Main {

    public static void main(String[] args) throws Exception {

        UserSkillDaoInter dao = Context.instanceUserSkillDao();

        System.out.println(dao.getById(93));
//        dao.add(new UserSkill(5, new Skill(null, "test"), new User()));
    }
}
