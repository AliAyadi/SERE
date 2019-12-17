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
public class PEOPLE { //the client class
    
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addPeople(String fname, String lname, String mname, String email, String phone, String web)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `people`(`First_Name`, `Last_Name`, `Middle_Name`, `Email`, `Phone`, `Web`) VALUES (?,?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, mname);
            st.setString(4, email);
            st.setString(5, phone);
            st.setString(6, web);
            
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(PEOPLE.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    //3 create a function to edit the selected client
    public boolean editPeople(int peopleID, String fname, String lname, String mname, String email, String phone, String web)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `people` SET `First_Name`=?,`Last_Name`=?,`Middle_Name`=?,`Email`=?,`Phone`=?,`Web`=? WHERE `People_ID`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, mname);
            st.setString(4, email);
            st.setString(5, phone);
            st.setString(6, web);
            st.setInt(7, peopleID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PEOPLE.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removePeople(int peopleID)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `people` WHERE `People_ID`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setInt(1, peopleID);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PEOPLE.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillPeopleJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `people`";
        
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
            Logger.getLogger(PEOPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    
    
    
    
}
