/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gomez_maestre_mario_examen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author marioquartz
 */
public class Database {
   private Connection connection;
    
    /**
     * Abre la conexión con la base de datos
     */
    public void openConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/NBA",
                "examen", "mariogomez"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Cierra la conexión
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Permite crear las consultas sin exponer la conexión
     * 
     * @return Statement
     * @throws java.sql.SQLException
     */
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
}
