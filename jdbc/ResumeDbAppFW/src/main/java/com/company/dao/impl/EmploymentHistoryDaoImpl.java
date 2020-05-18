/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String header = rs.getString("header");
        Date beginDate = rs.getDate("begin_date");

        Date endDate = null;
        if (rs.getDate("end_date") != null) {
            endDate = rs.getDate("end_date");
        }

        String jobDescription = rs.getString("job_description");
        int userId = rs.getInt("user_id");

        EmploymentHistory emp = new EmploymentHistory(id, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT "
                    + "* "
                    + "FROM "
                    + "employment_history");

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<EmploymentHistory> getByUserId(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(""
                    + "SELECT "
                    + "* "
                    + "FROM "
                    + "employment_history "
                    + "WHERE "
                    + "user_id = ?");

            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result.add(getEmploymentHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean insert(EmploymentHistory history) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement(""
                    + "insert "
                    + "into employment_history "
                    + "(header, job_description, begin_date, end_date, user_id) "
                    + "values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, history.getHeader());
            stmt.setString(2, history.getJobDescription());
            stmt.setDate(3, history.getBeginDate());
            stmt.setDate(4, history.getEndDate());
            stmt.setInt(5, history.getUser().getId());
            boolean b = stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                history.setId(generatedKeys.getInt(1));
            }

            return b;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(EmploymentHistory history) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update employment_history set header = ?, job_description = ?, begin_date = ?, end_date = ? where id = ?");
            stmt.setString(1, history.getHeader());
            stmt.setString(2, history.getJobDescription());
            stmt.setDate(3, history.getBeginDate());
            stmt.setDate(4, history.getEndDate());
            stmt.setInt(5, history.getId());

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
            return stmt.execute("delete from employment_history where id = " + userId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
