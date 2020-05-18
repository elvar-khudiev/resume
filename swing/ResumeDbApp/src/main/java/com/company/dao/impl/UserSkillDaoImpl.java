/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int power = rs.getInt("power");
        String skillName = rs.getString("skill_name");
        
        return new UserSkill(userSkillId, new User(userId), new Skill(userId, skillName), power);
    }
    
    @Override
    public List<String> getAllSkillName() {
        List<String> allSkillName = null;
        List<UserSkill> userSkills = getAllUserSkill();
        
        for(UserSkill us : userSkills) {
            allSkillName.add(us.getSkill().getName());
        }
        return allSkillName;
    }
    
    @Override
    public List<UserSkill> getAllUserSkill () {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "u.*, "
                    + "us.id as userSkillId, "
                    + "s.id, "
                    + "s.name AS skill_name, "
                    + "us.power "
                    + "FROM "
                    + "user_skill us "
                    + "LEFT JOIN user u ON us.user_id = u.id "
                    + "LEFT JOIN skill s ON us.skill_id = s.id ");
            
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    
    @Override
    public List<UserSkill> getAllSkillByUserId (int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "u.*, "
                    + "us.id as userSkillId, "
                    + "s.id, "
                    + "s.name AS skill_name, "
                    + "us.power "
                    + "FROM "
                    + "user_skill us "
                    + "LEFT JOIN user u ON us.user_id = u.id "
                    + "LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE "
                    + "us.user_id = ?");
            
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean insert(UserSkill userSkill) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user_skill (skill_id, user_id, power) values (?, ?, ?)");
            stmt.setInt(1, userSkill.getSkill().getId());
            stmt.setInt(2, userSkill.getUser().getId());
            stmt.setInt(3, userSkill.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean update(UserSkill userSkill) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set skill_id = ?, user_id = ?, power = ? where id = ?");
            stmt.setInt(1, userSkill.getSkill().getId());
            stmt.setInt(2, userSkill.getUser().getId());
            stmt.setInt(3, userSkill.getPower());
            stmt.setInt(4, userSkill.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(int id) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
