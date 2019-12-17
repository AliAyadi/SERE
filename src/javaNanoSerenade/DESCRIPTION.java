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
public class DESCRIPTION { //the client class
    
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addDescription(String table, String field, String note, String data_type, String data_value)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `description`(`Table_descrip`, `Field`, `Note`, `Data_type`, `Valid_value`) VALUES (?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, table);
            st.setString(2, field);
            st.setString(3, note);
            st.setString(4, data_type);
            st.setString(5, data_value);
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(DESCRIPTION.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editDescription(int descriptionID, String table, String field, String note, String data_type, String data_value)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `description` SET `Table_descrip`=?,`Field`=?,`Note`=?,`Data_type`=?,`Valid_Value`=? WHERE `Description_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
             st.setString(1, table);
            st.setString(2, field);
            st.setString(3, note);
            st.setString(4, data_type);
            st.setString(5, data_value);
            st.setInt(6, descriptionID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(DESCRIPTION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeDescription(int descriptionID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `description` WHERE `Description_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, descriptionID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(DESCRIPTION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillDescriptionJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `description`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[6];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DESCRIPTION.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
    
    
    
}
