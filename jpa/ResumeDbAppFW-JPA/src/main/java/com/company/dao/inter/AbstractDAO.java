/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author HP
 */
public abstract class AbstractDAO {

    public Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/filemansys";
        String username = "root";
        String password = "12345";
        Connection c = null;
        c = DriverManager.getConnection(url, username, password);
        return c;
    }
    
    private static EntityManagerFactory emf = null;
    
    public EntityManager em() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        
        EntityManager entitymanager = emf.createEntityManager();
        return entitymanager;
    }
    
    public void closeEmf() {
        emf.close();
    }
}