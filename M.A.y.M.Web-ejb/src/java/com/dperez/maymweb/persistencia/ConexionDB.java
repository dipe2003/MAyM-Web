/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author dperez
 */
public class ConexionDB {
    
    private static ConexionDB instance;
    final String DRIVER = "com.mysql.jdbc.Driver";
    final String CONNECTION = "jdbc:mysql://localhost:3306/MAYM_WEB_EXAMPLE;create=true";
    private static Connection conexion = null;
    
    public static ConexionDB getInstancia(){
        if(instance == null){
            instance = new ConexionDB();
        }
        return instance;
    }
    
    private ConexionDB(){}
    
    public int CrearBaseDatos(String NombreBaseDatos){
        int res = -1;
        try{
            Class.forName(DRIVER).newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
            Statement s = conexion.createStatement();
            res = s.executeUpdate("CREATE DATABASE IF NOT EXIST "+ NombreBaseDatos);
        }catch(SQLException e){
            System.out.println("Error CONNECTION: " + e.getMessage());
        }
        return res;
    }
    
    public EntityManager getEntityManager(String NombreBaseDatos){
//        try{
//            Class.forName(DRIVER).newInstance();
//        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        
//        try {
//            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
//            Statement s = conexion.createStatement();
//            int Result = s.executeUpdate("CREATE DATABASE IF NOT EXIST "+ "maym_web_example");
//        }catch(SQLException e){
//            System.out.println("Error CONNECTION: " + e.getMessage());
//        }
        
        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url","jdbc:mysql://localhost:3306/" + NombreBaseDatos);
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "1234");
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");      
      
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MAYM-Web-Datos", properties);
        EntityManager entitymanager = emf.createEntityManager( );
        return entitymanager;
    }
}
