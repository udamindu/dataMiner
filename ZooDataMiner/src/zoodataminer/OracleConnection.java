/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoodataminer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author Arunalu Hasakelum
 */
public class OracleConnection {
    
    private OracleConnection(){}
    
    private static Connection con = null;
    
    private static OracleConnection instance;
    public static OracleConnection getInstance(){
        if(instance==null){
            instance = new OracleConnection();
        }
        return instance;
    }
    
    public static Connection getConnection(){
        
        try {
            //load the driver class  
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            String url = "jdbc:oracle:thin:@localhost:1522:orcl";

            Properties prop = new Properties();
            prop.setProperty("user", "dmuser");
            prop.setProperty("password", "oracle");
            
            //create  the connection object
            con = DriverManager.getConnection(url, prop);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static void closeConncection(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
