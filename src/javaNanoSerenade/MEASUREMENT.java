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
public class MEASUREMENT {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
    //1 create a function to add a client
    public boolean addMeasurement(String doi, int measurementID, String measurementType, int referencingID, String sourceDOI, int sourceID, float timeRelative, String timeUnit, float timeAbsolute, String parameter, int inheritID, String parameterDescription, String parameterDataType, String parameterText, String parameterStatistic, float parameterNumeric, String parameterNumericOperator, int parameterUnit, String parameterUncertaintyType, float parameterUncertaintyLower, float parameterUncertaintyUpper, String parameterUncertaintyUnit, int ownerGroup, int protocolID, int instrumentID)
    {
        PreparedStatement st;
        String addQuery = "INSERT INTO `measurement`(`DOI`, `Measurement_ID`, `Measurement_Type`, `Referencing_ID`, `Source_Doi`, `Source_ID`, `Time_Relative`, `Time_Unit`, `Time_Absolute`, `Parameter`, `Inherit_ID`, `Parameter_Description`, `Parameter_Data_Type`, `Parameter_Text`, `Parameter_Statistic`, `Parameter_Numeric`, `Parameter_Numeric_Operator`, `Parameter_Unit`, `Parameter_Uncertainty_Type`, `Parameter_Uncertainty_Lower`, `Parameter_Uncertainty_Upper`, `Parameter_Uncertainty_Unit`, `Owner_Group`, `Protocol_ID`, `Instrument_ID`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, doi);
            st.setInt(2, measurementID);
            st.setString(3, measurementType);
            st.setInt(4, referencingID);
            st.setString(5, sourceDOI);
            st.setInt(6, sourceID);
            st.setFloat(7, timeRelative);
            st.setString(8, timeUnit);
            st.setFloat(9, timeAbsolute);
            st.setString(10, parameter);
            st.setInt(11, inheritID);
            st.setString(12, parameterDescription);
            st.setString(13, parameterDataType);
            st.setString(14, parameterText);
            st.setString(15, parameterStatistic);
            st.setFloat(16, parameterNumeric);
            st.setString(17, parameterNumericOperator);
            st.setInt(18, parameterUnit);
            st.setString(19, parameterUncertaintyType);
            st.setFloat(20, parameterUncertaintyLower);
            st.setFloat(21, parameterUncertaintyUpper);
            st.setString(22, parameterUncertaintyUnit);
            st.setInt(23, ownerGroup);
            st.setInt(24, protocolID);
            st.setInt(25, instrumentID);
            
                            return (st.executeUpdate() > 0);
           
        } catch (SQLException ex) {
            Logger.getLogger(MEASUREMENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //3 create a function to edit the selected client
    public boolean editMeasurement(String doi, int measurementID, String measurementType, int referencingID, String sourceDOI, int sourceID, float timeRelative, String timeUnit, float timeAbsolute, String parameter, int inheritID, String parameterDescription, String parameterDataType, String parameterText, String parameterStatistic, float parameterNumeric, String parameterNumericOperator, int parameterUnit, String parameterUncertaintyType, float parameterUncertaintyLower, float parameterUncertaintyUpper, String parameterUncertaintyUnit, int ownerGroup, int protocolID, int instrumentID)
    {
        PreparedStatement st;
        String editQuery = "UPDATE `measurement` SET `DOI`=?,`Measurement_ID`=?,`Measurement_Type`=?,`Referencing_ID`=?,`Source_Doi`=?,`Source_ID`=?,`Time_Relative`=?,`Time_Unit`=?,`Time_Absolute`=?,`Parameter`=?,`Inherit_ID`=?,`Parameter_Description`=?,`Parameter_Data_Type`=?,`Parameter_Text`=?,`Parameter_Statistic`=?,`Parameter_Numeric`=?,`Parameter_Numeric_Operator`=?,`Parameter_Unit`=?,`Parameter_Uncertainty_Type`=?,`Parameter_Uncertainty_Lower`=?,`Parameter_Uncertainty_Upper`=?,`Parameter_Uncertainty_Unit`=?,`Owner_Group`=?,`Protocol_ID`=?,`Instrument_ID`=? WHERE `DOI`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            
            
            st.setInt(1, measurementID);
            st.setString(2, measurementType);
            st.setInt(3, referencingID);
            st.setString(4, sourceDOI);
            st.setInt(5, sourceID);
            st.setFloat(6, timeRelative);
            st.setString(7, timeUnit);
            st.setFloat(8, timeAbsolute);
            st.setString(9, parameter);
            st.setInt(10, inheritID);
            st.setString(11, parameterDescription);
            st.setString(12, parameterDataType);
            st.setString(13, parameterText);
            st.setString(14, parameterStatistic);
            st.setFloat(15, parameterNumeric);
            st.setString(16, parameterNumericOperator);
            st.setInt(17, parameterUnit);
            st.setString(18, parameterUncertaintyType);
            st.setFloat(19, parameterUncertaintyLower);
            st.setFloat(20, parameterUncertaintyUpper);
            st.setString(21, parameterUncertaintyUnit);
            st.setInt(22, ownerGroup);
            st.setInt(23, protocolID);
            st.setInt(24, instrumentID);
            st.setString(25, doi);
            
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(MEASUREMENT.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
    
    
    
    //4 create a function to remove the selected client
    public boolean removeMeasurement(String doi)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `measurement` WHERE `DOI`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setString(1, doi);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(MEASUREMENT.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the clients in the database
    public void fillMeasurementJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `measurement`";
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[20];
                row[0] = rs.getString(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3);
                row[3] = rs.getInt(4);
                row[4] = rs.getString(5);
                row[5] = rs.getInt(6);
                row[6] = rs.getFloat(7);
                row[7] = rs.getString(8);
                row[8] = rs.getFloat(9);
                row[9] = rs.getString(10);
                row[10] = rs.getInt(11);
                row[11] = rs.getString(12);
                row[12] = rs.getString(13);
                row[13] = rs.getString(14);
                row[14] = rs.getString(15);
                row[15] = rs.getFloat(16);
                row[16] = rs.getString(17);
                row[17] = rs.getInt(18);
                row[18] = rs.getString(19);
                row[19] = rs.getFloat(20);
                row[20] = rs.getFloat(21);
                row[21] = rs.getString(22);
                row[22] = rs.getInt(23);
                row[23] = rs.getInt(24);
                row[24] = rs.getInt(25);
               
               
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MEASUREMENT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
