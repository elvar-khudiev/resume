/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getCountry(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");
        
        return new Country(id, name, nationality);
    }

    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "* "
                    + "FROM "
                    + "country");
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country country = getCountry(rs);
                result.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Country getById(int userId) {
        Country result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from country where id = " + userId);
            
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getCountry(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Country u) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, nationality = ? where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean delete(int userId) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from country where id = " + userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
