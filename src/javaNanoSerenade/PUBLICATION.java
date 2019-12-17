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
public class PUBLICATION {
    
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addPublication(String doi, String title, String journal, String year, String volume, String issue, String page, String contact_author, String keywords, String pabstract, String publish_status, String curation_status, String curation_note)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `publication`(`DOI`, `Title`, `Journal`, `Year`, `Volume`, `Issue`, `Page`, `Contact_Author`, `Keywords`, `Abstract`, `Publish_Status`, `Curation_Status`, `Curation_Note`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, doi);
            st.setString(2, title);
            st.setString(3, journal);
            st.setString(4, year);
            st.setString(5, volume);
            st.setString(6, issue);
            st.setString(7, page);
            st.setString(8, contact_author);
            st.setString(9, keywords);
            st.setString(10, pabstract);
            st.setString(11, publish_status);
            st.setString(12, curation_status);
            st.setString(13, curation_note);
            
           
                return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(PUBLICATION.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
     //3 create a function to edit the selected client
    public boolean editPublication(String doi, String title, String journal, String year, String volume, String issue, String page, String contact_author, String keywords, String pabstract, String publish_status, String curation_status, String curation_note)
    
    {
        PreparedStatement st;
        String editQuery = "UPDATE `publication` SET `Title`=?,`Journal`=?,`Year`=?,`Volume`=?,`Issue`=?,`Page`=?,`Contact_Author`=?,`Keywords`=?,`Abstract`=?,`Publish_Status`=?,`Curation_Status`=?,`Curation_Note`=? WHERE `DOI`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, title);
            st.setString(2, journal);
            st.setString(3, year);
            st.setString(4, volume);
            st.setString(5, issue);
            st.setString(6, page);
            st.setString(7, contact_author);
            st.setString(8, keywords);
            st.setString(9, pabstract);
            st.setString(10, publish_status);
            st.setString(11, curation_status);
            st.setString(12, curation_note);
            st.setString(13, doi);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PUBLICATION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
        //4 create a function to remove the selected client
    public boolean removePublication(String doi)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `publication` WHERE `DOI`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setString(1, doi);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(PUBLICATION.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
     //2 create a function to populate the jtable all the clients in the database
    public void fillPublicationJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `publication`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[13];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                row[9] = rs.getString(10);
                row[10] = rs.getString(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PUBLICATION.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
    }
}
