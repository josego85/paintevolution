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
    // Objetos de clase.
    private Connection conexion;
    private Statement statement;
    private String user;
    private String password;
    private String baseDatos;
    
    /**
     * Constructor 
     * @param user
     * @param pwd
     * @param DB
     * @throws SQLException 
     */
    public ConexionMysql(String user, String password, String baseDatos) throws SQLException{
        setBaseDatos(baseDatos);
        setUser(user);
        setPassword(password);      
    }
    
    /**
     * Metodo publico que abre una conexion a la base de datos mysql (localhost).
     * @throws SQLException 
     */
    public void abrirConexion()throws SQLException{
        this.conexion = DriverManager.getConnection ("jdbc:mysql://localhost/" + getBaseDatos(), 
            getUsuario(), getPassword());
        this.statement = this.conexion.createStatement();
    }
    
    /**
     * 
     * @param query
     * @return
     * @throws SQLException 
     */
    public ResultSet ejecutarQuery(String query) throws SQLException{
        return statement.executeQuery(query);
    }
    
    /**
     * 
     * @throws SQLException 
     */
    public void terminarConexion() throws SQLException{
        this.conexion.close();
    }
    
    /**
     * 
     * @return 
     */
    public String getBaseDatos(){
        return this.baseDatos;
    }
    
    /**
     * 
     * @return 
     */
    public String getUsuario(){
        return this.user;
    }
    
    /**
     * 
     * @return 
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * 
     * @param user 
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @param baseDatos 
     */
    public void setBaseDatos(String baseDatos) {
        this.baseDatos = baseDatos;
    }
}
