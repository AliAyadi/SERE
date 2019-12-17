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
public class MESOCOSM1 {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    
   /**
	 * Recupère l'identifiant idE correspondant au doi et les expériences
	 * return Ide
	 */
	public long getIde(String doi, float totaldose, int totalTime, String ecosystem, String injectionMode, String primaryCons,
                String secondaryCons, String benthicInocu, String planctonic) {
		long idE = -1;
		String getIDEQuery = "SELECT `IDE` FROM `experiment` WHERE `doi`=? and `Total_dose`=? and `Total_time`=? and `Ecosystem`=?"
                        + " and `Injection_mode`=? and `Primary_consumer`=? and `Secondary_consumer`=? and `Benthic_inocolum`=? and "
                        + "`Planctonic_inocolum`=?";
		PreparedStatement stGetIde;
		ResultSet ideResult;
		try {

			stGetIde = my_connection.createConnection().prepareStatement(getIDEQuery);
			stGetIde.setString(1, doi);
			stGetIde.setFloat(2, totaldose);
			stGetIde.setInt(3, totalTime);
			stGetIde.setString(4, ecosystem);
                        stGetIde.setString(5, injectionMode);
			stGetIde.setString(6, primaryCons);
                        stGetIde.setString(7, secondaryCons);
                        stGetIde.setString(8, benthicInocu);
                        stGetIde.setString(9, planctonic);

			ideResult = stGetIde.executeQuery();
			if (ideResult.next())
				idE = ideResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, e);
			return -2;
		}

		return idE;
	}

	/**
	 * Insert values describing the experiment setup in the doi
	 * return true on succeed, false otherwise
	 */
	public boolean insertExperiment(String doi, float totaldose, int totalTime, String ecosystem, String injectionMode, String primaryCons, 
                String secondaryCons, String benthicInocu, String planctonic)
        {
    	 PreparedStatement st_insertionExperiment;
         ResultSet rs_resutltIDE;
         String addEQuery = "INSERT INTO `experiment`(`doi`, `Total_dose`, `Total_time`, `Ecosystem`, `Injection_mode`, `Primary_consumer`,"
                 + " `Secondary_consumer`, `Benthic_inocolum`, `Planctonic_inocolum`) VALUES (?,?,?,?,?,?,?,?,?)";

         try {
                        
             st_insertionExperiment = my_connection.createConnection().prepareStatement(addEQuery);
             st_insertionExperiment.setString(1, doi);
             st_insertionExperiment.setFloat(2, totaldose);
             st_insertionExperiment.setInt(3, totalTime);
             st_insertionExperiment.setString(4, ecosystem);             
             st_insertionExperiment.setString(5, injectionMode);
             st_insertionExperiment.setString(6, primaryCons);
             st_insertionExperiment.setString(7, secondaryCons);
             st_insertionExperiment.setString(8, benthicInocu);
             st_insertionExperiment.setString(9, planctonic);
             
             return st_insertionExperiment.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}
        
        


	/**
	 * Insert values describing the experiment setup in the doi, and return the created id
	 * return idE
	 */
	public long addExperiment(String doi, float totaldose, int totalTime, String ecosystem, String injectionMode, String primaryCons,
                String secondaryCons, String benthicInocu, String planctonic)
        {
            //recupérer l'idE associé a l'entrée
            long idE = getIde(doi, totaldose, totalTime, ecosystem, injectionMode, primaryCons, secondaryCons, benthicInocu, planctonic);
            // Si on ne le trouve pas , alors on l'ajoute pour récuper l'idE nouvellemnt créé
            if(idE < 0) {
                    //on ajoute l'entrée et...
                    insertExperiment(doi, totaldose, totalTime, ecosystem, injectionMode, primaryCons, secondaryCons, benthicInocu, planctonic);
                    // .. on recupère l'ide associé créé
                idE = getIde(doi, totaldose, totalTime, ecosystem, injectionMode, primaryCons, secondaryCons, benthicInocu, planctonic);
            }
        
            return idE;
	}
	/**
	 * Recupère l'identifiant de la mésure périodique correspondant à l'idE 
	 * return idS
	 */
	public long getIdS(long idE, String metal, String mineralogy, String shape, String coating, int size ,String nanoparticle, int measureTime) {
		long idS = -1;
		String getIdsQuery = "SELECT `IDS` FROM `sampling` WHERE `IDE`=? AND `Metal`=? AND `Mineralogy`=? AND `Shape`=? AND `Coating`=? "
                        + "AND`Size`=? AND `Contaminant`=? AND `Sampling_time`=?";	
		PreparedStatement stGetIdS;
		ResultSet idsResult;
		
		try {

			stGetIdS = my_connection.createConnection().prepareStatement(getIdsQuery);
			                        
                        stGetIdS.setLong(1, idE);
                        stGetIdS.setString(2, metal);
                        stGetIdS.setString(3, mineralogy);
                        stGetIdS.setString(4, shape);
                        stGetIdS.setString(5, coating);
                        stGetIdS.setInt(6, size);
                        stGetIdS.setString(7, nanoparticle);
			stGetIdS.setInt(8, measureTime);

			idsResult = stGetIdS.executeQuery();
			if (idsResult.next())
				idS = idsResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, e);
			return -3;
		}
		return idS;
	}

	/**
	 * Insert values describing the periodic sampling mesurement setup in the experiment idE
	 * return true on succeed, false otherwise
	 */
	public boolean insertSampling(long idE, String metal, String mineralogy, String shape, String coating, int size ,String nanoparticle, int measureTime) {

		PreparedStatement st_insertionSampling;
		ResultSet rs_resutltIDS;

		String addSQuery = "INSERT INTO `sampling`(`IDE`, `Metal`, `Mineralogy`, `Shape`, `Coating`, `Size`, `Contaminant`, `Sampling_time`) "
                        + "VALUES (?,?,?,?,?,?,?,?)";

		try {
			st_insertionSampling = my_connection.createConnection().prepareStatement(addSQuery);

			st_insertionSampling.setLong(1, idE);
                        st_insertionSampling.setString(2, metal);
                        st_insertionSampling.setString(3, mineralogy);
                        st_insertionSampling.setString(4, shape);
                        st_insertionSampling.setString(5, coating);
                        st_insertionSampling.setInt(6, size);
                        st_insertionSampling.setString(7, nanoparticle);
			st_insertionSampling.setInt(8, measureTime);
			

			return st_insertionSampling.executeUpdate() > 0;

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	/**
	 * Insert values describing the sampling measurement setup in the experiment idE, and return the created id
	 * return idS
	 */
	public long addSampling(long idE, String metal, String mineralogy, String shape, String coating, int size ,String nanoparticle, int measureTime) {
		//recupérer l'idS associé a l'entrée
            long idS = getIdS(idE, metal, mineralogy, shape, coating, size, nanoparticle, measureTime);
            // Si on ne le trouve pas , alors on l'ajoute pour récuper l'idS nouvellemnt créé
            if(idS < 0) {
                    //on ajoute l'entrée et...
                    insertSampling(idE, metal, mineralogy, shape, coating, size, nanoparticle, measureTime);
                    // ... on recupère l'idS associé créé
                    idS = getIdS(idE, metal, mineralogy, shape, coating, size, nanoparticle, measureTime);
            }
        
            return idS;
	}
      
	/**
	 * Recupère l'identifiant de l'entrée correspondant à l'idS et les mésures
	 * return idM
	 */
	public long getIdM(long ids, float ph, float temperature, float conductivity, float dissoveldOxigen, float toc,
			float orpWater, float orpSediment, float orpInterface, float particlesWater, float concentrationWater, float concentrationSediment,
			float dissolvedConcentration, float metalLayings, float metalGland, float metalAdult, float metalJuveniles, float CuNi, float speciationGland,
                        float speciationLayings, float speciationJuveniles, float numberAdult, float numberJuveniles, float picoplankton, float picobenthos, 
                        float algaeWater, float algae, float tbars, float taoc) {
		long idM = -1;
		String getIdsQuery = "SELECT `IDM` FROM `measure` WHERE `IDS`=? AND `PH`=? AND `Temperature`=? AND `Conductivity`=? AND `Dissolved_oxygen`=? AND"
                        + " `TOC`=? AND `ORP_water`=? AND `ORP_sediment`=? AND `ORP_interface`=? AND `Particles_water`=? AND `Metal_sediment`=? AND `Metal_water`=?"
                        + " AND `Dissolved_Metal`=? AND `Metal_layings`=? AND `Metal_gland`=? AND `Metal_adult`=? AND `Metal_juveniles`=? AND `CU_NI_sediment`=?"
                        + " AND `Speciation_digestive`=? AND `Speciation_layings`=? AND `Speciation_juveniles`=? AND `Adult`=? AND `Juveniles`=? AND `Picoplankton`=?"
                        + " AND `Picobenthos`=? AND `Algae_water`=? AND `Algae_sediment`=? AND `TBARS`=? AND `TAOC`=?";	
		PreparedStatement stGetIdM;
		ResultSet idmResult;
		
		try {
			stGetIdM = my_connection.createConnection().prepareStatement(getIdsQuery);

			stGetIdM.setLong(1, ids);
			stGetIdM.setFloat(2, ph);
			stGetIdM.setFloat(3, temperature);
			stGetIdM.setFloat(4, conductivity);
			stGetIdM.setFloat(5, dissoveldOxigen);
                        stGetIdM.setFloat(6, toc);
			stGetIdM.setFloat(7, orpWater);
			stGetIdM.setFloat(8, orpSediment);
                        stGetIdM.setFloat(9, orpInterface);
                        stGetIdM.setFloat(10, particlesWater);
                        stGetIdM.setFloat(11, concentrationSediment);
                        stGetIdM.setFloat(12, concentrationWater);
			stGetIdM.setFloat(13, dissolvedConcentration);
                        stGetIdM.setFloat(14, metalLayings);
                        stGetIdM.setFloat(15, metalGland);
                        stGetIdM.setFloat(16, metalAdult);
                        stGetIdM.setFloat(17, metalJuveniles);
                        stGetIdM.setFloat(18, CuNi);
                        stGetIdM.setFloat(19, speciationGland);
                        stGetIdM.setFloat(20, speciationLayings);
                        stGetIdM.setFloat(21, speciationJuveniles);
                        stGetIdM.setFloat(22, numberAdult);
                        stGetIdM.setFloat(23, numberJuveniles);
                        stGetIdM.setFloat(24, picoplankton);
                        stGetIdM.setFloat(25, picobenthos);
                        stGetIdM.setFloat(26, algaeWater);
                        stGetIdM.setFloat(27, algae);
			stGetIdM.setFloat(28, tbars);
			stGetIdM.setFloat(29, taoc);
			
			

			idmResult = stGetIdM.executeQuery();
			if (idmResult.next())
				idM = idmResult.getLong(1);

		} catch (Exception e) {
			Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, e);
			return -4;
		}
		return idM;
	}
	
	/**
	 * Insert values describing the sampling idS
	 * return true on succeed, false otherwise
	 */
	public boolean insertMeasure(long ids, float ph, float temperature, float conductivity, float dissoveldOxigen, float toc,
			float orpWater, float orpSediment, float orpInterface, float particlesWater, float concentrationWater, float concentrationSediment,
			float dissolvedConcentration, float metalLayings, float metalGland, float metalAdult, float metalJuveniles, float CuNi, float speciationGland,
                        float speciationLayings, float speciationJuveniles, float numberAdult, float numberJuveniles, float picoplankton, float picobenthos, 
                        float algaeWater, float algae, float tbars, float taoc) {

		PreparedStatement st_insertionMeausre;

		String addMQuery = "INSERT INTO `measure`(`IDS`, `PH`, `Temperature`, `Conductivity`, `Dissolved_oxygen`, `TOC`, `ORP_water`, `ORP_sediment`, `ORP_interface`,"
                        + " `Particles_water`, `Metal_sediment`, `Metal_water`, `Dissolved_Metal`, `Metal_layings`, `Metal_gland`, `Metal_adult`, `Metal_juveniles`, `CU_NI_sediment`,"
                        + " `Speciation_digestive`, `Speciation_layings`, `Speciation_juveniles`, `Adult`, `Juveniles`, `Picoplankton`, `Picobenthos`, `Algae_water`, `Algae_sediment`,"
                        + " `TBARS`, `TAOC`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {

			st_insertionMeausre = my_connection.createConnection().prepareStatement(addMQuery);

			st_insertionMeausre.setLong(1, ids);
			st_insertionMeausre.setFloat(2, ph);
			st_insertionMeausre.setFloat(3, temperature);
			st_insertionMeausre.setFloat(4, conductivity);
			st_insertionMeausre.setFloat(5, dissoveldOxigen);
                        st_insertionMeausre.setFloat(6, toc);
			st_insertionMeausre.setFloat(7, orpWater);
			st_insertionMeausre.setFloat(8, orpSediment);
                        st_insertionMeausre.setFloat(9, orpInterface);
                        st_insertionMeausre.setFloat(10, particlesWater);
                        st_insertionMeausre.setFloat(11, concentrationSediment);
                        st_insertionMeausre.setFloat(12, concentrationWater);
			st_insertionMeausre.setFloat(13, dissolvedConcentration);
                        st_insertionMeausre.setFloat(14, metalLayings);
                        st_insertionMeausre.setFloat(15, metalGland);
                        st_insertionMeausre.setFloat(16, metalAdult);
                        st_insertionMeausre.setFloat(17, metalJuveniles);
                        st_insertionMeausre.setFloat(18, CuNi);
                        st_insertionMeausre.setFloat(19, speciationGland);
                        st_insertionMeausre.setFloat(20, speciationLayings);
                        st_insertionMeausre.setFloat(21, speciationJuveniles);
                        st_insertionMeausre.setFloat(22, numberAdult);
                        st_insertionMeausre.setFloat(23, numberJuveniles);
                        st_insertionMeausre.setFloat(24, picoplankton);
                        st_insertionMeausre.setFloat(25, picobenthos);
                        st_insertionMeausre.setFloat(26, algaeWater);
                        st_insertionMeausre.setFloat(27, algae);
			st_insertionMeausre.setFloat(28, tbars);
			st_insertionMeausre.setFloat(29, taoc);

			return (st_insertionMeausre.executeUpdate() > 0);

		} catch (SQLException ex) {
			Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}

	}
        
       
    
  // a general function to add a mesocosm
	public boolean addMesocosm(String doi, float totaldose, int totalTime, String ecosystem, String injectionMode, String primaryCons, 
                        String secondaryCons, String benthicInocu, String planctonic, String metal, String mineralogy, String shape, String coating, int size,
                        String nanoparticle, int measureTime, float ph, float temperature, float conductivity, float dissoveldOxigen, float toc, float orpWater,
                        float orpSediment, float orpInterface, float particlesWater, float concentrationWater, float concentrationSediment, 
                        float dissolvedConcentration, float metalLayings, float metalGland,  float metalAdult, float metalJuveniles, float CuNi, 
                        float speciationGland, float speciationLayings, float speciationJuveniles, float numberAdult, float numberJuveniles, float picoplankton, 
                        float picobenthos, float algaeWater,  float algae, float tbars, float taoc) {

		long ide, ids;
		ide = addExperiment(doi, totaldose, totalTime, ecosystem, injectionMode, primaryCons, secondaryCons, benthicInocu, planctonic);
		if(ide < 0) {
			System.err.println("Unable to add a new experiment doi:" + doi);
			return false;
		}
		
		ids = addSampling(ide, metal, mineralogy, shape, coating, size, nanoparticle, measureTime);
		if(ids < 0) {
			System.err.println("Unable to add a sampling doi:" + doi + " at time:" + measureTime);
			return false;
		}
		return insertMeasure(ids, ph, temperature, conductivity, dissoveldOxigen, toc, orpWater, orpSediment, orpInterface, particlesWater,
                        concentrationSediment, concentrationWater, dissolvedConcentration, metalLayings, metalGland,  metalAdult, metalJuveniles,
                        CuNi, speciationGland, speciationLayings, speciationJuveniles, numberAdult, numberJuveniles, picoplankton, picobenthos,
                        algaeWater, algae, tbars, taoc);
		
	}
    
    
    
    
    
    
    
    // a general function to add a mesocosm
	public boolean editMesocosm(long rowIdE, long rowIdS, long rowIdM, String doi, float totaldose, int totalTime, String ecosystem, String injectionMode, String primaryCons, 
                        String secondaryCons, String benthicInocu, String planctonic, String metal, String mineralogy, String shape, String coating, int size,
                        String nanoparticle, int measureTime, float ph, float temperature, float conductivity, float dissoveldOxigen, float toc, float orpWater,
                        float orpSediment, float orpInterface, float particlesWater, float concentrationWater, float concentrationSediment, 
                        float dissolvedConcentration, float metalLayings, float metalGland,  float metalAdult, float metalJuveniles, float CuNi, 
                        float speciationGland, float speciationLayings, float speciationJuveniles, float numberAdult, float numberJuveniles, float picoplankton, 
                        float picobenthos, float algaeWater,  float algae, float tbars, float taoc) {

          {
    	 PreparedStatement st_editMesocosm;
         ResultSet rs_resutltIDE;
         String editMesQuery = "UPDATE experiment e, sampling s, measure m SET `doi`=?, `Total_dose`=?, `Total_time`=?, `Ecosystem`=?, `Injection_mode`=?, "
                 + "`Primary_consumer`=?, `Secondary_consumer`=?, `Benthic_inocolum`=?, `Planctonic_inocolum`=?, `Metal`=?, `Mineralogy`=?, `Shape`=?, "
                 + "`Coating`=?, `Size`=?, `Contaminant`=?, `Sampling_time`=?, `PH`=?, `Temperature`=?, `Conductivity`=?, `Dissolved_oxygen`=?, `TOC`=?, "
                 + "`ORP_water`=?, `ORP_sediment`=?, `ORP_interface`=?, `Particles_water`=?, `Metal_sediment`=?, `Metal_water`=?, `Dissolved_Metal`=?, "
                 + "`Metal_layings`=?, `Metal_gland`=?, `Metal_adult`=?, `Metal_juveniles`=?, `CU_NI_sediment`=?, `Speciation_digestive`=?, "
                 + "`Speciation_layings`=?, `Speciation_juveniles`=?, `Adult`=?, `Juveniles`=?, `Picoplankton`=?, `Picobenthos`=?, `Algae_water`=?, "
                 + "`Algae_sediment`=?, `TBARS`=?, `TAOC`=? WHERE e.IDE = s.IDE AND s.IDS = m.IDS AND e.IDE="+rowIdE+" AND s.IDS="+rowIdS+" AND IDM="+rowIdM;

         try {
                        
             st_editMesocosm = my_connection.createConnection().prepareStatement(editMesQuery);
             
            
                        st_editMesocosm.setString(1, doi);
                        st_editMesocosm.setFloat(2, totaldose);
                        st_editMesocosm.setInt(3, totalTime);
                        st_editMesocosm.setString(4, ecosystem);
                        st_editMesocosm.setString(5, injectionMode);
                        st_editMesocosm.setString(6, primaryCons);
                        st_editMesocosm.setString(7, secondaryCons);
                        st_editMesocosm.setString(8, benthicInocu);
                        st_editMesocosm.setString(9, planctonic);            
                        st_editMesocosm.setString(10, metal);
                        st_editMesocosm.setString(11, mineralogy);
                        st_editMesocosm.setString(12, shape);
                        st_editMesocosm.setString(13, coating);
                        st_editMesocosm.setInt(14, size);
                        st_editMesocosm.setString(15, nanoparticle);
			st_editMesocosm.setInt(16, measureTime);
                        st_editMesocosm.setFloat(17, ph);
			st_editMesocosm.setFloat(18, temperature);
			st_editMesocosm.setFloat(19, conductivity);
			st_editMesocosm.setFloat(20, dissoveldOxigen);
                        st_editMesocosm.setFloat(21, toc);
			st_editMesocosm.setFloat(22, orpWater);
			st_editMesocosm.setFloat(23, orpSediment);
                        st_editMesocosm.setFloat(24, orpInterface);
                        st_editMesocosm.setFloat(25, particlesWater);
                        st_editMesocosm.setFloat(26, concentrationSediment);
                        st_editMesocosm.setFloat(27, concentrationWater);
			st_editMesocosm.setFloat(28, dissolvedConcentration);
                        st_editMesocosm.setFloat(29, metalLayings);
                        st_editMesocosm.setFloat(30, metalGland);
                        st_editMesocosm.setFloat(31, metalAdult);
                        st_editMesocosm.setFloat(32, metalJuveniles);
                        st_editMesocosm.setFloat(33, CuNi);
                        st_editMesocosm.setFloat(34, speciationGland);
                        st_editMesocosm.setFloat(35, speciationLayings);
                        st_editMesocosm.setFloat(36, speciationJuveniles);
                        st_editMesocosm.setFloat(37, numberAdult);
                        st_editMesocosm.setFloat(38, numberJuveniles);
                        st_editMesocosm.setFloat(39, picoplankton);
                        st_editMesocosm.setFloat(40, picobenthos);
                        st_editMesocosm.setFloat(41, algaeWater);
                        st_editMesocosm.setFloat(42, algae);
			st_editMesocosm.setFloat(43, tbars);
			st_editMesocosm.setFloat(44, taoc);             
             
             return st_editMesocosm.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}
        }
    
    
    //4 create a function to remove the selected mesocosm
   	public boolean removeMesocosm(long rowIdE, long rowIdS, long rowIdM) {

          {
    	 PreparedStatement st_deleteMesocosm;
         ResultSet rs_resutltIDE;
         String deleteMQuery = "DELETE FROM measure WHERE IDM ="+ rowIdM;
         //String deleteSQuery = "DELETE FROM sampling WHERE IDS="+ rowIdS;

         try {
                        
             st_deleteMesocosm = my_connection.createConnection().prepareStatement(deleteMQuery);
                 
             
             return st_deleteMesocosm.executeUpdate() > 0;
             
             
         }catch(SQLException ex){
				Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
				return false;
		 }
	}
        }
    
    
            
            
    //2 create a function to populate the jtable all the mesocosms in the database
    public void fillMesocosmJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT e.IDE, s.IDS, m.IDM, `doi`, `Total_dose`, `Total_time`, `Ecosystem`, `Injection_mode`, `Primary_consumer`,"
                + " `Secondary_consumer`, `Benthic_inocolum`, `Planctonic_inocolum`, `Metal`, `Mineralogy`, `Shape`, `Coating`, `Size`, `Contaminant`,"
                + " `Sampling_time`, `PH`, `Temperature`, `Conductivity`, `Dissolved_oxygen`, `TOC`, `ORP_water`, `ORP_sediment`, `ORP_interface`,"
                + " `Particles_water`, `Metal_sediment`, `Metal_water`, `Dissolved_Metal`, `Metal_layings`, `Metal_gland`, `Metal_adult`, `Metal_juveniles`,"
                + " `CU_NI_sediment`, `Speciation_digestive`, `Speciation_layings`, `Speciation_juveniles`, `Adult`, `Juveniles`, `Picoplankton`,"
                + " `Picobenthos`, `Algae_water`, `Algae_sediment`, `TBARS`, `TAOC` FROM experiment e, sampling s, measure m "
                + "WHERE e.IDE = s.IDE AND s.IDS = m.IDS Order by Sampling_time";

        
        try {
            ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
            DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[45];
                row[0] = rs.getLong(1)+"_"+rs.getLong(2)+"_"+rs.getLong(3);
                row[1] = rs.getString(4);
                row[2] = rs.getFloat(5);
                row[3] = rs.getInt(6);
                row[4] = rs.getString(7);
                row[5] = rs.getString(8);
                row[6] = rs.getString(9);
                row[7] = rs.getString(10);
                row[8] = rs.getString(11);
                row[9] = rs.getString(12);                
                row[10] = rs.getString(13);
                row[11] = rs.getString(14);
                row[12] = rs.getString(15);
                row[13] = rs.getString(16);
                row[14] = rs.getString(17);
                row[15] = rs.getString(18);
                row[16] = rs.getInt(19);                              
                row[17] = rs.getFloat(20);
                row[18] = rs.getFloat(21);
                row[19] = rs.getFloat(22);
                row[20] = rs.getFloat(23);
                row[21] = rs.getFloat(24);
                row[22] = rs.getFloat(25);
                row[23] = rs.getFloat(26);
                row[24] = rs.getFloat(27);  
                row[25] = rs.getFloat(28);
                row[26] = rs.getFloat(29);
                row[27] = rs.getFloat(30);
                row[28] = rs.getFloat(31);
                row[29] = rs.getFloat(32);
                row[30] = rs.getFloat(33);
                row[31] = rs.getFloat(34);
                row[32] = rs.getFloat(35);
                row[33] = rs.getFloat(36);
                row[34] = rs.getFloat(37);
                row[35] = rs.getFloat(38);
                row[36] = rs.getFloat(39);
                row[37] = rs.getFloat(40);
                row[38] = rs.getFloat(41);
                row[39] = rs.getFloat(42);
                row[40] = rs.getFloat(43);
                row[41] = rs.getFloat(44);
                row[42] = rs.getFloat(45);
                row[43] = rs.getFloat(46);
                row[44] = rs.getFloat(47);
               
                tableModel.addRow(row);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MESOCOSM1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
