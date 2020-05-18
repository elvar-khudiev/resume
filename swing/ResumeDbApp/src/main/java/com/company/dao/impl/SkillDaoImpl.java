/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {
    
    private Skill getSkill(ResultSet rs) throws Exception{
        return new Skill(rs.getInt("id"), rs.getString("name"));
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "* "
                    + "FROM "
                    + "skill");

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result.add(getSkill(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<String> getAllName() {
        List<Skill> skills = getAll();
        System.out.println(skills);
        List<String> allSkillName = null;
        
        for(Skill s : skills) {
            allSkillName.add(s.getName());
        }
        return allSkillName;
    }

    @Override
    public Skill getById(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String skill = rs.getString("skill");
            return new Skill(id, skill);
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Skill();
    }

    @Override
    public boolean insert(Skill skill) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(""
                    + "insert "
                    + "into "
                    + "skill (name) "
                    + "values (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            boolean result = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from skill where id = ?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
