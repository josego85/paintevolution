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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodo
 */
public class ConexionMysql {
    Connection conexion;
    Statement s;
    public ConexionMysql(String user,String pwd,String DB) throws SQLException{
            conexion= DriverManager.getConnection ("jdbc:mysql://localhost/"+DB,user,pwd);
            s = conexion.createStatement(); 
    }
    public ResultSet ejecutarQuery(String query) throws SQLException{
        return s.executeQuery(query);
    }
    public void terminarConexion() throws SQLException{
    conexion.close();
    }
    
    
}
