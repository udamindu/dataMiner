/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoodataminer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arunalu Hasakelum
 */
public class ZooLogic {
    
    private Connection con;
    private List predictionList;
    private OracleConnection oracleConnection;
    
    private int Hair; 
    private int Feathers; 
    private int Eggs; 
    private int Milk; 
    private int Airborne; 
    private int Aquatic; 
    private int Predator; 
    private int Toothed;
    private int Backbone;
    private int Breathes;
    private int Venomous;
    private int Fins;
    private int Legs;
    private int Tail;
    private int Domestic;
    private int Catsize;
    
    public ZooLogic(String hr,String fthrs,String egs,String mlk,String ab,String aqt,String pred,String toth,String bk,String br,String vm,String fns,String lgs,String tl,String dm,String ct){
        this.Hair = Integer.parseInt(hr);
        this.Feathers = Integer.parseInt(fthrs);
        this.Eggs = Integer.parseInt(egs);
        this.Milk = Integer.parseInt(mlk);
        this.Airborne = Integer.parseInt(ab);
        this.Aquatic = Integer.parseInt(aqt);
        this.Predator = Integer.parseInt(pred);
        this.Toothed = Integer.parseInt(toth);
        this.Backbone = Integer.parseInt(bk);
        this.Breathes = Integer.parseInt(br);
        this.Venomous = Integer.parseInt(vm);
        this.Fins = Integer.parseInt(fns);
        this.Legs = Integer.parseInt(lgs);
        this.Tail = Integer.parseInt(tl);
        this.Domestic = Integer.parseInt(dm);
        this.Catsize = Integer.parseInt(ct);
    }
    
    /**
     * contains Zoo dataset related logic to evaluate predictions
     * @return a list contains prediction and prediction probability
     */
    public List getPredictions(){
        oracleConnection = OracleConnection.getInstance();
        con = oracleConnection.getConnection();
        predictionList = new ArrayList();

        String sql = "select prediction(CLAS_SVM_1_6 using " 
                + Hair + " as Hair, "
                + Feathers + "  as Feathers, "
                + Eggs + "  as Eggs, "
                + Milk + " as Milk, "
                + Airborne + " as Airborne, "
                + Aquatic + "  as Aquatic, "
                + Predator + "  as Predator, "
                + Toothed + " as Toothed, "
                + Backbone + " as Backbone, "
                + Breathes + "  as Breathes, "
                + Venomous + "  as Venomous, "
                + Fins + " as Fins, "
                + Legs + " as Legs, "
                + Tail + "  as Tail, "
                + Domestic + "  as Domestic, "
                + Catsize + " as Catsize),"
                + "prediction_probability(CLAS_SVM_1_6 using "
                + Hair + "  as Hair, "
                + Feathers + "  as Feathers, "
                + Eggs + " as Eggs, "
                + Milk + " as Milk, "
                + Airborne + "  as Airborne, "
                + Aquatic + "  as Aquatic, "
                + Predator + " as Predator, "
                + Toothed + " as Toothed, "
                + Backbone + "  as Backbone, "
                + Breathes + "  as Breathes, "
                + Venomous + " as Venomous, "
                + Fins + " as Fins, "
                + Legs + "  as Legs, "
                + Tail + "  as Tail, "
                + Domestic + " as Domestic, "
                + Catsize + "  as Catsize )from ZOO_DATA where rownum<2";

        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int preditResult = result.getInt(1);
                double prob = result.getDouble(2);
                predictionList.add(0, preditResult);
                predictionList.add(1, prob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ZooDataMiner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            oracleConnection.closeConnection();
        }
        
        return predictionList;
    }
}
