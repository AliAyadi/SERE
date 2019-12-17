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
public class INSTITUTION {
    
     MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addInstitution(String department, String pinstitution, String city, String state, String zipcode, String country)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `institution`(`Department`, `Institution`, `City`, `State`, `Zipcode`, `Country`) VALUES (?,?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, department);
            st.setString(2, pinstitution);
            st.setString(3, city);
            st.setString(4, state);
            st.setString(5, zipcode);
            st.setString(6, country);
            
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(INSTITUTION.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editInstitution(int institutionID, String department, String pinstitution, String city, String state, String zipcode, String country)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `institution` SET `Department`=?,`Institution`=?,`City`=?,`State`=?,`Zipcode`=?,`Country`=? WHERE `Institution_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, department);
            st.setString(2, pinstitution);
            st.setString(3, city);
            st.setString(4, state);
            st.setString(5, zipcode);
            st.setString(6, country);
            st.setInt(7, institutionID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(INSTITUTION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeInstitution(int institutionID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `institution` WHERE `Institution_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, institutionID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(INSTITUTION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillInstitutionJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `institution`";
        
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
            Logger.getLogger(INSTITUTION.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
    
    
}
