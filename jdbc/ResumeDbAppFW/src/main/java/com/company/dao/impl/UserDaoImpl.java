/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        Date birthDate = rs.getDate("birthdate");
        String address = rs.getString("address");
        String profileDescription = rs.getString("profile_description");
        int nationalityId = rs.getInt("nationality_id");
        int birthPlaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthPlaceStr = rs.getString("birthplace");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthPlace = new Country(birthPlaceId, birthPlaceStr, null);

        return new User(id, name, surname, email, phone, birthDate, address, profileDescription, nationality, birthPlace);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "select "
                    + "u.*, "
                    + "n.name as nationality, "
                    + "c.name as birthplace "
                    + "from "
                    + "user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where 1=1 ";

            if (name != null && !name.trim().isEmpty()) {
                sql += "and u.name=? ";
            }

            if (surname != null && !surname.trim().isEmpty()) {
                sql += "and u.surname=? ";
            }

            if (nationalityId != null) {
                sql += "and u.nationality_id=? ";
            }
            sql += " ORDER BY id ASC";

            PreparedStatement stmt = c.prepareStatement(sql);

            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }

            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }

            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select "
                    + "u.*, "
                    + "n.name as nationality, "
                    + "c.name as birthplace "
                    + "from "
                    + "user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id "
                    + "where u.id = " + userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                result = getUser(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(User u) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name = ?, surname = ?, email = ?, phone = ?, address = ?, birthdate = ?, profile_description = ?, birthplace_id = ?, nationality_id = ? where id = ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getAddress());
            stmt.setDate(6, u.getBirthDate());
            stmt.setString(7, u.getProfileDescription());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean add(User u) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(
                    "insert "
                    + "into "
                    + "user(name, surname, phone, email, address, profile_description, birthdate, birthplace_id, nationality_id) "
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getAddress());
            stmt.setString(6, u.getProfileDescription());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());

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
            return stmt.execute("delete from user where id = " + userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
