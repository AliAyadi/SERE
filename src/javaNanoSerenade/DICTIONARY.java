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
public class DICTIONARY{ //the client class
    
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addDictionary(String group, String item, String parameter)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `dictionary`(`Group`, `Item`, `Parameter`) VALUES (?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, group);
            st.setString(2, item);
            st.setString(3, parameter);
           
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(DICTIONARY.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editDictionary(int dictionaryID, String group, String item, String parameter)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `dictionary` SET `Group`=?,`Item`=?,`Parameter`=? WHERE `Dictionary_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
             st.setString(1, group);
            st.setString(2, item);
            st.setString(3, parameter);
            st.setInt(4, dictionaryID);
           
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(DICTIONARY.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeDictionary(int dictionaryID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `dictionary` WHERE `Dictionary_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, dictionaryID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(DICTIONARY.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillDictionaryJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `dictionary`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DICTIONARY.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
    
    
    
}
