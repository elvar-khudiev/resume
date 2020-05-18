/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserDaoInter {

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public User getByEmailAndPassword(String email, String password);

    public User getById(int userId);

    public boolean update(User u);

    public boolean add(User u);

    public boolean delete(int id);
}
