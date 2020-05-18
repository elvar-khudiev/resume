/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.AdminDaoInter;
import com.company.entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class AdminDaoImpl extends AbstractDAO implements AdminDaoInter {

    private Admin getAdmin(ResultSet rs) throws Exception {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");

        Admin admin = new Admin(id, name, surname, email, null);
        admin.setPassword(rs.getString("password"));

        return admin;
    }

    @Override
    public List<Admin> getAll(String name, String surname) {
        List<Admin> result = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "select "
                    + "u.*, "
                    + "n.name as nationality, "
                    + "c.name as birthplace "
                    + "from "
                    + "admin "
                    + "where 1=1 ";

            PreparedStatement stmt = c.prepareStatement(sql);

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Admin admin = getAdmin(rs);
                result.add(admin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Admin getByEmailAndPassword(String email, String password) {
        Admin result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from admin where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getAdmin(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public Admin getByEmail(String email) {
        Admin result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from admin where email=?");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getAdmin(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public Admin getById(int adminId) {
        Admin result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select "
                    + "* "
                    + "from "
                    + "admin "
                    + "where admin.id = " + adminId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                result = getAdmin(rs);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean add(Admin admin) {

        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(
                    "insert "
                            + "into "
                            + "admin(name, surname, email, password) "
                            + "values(?, ?, ?, ?)");
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getSurname());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, crypt.hashToString(4, admin.getPassword().toCharArray()));

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int adminId) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from admin where id = " + adminId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
