/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNanoSerenade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author aliay
 */
public class INSTRUMENT {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addInstrument(String name, String model, String manufacturer_name, String manufacturer_city, String manufacturer_state, String manufacturer_country)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `instrument`(`Name`, `Model`, `Manufacturer_Name`, `Manufacturer_City`, `Manufacturer_State`, `Manufacturer_Country`) VALUES (?,?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, name);
            st.setString(2, model);
            st.setString(3, manufacturer_name);
            st.setString(4, manufacturer_city);
            st.setString(5, manufacturer_state);
            st.setString(6, manufacturer_country);
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(INSTRUMENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editInstrument(int instrumentID, String name, String model, String manufacturer_name, String manufacturer_city, String manufacturer_state, String manufacturer_country)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `instrument` SET `Name`=?,`Model`=?,`Manufacturer_Name`=?,`Manufacturer_City`=?,`Manufacturer_State`=?,`Manufacturer_Country`=? WHERE `Instrument_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, name);
            st.setString(2, model);
            st.setString(3, manufacturer_name);
            st.setString(4, manufacturer_city);
            st.setString(5, manufacturer_state);
            st.setString(6, manufacturer_country);
            st.setInt(7, instrumentID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(INSTRUMENT.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeInstrument(int instrumentID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `instrument` WHERE `Instrument_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, instrumentID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(INSTRUMENT.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillInstrumentJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `instrument`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[7];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(INSTRUMENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
