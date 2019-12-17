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
public class NewClass {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
   /**
	 * Recupère l'identifiant idE correspondant au doi et les expériences
	 * return Ide
	 */
	public long getIde(String doi, int totalTime, float totalDose, String injectionMode, String ecosystem) {
		long idE = -1;
		String getIDEQuery = "SELECT `IDE` FROM `experiment` WHERE `doi`=? and `Total_time`=? and `Total_dose`=? and `Injection_mode`=? and `Ecosystem`=?";
		PreparedStatement stGetIde;
		ResultSet ideResult;
		try {

			stGetIde = my_connection.createConnection().prepareStatement(getIDEQuery);
			stGetIde.setString(1, doi);
			stGetIde.setInt(2, totalTime);
			stGetIde.setFloat(3, totalDose);
			stGetIde.setString(4, injectionMode);
			stGetIde.setString(5, ecosystem);

			ideResult = stGetIde.executeQuery();
			if (ideResult.next())
				idE = ideResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, e);
			return -2;
		}

		return idE;
	}

	/**
	 * Insert values describing the experiment setup in the doi
	 * return true on succeed, false otherwise
	 */
	public boolean insertExperiment(String doi, int totalTime, float totalDose, String injectionMode, String ecosystem)
        {
    	 PreparedStatement st_insertionExperiment;
         ResultSet rs_resutltIDE;
         String addEQuery = "INSERT INTO `experiment`(`doi`, `Total_time`, `Total_dose`, `Injection_mode`, `Ecosystem`) VALUES (?,?,?,?,?)";

         try {
                        
             st_insertionExperiment = my_connection.createConnection().prepareStatement(addEQuery);
             st_insertionExperiment.setString(1, doi);
             st_insertionExperiment.setInt(2, totalTime);
             st_insertionExperiment.setFloat(3, totalDose);
             st_insertionExperiment.setString(4, injectionMode);
             st_insertionExperiment.setString(5, ecosystem);
             
             return st_insertionExperiment.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}
        
         public boolean editExperiment(long rowIdE, String doi, int totalTime, float totalDose, String injectionMode, String ecosystem)
        {
    	 PreparedStatement st_editExperiment;
         ResultSet rs_resutltIDE;
         String editEQuery = "UPDATE `experiment` SET `doi`=?,`Total_time`=?,`Total_dose`=?,`Injection_mode`=?,`Ecosystem`=? WHERE `IDE`="+rowIdE;

         try {
                        
             st_editExperiment = my_connection.createConnection().prepareStatement(editEQuery);
             st_editExperiment.setString(1, doi);
             st_editExperiment.setInt(2, totalTime);
             st_editExperiment.setFloat(3, totalDose);
             st_editExperiment.setString(4, injectionMode);
             st_editExperiment.setString(5, ecosystem);
             
             return st_editExperiment.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}


	/**
	 * Insert values describing the experiment setup in the doi, and return the created id
	 * return idE
	 */
	public long addExperiment(String doi, int totalTime, float totalDose, String injectionMode, String ecosystem)
        {
            //recupérer l'idE associé a l'entrée
            long idE = getIde(doi, totalTime, totalDose, injectionMode, ecosystem);
            // Si on ne le trouve pas , alors on l'ajoute pour récuper l'idE nouvellemnt créé
            if(idE < 0) {
                    //on ajoute l'entrée et...
                    insertExperiment(doi, totalTime, totalDose, injectionMode, ecosystem);
                    // .. on recupère l'ide associé créé
                idE = getIde(doi, totalTime, totalDose, injectionMode, ecosystem);
            }
        
            return idE;
	}
	/**
	 * Recupère l'identifiant de la mésure périodique correspondant à l'idE 
	 * return idS
	 */
	public long getIdS(long idE, int measureTime, String nanoparticle) {
		long idS = -1;
		String getIdsQuery = "SELECT `IDS` FROM `sampling` WHERE `IDE`=? AND `Measure_time`=? AND `Nanoparticle`=?";	
		PreparedStatement stGetIdS;
		ResultSet idsResult;
		
		try {

			stGetIdS = my_connection.createConnection().prepareStatement(getIdsQuery);
			stGetIdS.setLong(1, idE);
			stGetIdS.setInt(2, measureTime);
			stGetIdS.setString(3, nanoparticle);

			idsResult = stGetIdS.executeQuery();
			if (idsResult.next())
				idS = idsResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, e);
			return -3;
		}
		return idS;
	}

	/**
	 * Insert values describing the periodic sampling mesurement setup in the experiment idE
	 * return true on succeed, false otherwise
	 */
	public boolean insertSampling(long idE, int measureTime, String nanoparticle) {

		PreparedStatement st_insertionSampling;
		ResultSet rs_resutltIDS;

		String addSQuery = "INSERT INTO `sampling`(`IDE`, `Measure_time`, `Nanoparticle`) VALUES (?,?,?)";

		try {
			st_insertionSampling = my_connection.createConnection().prepareStatement(addSQuery);

			st_insertionSampling.setLong(1, idE);
			st_insertionSampling.setInt(2, measureTime);
			st_insertionSampling.setString(3, nanoparticle);

			return st_insertionSampling.executeUpdate() > 0;

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	/**
	 * Insert values describing the sampling measurement setup in the experiment idE, and return the created id
	 * return idS
	 */
	public long addSampling(long idE, int measureTime, String nanoparticle) {
		//recupérer l'idS associé a l'entrée
            long idS = getIdS(idE, measureTime, nanoparticle);
            // Si on ne le trouve pas , alors on l'ajoute pour récuper l'idS nouvellemnt créé
            if(idS < 0) {
                    //on ajoute l'entrée et...
                    insertSampling(idE, measureTime, nanoparticle);
                    // ... on recupère l'idS associé créé
                    idS = getIdS(idE, measureTime, nanoparticle);
            }
        
            return idS;
	}
        
        /**
	 * Insert values describing the periodic sampling mesurement setup in the experiment idE
	 * return true on succeed, false otherwise
	 */
	public boolean editSampling(long rowIdE, long rowIdS, int measureTime, String nanoparticle) {

		PreparedStatement st_editSampling;
		ResultSet rs_resutltIDS;

		String editSQuery = "UPDATE `sampling` SET `Measure_time`=?,`Nanoparticle`=? WHERE `IDS`="+rowIdS;

		try {
			st_editSampling = my_connection.createConnection().prepareStatement(editSQuery);

			st_editSampling.setInt(1, measureTime);
			st_editSampling.setString(2, nanoparticle);

			return st_editSampling.executeUpdate() > 0;

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	/**
	 * Recupère l'identifiant de l'entrée correspondant à l'idS et les mésures
	 * return idM
	 */
	public long getIdM(long ids, float ph, float temperature, float conductivity, float dissoveldOxigen,
			float orpWater, float orpSediment, float concentrationWater, float concentrationSediment,
			float dissolvedConcentration, float tbars, float taoc, float algae, float bacteria) {
		long idM = -1;
		String getIdsQuery = "SELECT `IDM` FROM `measure` WHERE `IDS`=? AND `PH`=? AND `Temperature`=? AND `Conductivity`=? AND `Dissolved_oxygen`=? AND `ORP_water`=? AND `ORP_sediment`=? AND `Concentration_water`=? AND `Concentration_sediment`=? AND `Dissolved_concentration`=? AND `TBARS`=? AND `TAOC`=? AND `Algae`=? AND `Bacteria`=?";	
		PreparedStatement stGetIdM;
		ResultSet idmResult;
		
		try {
			stGetIdM = my_connection.createConnection().prepareStatement(getIdsQuery);

			stGetIdM.setLong(1, ids);
			stGetIdM.setFloat(2, ph);
			stGetIdM.setFloat(3, temperature);
			stGetIdM.setFloat(4, conductivity);
			stGetIdM.setFloat(5, dissoveldOxigen);
			stGetIdM.setFloat(6, orpWater);
			stGetIdM.setFloat(7, orpSediment);
			stGetIdM.setFloat(8, concentrationWater);
			stGetIdM.setFloat(9, concentrationSediment);
			stGetIdM.setFloat(10, dissolvedConcentration);
			stGetIdM.setFloat(11, tbars);
			stGetIdM.setFloat(12, taoc);
			stGetIdM.setFloat(13, algae);
			stGetIdM.setFloat(14, bacteria);

			idmResult = stGetIdM.executeQuery();
			if (idmResult.next())
				idM = idmResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, e);
			return -4;
		}
		return idM;
	}
	
	/**
	 * Insert values describing the sampling idS
	 * return true on succeed, false otherwise
	 */
	public boolean insertMeasure(long ids, float ph, float temperature, float conductivity, float dissoveldOxigen,
			float orpWater, float orpSediment, float concentrationWater, float concentrationSediment,
			float dissolvedConcentration, float tbars, float taoc, float algae, float bacteria) {

		PreparedStatement st_insertionMeausre;

		String addMQuery = "INSERT INTO `measure`(`IDS`, `PH`, `Temperature`, `Conductivity`, `Dissolved_oxygen`, `ORP_water`, `ORP_sediment`, `Concentration_water`, `Concentration_sediment`, `Dissolved_concentration`, `TBARS`, `TAOC`, `Algae`, `Bacteria`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			st_insertionMeausre = my_connection.createConnection().prepareStatement(addMQuery);

			st_insertionMeausre.setLong(1, ids);
			st_insertionMeausre.setFloat(2, ph);
			st_insertionMeausre.setFloat(3, temperature);
			st_insertionMeausre.setFloat(4, conductivity);
			st_insertionMeausre.setFloat(5, dissoveldOxigen);
			st_insertionMeausre.setFloat(6, orpWater);
			st_insertionMeausre.setFloat(7, orpSediment);
			st_insertionMeausre.setFloat(8, concentrationWater);
			st_insertionMeausre.setFloat(9, concentrationSediment);
			st_insertionMeausre.setFloat(10, dissolvedConcentration);
			st_insertionMeausre.setFloat(11, tbars);
			st_insertionMeausre.setFloat(12, taoc);
			st_insertionMeausre.setFloat(13, algae);
			st_insertionMeausre.setFloat(14, bacteria);

			return (st_insertionMeausre.executeUpdate() > 0);

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

	}
        
         /**
	 * Insert values describing the sampling idS
	 * return true on succeed, false otherwise
	 */
	public boolean editMeasure(long rowIdM, long rowIdS, float ph, float temperature, float conductivity, float dissoveldOxigen,
			float orpWater, float orpSediment, float concentrationWater, float concentrationSediment,
			float dissolvedConcentration, float tbars, float taoc, float algae, float bacteria) {

		PreparedStatement st_editMeausre;

		String editMQuery = "UPDATE `measure` SET `PH`=?,`Temperature`=?,`Conductivity`=?,`Dissolved_oxygen`=?,`ORP_water`=?,`ORP_sediment`=?,`Concentration_water`=?,`Concentration_sediment`=?,`Dissolved_concentration`=?,`TBARS`=?,`TAOC`=?,`Algae`=?,`Bacteria`=? WHERE `IDM`="+rowIdM;

		try {

			st_editMeausre = my_connection.createConnection().prepareStatement(editMQuery);

			
			st_editMeausre.setFloat(1, ph);
			st_editMeausre.setFloat(2, temperature);
			st_editMeausre.setFloat(3, conductivity);
			st_editMeausre.setFloat(4, dissoveldOxigen);
			st_editMeausre.setFloat(5, orpWater);
			st_editMeausre.setFloat(6, orpSediment);
			st_editMeausre.setFloat(7, concentrationWater);
			st_editMeausre.setFloat(8, concentrationSediment);
			st_editMeausre.setFloat(9, dissolvedConcentration);
			st_editMeausre.setFloat(10, tbars);
			st_editMeausre.setFloat(11, taoc);
			st_editMeausre.setFloat(12, algae);
			st_editMeausre.setFloat(13, bacteria);

			return (st_editMeausre.executeUpdate() > 0);

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

	}
    
  // a general function to add a mesocosm
	public boolean addMesocosm(String doi, String nanoparticle, float totaldose,
			String injectionMode, String ecosystem, int totalTime, int measureTime, float ph, float temperature,
			float conductivity, float dissoveldOxigen, float orpWater, float orpSediment, float concentrationWater,
			float concentrationSediment, float dissolvedConcentration, float tbars, float taoc, float algae,
			float bacteria) {

		long ide, ids;
		ide = addExperiment(doi, totalTime, totaldose, injectionMode, ecosystem);
		if(ide < 0) {
			System.err.println("Unable to add a new experiment doi:" + doi);
			return false;
		}
		
		ids = addSampling(ide, measureTime, nanoparticle);
		if(ids < 0) {
			System.err.println("Unable to add a sampling doi:" + doi + " at time:" + measureTime);
			return false;
		}
		return insertMeasure(ids, ph, temperature, conductivity, dissoveldOxigen, orpWater, orpSediment, concentrationWater,
				concentrationSediment, dissolvedConcentration, tbars, taoc, algae, bacteria);
		
	}
    
    
    
    
    
    
    
    // a general function to add a mesocosm
	public boolean editMesocosm(long rowIdE, long rowIdS, long rowIdM, String doi, String nanoparticle, float totaldose,
			String injectionMode, String ecosystem, int totalTime, int measureTime, float ph, float temperature,
			float conductivity, float dissoveldOxigen, float orpWater, float orpSediment, float concentrationWater,
			float concentrationSediment, float dissolvedConcentration, float tbars, float taoc, float algae,
			float bacteria) {

		long ide, ids, idm;
                
                ide = addExperiment(doi, totalTime, totaldose, injectionMode, ecosystem);
                ids = addSampling(ide, measureTime, nanoparticle);
                idm = getIdM(ids, ph, temperature, conductivity, dissoveldOxigen,
			orpWater, orpSediment, concentrationWater, concentrationSediment,
			dissolvedConcentration, tbars, taoc, algae, bacteria);
                
                
		if(ide == rowIdE && ids == rowIdS && idm == rowIdM) {
                        editExperiment(rowIdE, doi, totalTime, totaldose, injectionMode, ecosystem);
                        editSampling(rowIdE, ids, measureTime, nanoparticle);
                        editMeasure(rowIdM, rowIdS, ph, temperature, conductivity, dissoveldOxigen, orpWater, orpSediment, concentrationWater,
				concentrationSediment, dissolvedConcentration, tbars, taoc, algae, bacteria);
                }else{
			System.err.println("Unable to edit the experiment doi:" + doi);
			//return false;
		}
		
                
                
          //return false;
        return false;
                
		
	}
    
    
    
    //4 create a function to remove the selected mesocosm
    public boolean removeMesocosm(String doi)
    {
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `mesocosm` WHERE `DOI`=?";
        
        try {
            
            st = my_connection.createConnection().prepareStatement(deleteQuery);
            
                        st.setString(1, doi);
         
                return (st.executeUpdate() > 0);
            
          } 
                catch (SQLException ex) {
                     Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
                     return false;
        }
    }
            
            
    //2 create a function to populate the jtable all the mesocosms in the database
    public void fillMesocosmJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT e.IDE, s.IDS, m.IDM, doi, Total_time, Total_dose, Injection_mode, Ecosystem, Measure_time, Nanoparticle, PH, Temperature, Conductivity, Dissolved_oxygen, ORP_water, ORP_sediment, Concentration_water, Concentration_sediment, Dissolved_concentration, TBARS, TAOC, Algae, Bacteria FROM experiment e, sampling s, measure m WHERE e.IDE = s.IDE AND s.IDS = m.IDS ";
        
        
        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[21];
                row[0] = rs.getLong(1)+"_"+rs.getLong(2)+"_"+rs.getLong(3);
                row[1] = rs.getString(4);
                row[2] = rs.getInt(5);
                row[3] = rs.getFloat(6);
                row[4] = rs.getString(7);
                row[5] = rs.getString(8);
                row[6] = rs.getInt(9);
                row[7] = rs.getString(10);
                row[8] = rs.getFloat(11);
                row[9] = rs.getFloat(12);
                row[10] = rs.getFloat(13);
                row[11] = rs.getFloat(14);
                row[12] = rs.getFloat(15);
                row[13] = rs.getFloat(16);
                row[14] = rs.getFloat(17);
                row[15] = rs.getFloat(18);
                row[16] = rs.getFloat(19);
                row[17] = rs.getFloat(20);
                row[18] = rs.getFloat(21);
                row[19] = rs.getFloat(22);
                row[20] = rs.getFloat(23);
               
                tableModel.addRow(row);
                
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    
    // a general function to add a mesocosm
	public boolean removeMesocosm(long rowIdE, long rowIdS, long rowIdM) {

          {
    	 PreparedStatement st_deleteMesocosm;
         ResultSet rs_resutltIDE;
         String deleteMesQuery = "UPDATE experiment e, sampling s, measure m WHERE e.IDE = s.IDE AND s.IDS = m.IDS AND e.IDE="+rowIdE+" AND s.IDS="+rowIdS+" AND IDM="+rowIdM;

         try {
                        
             st_deleteMesocosm = my_connection.createConnection().prepareStatement(deleteMesQuery);
                 
             
             return st_deleteMesocosm.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}
        }
    
    
    
    
    
    
    
}
