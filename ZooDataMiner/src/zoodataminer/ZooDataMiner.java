/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoodataminer;

import GUI.ZooDataClassification;import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Arunalu Hasakelum
 */
public class ZooDataMiner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        //ZooDataClassification z = new ZooDataClassification();
             
        ZooLogic zl = new ZooLogic("1","0","0","1","0","0","1","1","1","1","0","0","4","0","0","1");
        zl.getPredictions();

    }
    
}
