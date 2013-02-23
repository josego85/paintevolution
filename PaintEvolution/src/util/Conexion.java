package util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodo
 */
import java.sql.*;
public class Conexion {
    static Connection conexion;
    static Connection conectar(String DB,String User,String Password) throws SQLException{
    conexion= DriverManager.getConnection ("jdbc:mysql://localhost/",User,Password);
    return conexion;
    }
    public static void main(String[] Arg) throws SQLException{
        Connection prueba=conectar("tallermecanico","root","luisronaldo");
        Statement s = prueba.createStatement();
        System.out.println("lista de BD");
        ResultSet rsSD = s.executeQuery ("show databases");
        while (rsSD.next())
        {
        System.out.println(rsSD.getString (1));
        }
        s.executeQuery ("use tallermecanico");
        
          System.out.println("lista de tablas");
        ResultSet rsSDT = s.executeQuery ("show tables");
        while (rsSDT.next())
        {
        System.out.println(rsSDT.getString (1));
        }
          System.out.println("valores de auto");
        ResultSet rsSDTL = s.executeQuery ("Describe auto");
        while (rsSDTL.next())
        {
        System.out.println(rsSDTL.getString (1)+rsSDTL.getString (2));
        }
        
        
    }
}

