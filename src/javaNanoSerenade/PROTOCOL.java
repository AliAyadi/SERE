package javaNanoSerenade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author aliay
 */
public class PROTOCOL {
    
 MY_CONNECTION my_connection = new MY_CONNECTION();
 
    
 
 
 
     //1 create a function to add a Protocol
    public boolean addProtocol(String name, int duration_value, String duration_units, String description, String reference)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `protocol`(`Name`, `Duration_Value`, `Duration_Units`, `Description`, `Reference`) VALUES (?,?,?,?,?)";
        
     try {
         st = my_connection.createConnection().prepareStatement(addQuery);
         
         st.setString(1, name);
         st.setInt(2, duration_value);
         st.setString(3, duration_units);
         st.setString(4, description);
         st.setString(5, reference);
         
         return (st.executeUpdate() > 0);
         
     } catch (SQLException ex) {
         Logger.getLogger(PROTOCOL.class.getName()).log(Level.SEVERE, null, ex);
         return false;
     }
         
    }   
    
    
    
    
     //2 create a function to populate the jtable all the protocols in the database
    public void fillProtocolJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `Protocol`";
        
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
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
               
                tableModel.addRow(row);
            }
                      
        } catch (SQLException ex) {
            Logger.getLogger(PROTOCOL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
        
     //3 create a function to edit the selected protocol
    public boolean editProtocol(int protocolID, String name, int duration_value, String duration_units, String description, String reference)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `protocol` SET `Name`=?,`Duration_Value`=?,`Duration_Units`=?,`Description`=?,`Reference`=? WHERE `Protocol_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, name);
            st.setInt(2, duration_value);
            st.setString(3, duration_units);
            st.setString(4, description);
            st.setString(5, reference);
            st.setInt(6, protocolID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PROTOCOL.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
       
    }
    
    
    
    
   
    
    
     //4 create a function to remove the selected protocol
    public boolean removeProtocol(int protocol_ID)
    {
         PreparedStatement st;
        String deleteQuery = "DELETE FROM `protocol` WHERE `Protocol_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, protocol_ID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PROTOCOL.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
}