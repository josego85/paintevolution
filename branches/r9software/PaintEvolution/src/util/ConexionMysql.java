/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rodo
 */
public class ConexionMysql {
    Connection conexion;
    Statement s;
    String Base;
    private final String User;
    private final String Pwd;
    public ConexionMysql(String user,String pwd,String DB) throws SQLException{
            Base=DB;
            User=user;
            Pwd=pwd;
            conexion= DriverManager.getConnection ("jdbc:mysql://localhost/"+DB,user,pwd);
            s = conexion.createStatement(); 
    }
    public ResultSet ejecutarQuery(String query) throws SQLException{
        return s.executeQuery(query);
    }
    public void terminarConexion() throws SQLException{
    conexion.close();
    }
    public String getBaseDatos(){
    return Base;
    }
    public String getUser(){
    return User;
    }
    public String getPassword(){
    return Pwd;
    }
    
}
