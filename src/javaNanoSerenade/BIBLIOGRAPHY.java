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
public class BIBLIOGRAPHY {
    
     MY_CONNECTION my_connection = new MY_CONNECTION();
     
     //1 create a function to add a client
    public boolean addBibliography(int peopleID, int institutionID, String doi)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `bibliography`(`People_ID`, `Institution_ID`, `DOI`) VALUES (?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, peopleID);
            st.setInt(2, institutionID);
            st.setString(3, doi);
            
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(BIBLIOGRAPHY.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editBibliography(int bibliographyID, int peopleID, int institutionID, String doi)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `bibliography` SET `People_ID`=?, `Institution_ID`=?, `DOI`=? WHERE `Bibliography_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setInt(1, peopleID);
            st.setInt(2, institutionID);
            st.setString(3, doi);
            st.setInt(4, bibliographyID);
            
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(BIBLIOGRAPHY.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeBibliography(int bibliographyID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `bibliography` WHERE `Bibliography_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, bibliographyID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(BIBLIOGRAPHY.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillBibliographyJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `bibliography`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3);
                row[3] = rs.getString(4);
               
               
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BIBLIOGRAPHY.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
