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
        UserDaoInter dao2 = Context.instanceUserDao();
        SkillDaoInter dao3 = Context.instanceSkillDao();

//        System.out.println(dao.getById(93));
//        User u = dao2.getByEmail("elvarkhudiev@mail.ru");
//        u.setEmail("test");
//        dao2.add(u);
//        System.out.println(dao2.getByEmail("test"));

        Skill s0 = new Skill(100, "A");
        Skill s1 = dao3.getByName("A");
        dao3.add(s1);
        System.out.println(s1);
        dao3.delete(s1.getId());
    }
}
