/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNanoSerenade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.text.*;
import java.awt.print.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author aliay
 */
public class SearchMesocosmsForm extends JFrame {
    
    
    private SpinnerNumberModel modelLB, modelHB;
  

    public static final ArrayList<String> TextFields = new ArrayList<String>(Arrays.asList("doi", "ecosystem", "primary consumer", "secondary consumer", "benthic inoculum", "planctonic inoculum", "injection mode", "metal", "mineralogy", "shape", "coating",  "contaminant"));

    public static final ArrayList<String> NumberFields = new ArrayList<String>(Arrays.asList("total dose", "total duration", "size", "Sampling time", "ph", "temperature", "conductivity", "dissolved oxygen", "toc", "orp water", "orp sediment", "orp water/sediment interface", "number of particles in the water column", "total [metal] in surficial sediment", "total [metal] in water", "dissolved [metal] in water",  "total [metal] in layings", "total [metal] in digestive gland", "total [metal] in adult", "total [metal] in juveniles", "[cu/ni] in sediment", "speciation in digestive gland", "speciation in layings", "speciation in juveniles", "number adult", "number juveniles", "number picoplankton", "number picobenthos", "number algae - water", "number algae - sediment", "tbars", "taoc"));

    public String[] comboboxFieldList = {"All fields", "DOI", "Total dose", "Total duration", "Ecosystem", "Primary consumer", "Secondary consumer", "Benthic inoculum", "Planctonic inoculum", "Injection mode", "Metal", "Mineralogy", "Shape", "Coating", "Size", "Contaminant",  "Sampling time", "pH", "Temperature", "Conductivity", "Dissolved oxygen", "TOC", "ORP water", "ORP sediment", "ORP water/sediment interface", "Number of particles in the water column", "Total [metal] in surficial sediment", "Total [metal] in water", "Dissolved [metal] in water",  "Total [metal] in layings", "Total [metal] in digestive gland", "Total [metal] in adult", "Total [metal] in juveniles", "[Cu/Ni] in sediment", "Speciation in digestive gland", "Speciation in layings", "Speciation in juveniles", "Number adult", "Number juveniles", "Number picoplankton", "Number picobenthos", "Number algae - water", "Number algae - sediment", "TBARS", "TAOC" };            
     
    public static final ArrayList<Integer> yellowCol = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8));
    
    public static final ArrayList<Integer> grayCol = new ArrayList<Integer>(Arrays.asList(9,10,11,12,13,14,15));
    
    public static final ArrayList<Integer> greenCol = new ArrayList<Integer>(Arrays.asList(16,17,18,19,20,21,22,23,24));
    
    public static final ArrayList<Integer> blueCol = new ArrayList<Integer>(Arrays.asList(25,26,27,28,29,30,31,32,33,34,35));
    
   public static final ArrayList<Integer> redCol = new ArrayList<Integer>(Arrays.asList(36,37,38,39,40,41,42,43));
   
	MY_CONNECTION my_connection = new MY_CONNECTION();

	/**
	 * Creates new form ManageRoomsForm
	 */
	public SearchMesocosmsForm() {

		initComponents();
                
                GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
 
                if( device.isFullScreenSupported() && !this.isDisplayable() ) {
                    this.setUndecorated(true);
                    device.setFullScreenWindow(this); 
                }else {
                    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    this.setResizable(true); // on empêche de redimensionner la fenêtre 
                }
                //avoir une fenetre à la taille de l'écran
//        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//        int height = (int)dimension.getHeight();
//        int width  = (int)dimension.getWidth();
//        Dimension d= new Dimension(height, width);
//        this.setMinimumSize(d);
//        this.setPreferredSize(d);
              
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                
                jPanelLegend.setVisible(false);
                 
                // Get the column header for your specific column
//                JTableHeader th = table.getTableHeader();
//                TableColumnModel tcm = th.getColumnModel();
//                TableColumn tc = tcm.getColumn(column);

		jLabelLowerB.setVisible(false);
		spinnerLowerB.setVisible(false);
		jLabelHigherB.setVisible(false);
		spinnerHeigherB.setVisible(false);
		// spinnerLowerB.setEditable(false);
		// spinnerHeigherB.setEditable(false);
		jLabelTap.setVisible(false);
		jTextFieldSearch.setVisible(false);
		// jTextFieldSearch.setEditable(false);
		jComboBoxMT.setVisible(true);

		// ajout d une icone serende pour la page
		this.setIconImage(new ImageIcon(getClass().getResource("/javaNanoSerenade/image/lloogg_ser.png")).getImage());

		// populate the jtable
		// mesocosm.fillMesocosmJTable(jTable1);
                
           
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        
       

	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButtonClearCheckboxes = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonPrintResults = new javax.swing.JButton();
        jButtonExportResults = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxFields = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxMT = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabelTap = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonValidateSearch = new javax.swing.JButton();
        jLabelLowerB = new javax.swing.JLabel();
        jLabelHigherB = new javax.swing.JLabel();
        //private SpinnerNumberModel modelLB;
        modelLB = new SpinnerNumberModel(0, 0, 1000, 0.01);
        spinnerLowerB = new JSpinner(modelLB);
        //private SpinnerNumberModel modelHB;
        modelHB = new SpinnerNumberModel(0, 0, 1000, 0.01);
        spinnerHeigherB = new JSpinner(modelHB);
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jPanelLegend = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonReturn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemMesocosms = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemPublications = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItemInstitutions = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItemBibliograpgy = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItemPeople = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItemProtocols = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItemInstruments = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItemMeasurements = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItemDescription = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItemDictionary = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemDiscover = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItemHelp = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Advanced Search Mesocosms (SERENADE)");
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1320, 900));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButtonClearCheckboxes.setBackground(new java.awt.Color(204, 204, 204));
        jButtonClearCheckboxes.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButtonClearCheckboxes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/reset_button.png"))); // NOI18N
        jButtonClearCheckboxes.setToolTipText("Click to return to the precedent window.");
        jButtonClearCheckboxes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jButtonClearCheckboxes.setBorderPainted(false);
        jButtonClearCheckboxes.setContentAreaFilled(false);
        jButtonClearCheckboxes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonClearCheckboxes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearCheckboxesActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/exit_button.png"))); // NOI18N
        jButton1.setToolTipText("Click to return to the precedent window.");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setDefaultCapable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonPrintResults.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/print_button_1.png"))); // NOI18N
        jButtonPrintResults.setToolTipText("Click to return to the precedent window.");
        jButtonPrintResults.setBorderPainted(false);
        jButtonPrintResults.setContentAreaFilled(false);
        jButtonPrintResults.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonPrintResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrintResultsActionPerformed(evt);
            }
        });

        jButtonExportResults.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/EXP8button.png"))); // NOI18N
        jButtonExportResults.setToolTipText("Click to return to the precedent window.");
        jButtonExportResults.setBorderPainted(false);
        jButtonExportResults.setContentAreaFilled(false);
        jButtonExportResults.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonExportResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportResultsActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.MatteBorder(null), "Search fields", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Select Field (s): ");

        jComboBoxFields.setModel(new DefaultComboBoxModel<String>(comboboxFieldList));
        jComboBoxFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFieldsActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("at ");

        jComboBoxMT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All sampling times", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Sampling time: ");

        jLabelTap.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabelTap.setForeground(new java.awt.Color(102, 102, 102));
        jLabelTap.setText("Tap keywords:");

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jButtonValidateSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/search_button.png"))); // NOI18N
        jButtonValidateSearch.setToolTipText("Click to run your query on the SERENADE database. ");
        jButtonValidateSearch.setBorderPainted(false);
        jButtonValidateSearch.setContentAreaFilled(false);
        jButtonValidateSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonValidateSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonValidateSearchActionPerformed(evt);
            }
        });

        jLabelLowerB.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabelLowerB.setForeground(new java.awt.Color(102, 102, 102));
        jLabelLowerB.setText("Lower bound:");

        jLabelHigherB.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabelHigherB.setForeground(new java.awt.Color(102, 102, 102));
        jLabelHigherB.setText("Higher bound:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel10)
                .addGap(10, 10, 10)
                .addComponent(jComboBoxFields, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelTap)
                .addGap(10, 10, 10)
                .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelLowerB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(spinnerLowerB, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHigherB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerHeigherB, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBoxMT, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addComponent(jButtonValidateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabelTap)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLowerB)
                    .addComponent(jLabelHigherB)
                    .addComponent(spinnerLowerB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerHeigherB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jButtonValidateSearch)
                .addGap(8, 8, 8))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        class ColumnColorRenderer extends DefaultTableCellRenderer {
            Color backgroundColor, foregroundColor;
            public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
                super();
                this.backgroundColor = backgroundColor;
                this.foregroundColor = foregroundColor;
            }
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setBackground(backgroundColor);
                cell.setForeground(foregroundColor);
                return cell;
            }
        }
        jScrollPane1.setViewportView(table);

        jPanelLegend.setBackground(new java.awt.Color(255, 255, 255));
        jPanelLegend.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Table legend", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/bleu.png"))); // NOI18N
        jLabel1.setText("Sampling informations");
        jLabel1.setToolTipText("Blue columns represent the sampling informations ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/gris.png"))); // NOI18N
        jLabel2.setText("General informations ");
        jLabel2.setToolTipText("Gray columns represent the general informations ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/vert.png"))); // NOI18N
        jLabel3.setText("Environmental endpoints");
        jLabel3.setToolTipText("Green columns represent the environmentalendpoints");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/jaune.png"))); // NOI18N
        jLabel4.setText("Exposure endpoints");
        jLabel4.setToolTipText("Yellow columns represent the exposure endpoints");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/rouge.png"))); // NOI18N
        jLabel5.setText("Hazard endpoints");
        jLabel5.setToolTipText("Red columns represent the hazard endpoints");

        javax.swing.GroupLayout jPanelLegendLayout = new javax.swing.GroupLayout(jPanelLegend);
        jPanelLegend.setLayout(jPanelLegendLayout);
        jPanelLegendLayout.setHorizontalGroup(
            jPanelLegendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLegendLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanelLegendLayout.setVerticalGroup(
            jPanelLegendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLegendLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanelLegendLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        jButtonReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaNanoSerenade/image/back_button.png"))); // NOI18N
        jButtonReturn.setToolTipText("Click to return to the precedent window.");
        jButtonReturn.setBorderPainted(false);
        jButtonReturn.setContentAreaFilled(false);
        jButtonReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReturnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jButtonClearCheckboxes, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonExportResults, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonPrintResults, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButtonReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonClearCheckboxes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExportResults, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1))
                        .addComponent(jButtonReturn, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jButtonPrintResults, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N

        jMenu5.setText("File ");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Properties");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);
        jMenu5.add(jSeparator4);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.setToolTipText("Click to close the application.");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        jMenu1.setText("Edit >");
        jMenu1.add(jSeparator1);

        jMenuItemMesocosms.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemMesocosms.setText("Mesocosms");
        jMenuItemMesocosms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMesocosmsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemMesocosms);
        jMenu1.add(jSeparator2);

        jMenuItemPublications.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPublications.setText("Publications");
        jMenuItemPublications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPublicationsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPublications);
        jMenu1.add(jSeparator6);

        jMenuItemInstitutions.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemInstitutions.setText("Instutitions");
        jMenuItemInstitutions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInstitutionsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemInstitutions);
        jMenu1.add(jSeparator7);

        jMenuItemBibliograpgy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBibliograpgy.setText("Bibliograpgy");
        jMenuItemBibliograpgy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBibliograpgyActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemBibliograpgy);
        jMenu1.add(jSeparator8);

        jMenuItemPeople.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPeople.setText("People");
        jMenuItemPeople.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPeopleActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemPeople);
        jMenu1.add(jSeparator9);

        jMenuItemProtocols.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemProtocols.setText("Protocols");
        jMenuItemProtocols.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProtocolsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemProtocols);
        jMenu1.add(jSeparator10);

        jMenuItemInstruments.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemInstruments.setText("Instruments");
        jMenuItemInstruments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInstrumentsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemInstruments);
        jMenu1.add(jSeparator11);

        jMenuItemMeasurements.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemMeasurements.setText("Measurements");
        jMenuItemMeasurements.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMeasurementsActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemMeasurements);
        jMenu1.add(jSeparator12);

        jMenuItemDescription.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDescription.setText("Description");
        jMenuItemDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDescriptionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDescription);
        jMenu1.add(jSeparator13);

        jMenuItemDictionary.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDictionary.setText("Disctionary");
        jMenuItemDictionary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDictionaryActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemDictionary);
        jMenu1.add(jSeparator3);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Search ");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Query the database");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("?");
        jMenu2.setToolTipText("More details about the SEREDATA ?");

        jMenuItemDiscover.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDiscover.setText("Discover LabEX SERENADE");
        jMenuItemDiscover.setToolTipText("Know more about the SERENADE lab ?");
        jMenuItemDiscover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDiscoverActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDiscover);
        jMenu2.add(jSeparator14);

        jMenuItemHelp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemHelp.setText("Help");
        jMenuItemHelp.setToolTipText("Need some help ? ");
        jMenuItemHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemHelpActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemHelp);
        jMenu2.add(jSeparator5);

        jMenuItemAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbout.setText("About");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemAbout);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPrintResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrintResultsActionPerformed
        MessageFormat header = new MessageFormat("User report");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try{
            table.print(JTable.PrintMode.NORMAL,header, footer);
        }catch (java.awt.print.PrinterException e){
            System.err.format("Cannot print %%n", e.getMessage());
        }
        
    }//GEN-LAST:event_jButtonPrintResultsActionPerformed

    private void jButtonExportResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportResultsActionPerformed
       
			//DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        
        FileOutputStream excelFou = null;
        BufferedOutputStream excelBOU = null;
        XSSFWorkbook excelJTableExporter = null;
        //choose location for saving excel file
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\aliay\\Desktop");
        //change dialague bow title
        excelFileChooser.setDialogTitle("Save As");
        //Onliny filter files with these extensions "xls","xlsx","xlsm"
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);
        
        //check if save button is clicked
                if (excelChooser == JFileChooser.APPROVE_OPTION)
                {
                                      
            try {
                //import excel POI libraries to netbeans
                excelJTableExporter = new XSSFWorkbook();
                XSSFSheet excelSheet = excelJTableExporter.createSheet("JTable Sheet") ;
                TableModel model = table.getModel();
                
                TableColumnModel model1 = table.getTableHeader().getColumnModel();
                       
                XSSFRow fRow1 = excelSheet.createRow((short) 0);
                       for (int i = 0; i < model1.getColumnCount(); i++){
                            XSSFCell excelCell = fRow1.createCell((short) i);
                           excelCell.setCellValue(model1.getColumn(i).getHeaderValue().toString());
                       }
                
                //loop to get jtable columns and rows
                
                for (int i =0; i< table.getRowCount(); i++)
                {
                    XSSFRow excelRow = excelSheet.createRow(i+1);
                    for (int j =0; j< table.getColumnCount(); j++)
                    {
                        XSSFCell excelCell = excelRow.createCell(j);
                        
                        excelCell.setCellValue(table.getValueAt(i,j).toString());
                    }
                }
                //Append xlsx file extensions to selected files. To create unique file names
                excelFou = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                excelBOU = new BufferedOutputStream(excelFou);
                excelJTableExporter.write(excelBOU);
                JOptionPane.showMessageDialog(null, "Exported Successfully !");
            } catch (FileNotFoundException ex) {
                     ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(SearchMesocosmsForm.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if(excelBOU != null)
                    {
                        excelBOU.close();
                    }
                    if(excelFou != null)
                    {
                        excelFou.close();
                    }
                    if(excelJTableExporter != null)
                    {
                        excelJTableExporter.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
                    
                }
        
        
        
    }//GEN-LAST:event_jButtonExportResultsActionPerformed

    private void jButtonReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReturnActionPerformed
        // TODO add your handling code here:
        this.toBack();
        setVisible(false);
        
    }//GEN-LAST:event_jButtonReturnActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemMesocosmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMesocosmsActionPerformed
        // open the manage Rooms form
        ManageMesocosmsFormLONG mesocosmForm = new ManageMesocosmsFormLONG();
        mesocosmForm.setVisible(true);
        mesocosmForm.pack();
        mesocosmForm.setLocationRelativeTo(null);
        mesocosmForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemMesocosmsActionPerformed

    private void jMenuItemPublicationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPublicationsActionPerformed
        // open the Discover SERENADE form
        ManagePublicationForm publicationForm = new ManagePublicationForm();
        publicationForm.setVisible(true);
        publicationForm.pack();
        publicationForm.setLocationRelativeTo(null);
        publicationForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemPublicationsActionPerformed

    private void jMenuItemInstitutionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInstitutionsActionPerformed
        // open the Institution form
        ManageInstitutionForm institutionForm = new ManageInstitutionForm();
        institutionForm.setVisible(true);
        institutionForm.pack();
        institutionForm.setLocationRelativeTo(null);
        institutionForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemInstitutionsActionPerformed

    private void jMenuItemBibliograpgyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBibliograpgyActionPerformed
        // open the bibliography form
        ManageBibliographyForm bibliograpgyForm = new ManageBibliographyForm();
        bibliograpgyForm.setVisible(true);
        bibliograpgyForm.pack();
        bibliograpgyForm.setLocationRelativeTo(null);
        bibliograpgyForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bibliograpgyForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemBibliograpgyActionPerformed

    private void jMenuItemPeopleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPeopleActionPerformed
        // open the Description form
        ManagePeopleForm peopleForm = new ManagePeopleForm();
        peopleForm.setVisible(true);
        peopleForm.pack();
        peopleForm.setLocationRelativeTo(null);
        peopleForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jMenuItemPeopleActionPerformed

    private void jMenuItemProtocolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProtocolsActionPerformed
        // open the Manage protocol form
        ManageProtocolForm protocolForm = new ManageProtocolForm();
        protocolForm.setVisible(true);
        protocolForm.pack();
        protocolForm.setLocationRelativeTo(null);
        protocolForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemProtocolsActionPerformed

    private void jMenuItemInstrumentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInstrumentsActionPerformed
        // open the Manage Dictionary form
        ManageInstrumentForm instrumentForm = new ManageInstrumentForm();
        instrumentForm.setVisible(true);
        instrumentForm.pack();
        instrumentForm.setLocationRelativeTo(null);
        instrumentForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jMenuItemInstrumentsActionPerformed

    private void jMenuItemMeasurementsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMeasurementsActionPerformed
        // open the Institution form
        ManageMeasurementForm measurementForm = new ManageMeasurementForm();
        measurementForm.setVisible(true);
        measurementForm.pack();
        measurementForm.setLocationRelativeTo(null);
        measurementForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemMeasurementsActionPerformed

    private void jMenuItemDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDescriptionActionPerformed
        // open the Description form
        ManageDescriptionForm descriptionForm = new ManageDescriptionForm();
        descriptionForm.setVisible(true);
        descriptionForm.pack();
        descriptionForm.setLocationRelativeTo(null);
        descriptionForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jMenuItemDescriptionActionPerformed

    private void jMenuItemDictionaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDictionaryActionPerformed
        // open the Manage Dictionary form
        ManageDictionaryForm DictionaryForm = new ManageDictionaryForm();
        DictionaryForm.setVisible(true);
        DictionaryForm.pack();
        DictionaryForm.setLocationRelativeTo(null);
        DictionaryForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jMenuItemDictionaryActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // open the Institution form
        SearchMesocosmsForm searchMesocosmForm = new SearchMesocosmsForm();
        searchMesocosmForm.setVisible(true);
        searchMesocosmForm.pack();
        searchMesocosmForm.setLocationRelativeTo(null);
        searchMesocosmForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:
        // open the Institution form
        SearchMesocosmsForm searchMesocosmForm = new SearchMesocosmsForm();
        searchMesocosmForm.setVisible(true);
        searchMesocosmForm.pack();
        searchMesocosmForm.setLocationRelativeTo(null);
        searchMesocosmForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItemDiscoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDiscoverActionPerformed
        // open the Discover SERENADE form
        DiscoverSerenadeForm discoverSerenadeForm = new DiscoverSerenadeForm();
        discoverSerenadeForm.setVisible(true);
        discoverSerenadeForm.pack();
        discoverSerenadeForm.setLocationRelativeTo(null);
        discoverSerenadeForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemDiscoverActionPerformed

    private void jMenuItemHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemHelpActionPerformed
        // open the Help form
        HelpForm helpForm = new HelpForm();
        helpForm.setVisible(true);
        helpForm.pack();
        helpForm.setLocationRelativeTo(null);
        helpForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItemHelpActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        // open the About form
        AboutForm aboutForm = new AboutForm();
        aboutForm.setVisible(true);
        aboutForm.pack();
        aboutForm.setLocationRelativeTo(null);
        aboutForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        aboutForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_jMenuItemAboutActionPerformed

	// private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
	// //display the selected row data in the jtextfields
	//
	// //get the jlabel model
	// DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	//
	// // get the selected row index
	// int rIndex = jTable1.getSelectedRow();
	//
	// //display data
	//
	// jTextFieldDOI.setText(model.getValueAt(rIndex, 1).toString());
	// jTextFieldName.setText(model.getValueAt(rIndex, 7).toString());
	// jTextFieldTotDose.setText(model.getValueAt(rIndex, 3).toString());
	// String injectionMode = jComboBoxInjection.getSelectedItem().toString();
	// String ecosystem = jComboBoxEcosystem.getSelectedItem().toString();
	// jTextFieldTotTime.setText(model.getValueAt(rIndex, 2).toString());
	// jTextFieldMeasTime.setText(model.getValueAt(rIndex, 6).toString());
	// jTextFieldPH.setText(model.getValueAt(rIndex, 8).toString());
	// jTextFieldTemp.setText(model.getValueAt(rIndex, 9).toString());
	// jTextFieldConductivity.setText(model.getValueAt(rIndex, 10).toString());
	// jTextFieldDissOxygen.setText(model.getValueAt(rIndex, 11).toString());
	// jTextFieldWatORP.setText(model.getValueAt(rIndex, 12).toString());
	// jTextFieldSEDORP.setText(model.getValueAt(rIndex, 13).toString());
	// jTextFieldWatConcentration.setText(model.getValueAt(rIndex, 14).toString());
	// jTextFieldSedConcentration.setText(model.getValueAt(rIndex, 15).toString());
	// jTextFieldDissConcentration.setText(model.getValueAt(rIndex, 16).toString());
	// jTextFieldTBARS.setText(model.getValueAt(rIndex, 17).toString());
	// jTextFieldTAOC.setText(model.getValueAt(rIndex, 18).toString());
	// jTextFieldAlgae.setText(model.getValueAt(rIndex, 19).toString());
	// jTextFieldBacteria.setText(model.getValueAt(rIndex, 20).toString());
	//
	// }

	private void jButtonClearCheckboxesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonClearCheckboxesActionPerformed
		jPanelLegend.setVisible(false);
                jLabelTap.setVisible(false);
		jTextFieldSearch.setVisible(false);
		jLabelLowerB.setVisible(false);
		spinnerLowerB.setVisible(false);
		jLabelHigherB.setVisible(false);
		spinnerHeigherB.setVisible(false);
		// remove text from all jTextfields
		jComboBoxFields.setSelectedIndex(0);
		jComboBoxMT.setSelectedIndex(0);

		// clear the JTable first
		table.setModel(new DefaultTableModel(null,
				new String[] { "DOI", "Total metal concentration injected", "Total duration", "Ecosystem", "Primary consumer", 
                                    "Secondary consumer", "Benthic inoculum", "Planctonic inoculum", "Injection mode", "Metal", "Mineralogy", "Shape", 
                                    "Coating", "Size", "Contaminant",  "Sampling time", "pH", "Temperature", "Conductivity", "Dissolved oxygen", "TOC", 
                                    "ORP water", "ORP sediment", "ORP water/sediment interface", "# of particles in the water column", 
                                    "Total [metal] in surficial sediment", "Total [metal] in water", "Dissolved [metal] in water", "Total [metal] in layings", 
                                    "Total [metal] in digestive gland", "Total [metal] in adult", "Total [metal] in juveniles", "[Cu/Ni] in sediment", 
                                    "Speciation in digestive gland", "Speciation in layings", "Speciation in juveniles", "# adult", "# juveniles", "# picoplankton", 
                                    "# picobenthos", "# algae - water", "# algae - sediment", "TBARS", "TAOC" }));
		// reafficher le bouton search
		jButtonValidateSearch.setVisible(true);

	}// GEN-LAST:event_jButtonClearCheckboxesActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		System.exit(0);
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jButtonValidateSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButtonValidateSearchActionPerformed
                
                Color myNewBlue = new Color (111, 140, 222); 
                Color myColor = new Color(0Xee6a8c,true);  
                Color V, R, rouge, vert, bleu, gris, jaune;
                R = new Color(255, 0, 0, 100);
                rouge = new Color(255, 0, 0, 30);
                vert = new Color(0, 255, 0, 30);
                bleu = new Color(0, 0, 255, 30);
                gris = new Color(169,169,169,30);
                jaune =  new Color(255,255,0,30);
                V = new Color(154, 205, 50, 40);
                
		PreparedStatement stGetSearch;
		ResultSet searchResult;

		// déclaration et initialisation de LB et HB

		double LB = modelLB.getNumber().doubleValue();
		double HB = modelHB.getNumber().doubleValue();

		int intLB = (int) LB;
		int intHB = (int) HB;
		// keywords à chercher
		String valToSearch = jTextFieldSearch.getText();

		// déclaration et initialisation de mes 3 variables de
		String cond = "";
		String cond1 = "";
		String cond2 = "";

		// une varable selectedField pour renvoyer le combobox field sélectioné
		String selectedField = jComboBoxFields.getSelectedItem().toString().trim().toLowerCase();
   		switch (selectedField) {
		case "doi":
			cond1 = "LOWER(doi) LIKE '%" + valToSearch + "%'";
			break;
		case "total duration":
			cond1 = "Total_time BETWEEN " + intLB  + " AND " + intHB  + " ";
			break;
		case "total dose":
			cond1 = "Total_dose BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "ecosystem":
			cond1 = "LOWER(Ecosystem) LIKE '%" + valToSearch + "%'";
			break;
                case "primary consumer":
			cond1 = "LOWER(Primary_consumer) LIKE '%" + valToSearch + "%'";
			break;
                case "secondary consumer":
			cond1 = "LOWER(Secondary_consumer) LIKE '%" + valToSearch + "%'";
			break;
                case "benthic inoculum":
			cond1 = "LOWER(Benthic_inoculum) LIKE '%" + valToSearch + "%'";
			break;
                case "planctonic inoculum":
			cond1 = "LOWER(Planctonic_inoculum) LIKE '%" + valToSearch + "%'";
			break;        
		case "injection mode":
			cond1 = "LOWER(Injection_mode) LIKE '%" + valToSearch + "%'";
			break;
		case "contaminant":
			cond1 = "LOWER(Contaminant) LIKE '%" + valToSearch + "%'";
			break;
                case "metal":
			cond1 = "LOWER(Metal) LIKE '%" + valToSearch + "%'";
			break;
                case "mineralogy":
			cond1 = "LOWER(Mineralogy) LIKE '%" + valToSearch + "%'";
			break;
                case "shape":
			cond1 = "LOWER(Shape) LIKE '%" + valToSearch + "%'";
			break;
                case "coating":
			cond1 = "LOWER(Coating) LIKE '%" + valToSearch + "%'";
			break;
                case "size":
			cond1 = "Size BETWEEN " + intLB  + " AND " + intHB  + " ";
			break;
                case "sampling time":
			cond1 = "Sampling_time BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "ph":
			cond1 = "PH BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "temperature":
			cond1 = "Temperature BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "conductivity":
			cond1 = "Conductivity BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "dissolved oxygen":
			cond1 = "Dissolved_oxygen BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "toc":
			cond1 = "TOC BETWEEN " + LB + " AND " + HB + " ";
			break;        
		case "orp water":
			cond1 = "ORP_water BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "orp sediment":
			cond1 = "ORP_sediment BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "orp water/sediment interface":
			cond1 = "ORP_interface BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "number of particles in the water column":
			cond1 = "Particles_water BETWEEN " + LB + " AND " + HB + " ";
			break;   
                case "total [metal] in surficial sediment":
			cond1 = "Metal_sediment BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "total [metal] in water":
			cond1 = "Metal_water BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "dissolved [metal] in water":
			cond1 = "Dissolved_Metal BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "total [metal] in layings":
			cond1 = "Metal_layings BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "total [metal] in digestive gland":
			cond1 = "Metal_gland BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "total [metal] in adult":
			cond1 = "Metal_adult BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "total [metal] in juveniles":
			cond1 = "Metal_juveniles BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "[cu/ni] in sediment":
			cond1 = "CU_NI_sediment BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "speciation in digestive gland":
			cond1 = "Speciation_digestive BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "speciation in layings":
			cond1 = "Speciation_layings BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "speciation in juveniles":
			cond1 = "Speciation_juveniles BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "number adult":
			cond1 = "Adult BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "number juveniles":
			cond1 = "Juveniles BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "number picoplankton":
			cond1 = "Picoplankton BETWEEN " + LB + " AND " + HB + " ";
			break;
                case "number picobenthos":
			cond1 = "Picobenthos BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "number algae - water":
			cond1 = "Algae_water BETWEEN " + LB + " AND " + HB + " ";
			break;     
                case "number algae - sediment":
			cond1 = "Algae_sediment BETWEEN " + LB + " AND " + HB + " ";
			break;        
                case "tbars":
			cond1 = "TBARS BETWEEN " + LB + " AND " + HB + " ";
			break;
		case "taoc":
			cond1 = "TAOC BETWEEN " + LB + " AND " + HB + " ";
			break;
		default:
			cond1 = "";
		}

		// String selectedTime = (String) jComboBoxFields.getSelectedItem();
		int measureTime = jComboBoxMT.getSelectedIndex();
		if (measureTime != 0)
			cond2 = " Sampling_time=" + measureTime;

		System.out.println(cond);
		System.out.println(cond1);
		System.out.println(cond2);

		if (cond1.isEmpty()) {
			if (!(cond2.isEmpty()))
				cond = " AND " + cond2;

		} else {
			cond = " AND " + cond1;
			if (!(cond2.isEmpty()))
				cond = cond + " AND " + cond2;
		}

		try {

			String SearchGQuery = "SELECT `doi`, `Total_dose`, `Total_time`, `Ecosystem`, `Primary_consumer`,"
                + " `Secondary_consumer`, `Benthic_inocolum`, `Planctonic_inocolum`, `Injection_mode`, `Metal`, `Mineralogy`, `Shape`, `Coating`, `Size`, `Contaminant`,"
                + " `Sampling_time`, `PH`, `Temperature`, `Conductivity`, `Dissolved_oxygen`, `TOC`, `ORP_water`, `ORP_sediment`, `ORP_interface`,"
                + " `Particles_water`, `Metal_sediment`, `Metal_water`, `Dissolved_Metal`, `Metal_layings`, `Metal_gland`, `Metal_adult`, `Metal_juveniles`,"
                + " `CU_NI_sediment`, `Speciation_digestive`, `Speciation_layings`, `Speciation_juveniles`, `Adult`, `Juveniles`, `Picoplankton`,"
                + " `Picobenthos`, `Algae_water`, `Algae_sediment`, `TBARS`, `TAOC` FROM experiment e, sampling s, measure m "
                + "WHERE e.IDE = s.IDE AND s.IDS = m.IDS " + cond + " Order by Sampling_time";
                        
                        

			stGetSearch = my_connection.createConnection().prepareStatement(SearchGQuery);
			searchResult = stGetSearch.executeQuery();

			table.setModel(new DefaultTableModel(null,
					new Object[] { "DOI", "Total metal concentration injected", "Total duration", "Ecosystem", "Primary consumer", 
                                            "Secondary consumer", "Benthic inoculum", "Planctonic inoculum", "Injection mode", "Metal", "Mineralogy", "Shape", 
                                            "Coating", "Size", "Contaminant",  "Sampling time", "pH", "Temperature", "Conductivity", "Dissolved oxygen", "TOC", 
                                            "ORP water", "ORP sediment", "ORP water/sediment interface", "# of particles in the water column", 
                                            "Total [metal] in surficial sediment", "Total [metal] in water", "Dissolved [metal] in water", 
                                            "Total [metal] in layings", "Total [metal] in digestive gland", "Total [metal] in adult", "Total [metal] in juveniles", 
                                            "[Cu/Ni] in sediment", "Speciation in digestive gland", "Speciation in layings", "Speciation in juveniles", "# adult", 
                                            "# juveniles", "# picoplankton", "# picobenthos", "# algae - water", "# algae - sediment", "TBARS", "TAOC" }));


			
                        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

			Object[] row;
                        
                        row = new Object[44];
                        for (int i = 0; i < row.length; i++) {
                            if(yellowCol.contains(i)){
                            TableColumn tColumn = table.getColumnModel().getColumn(i);
                            tColumn.setHeaderRenderer(new ColumnColorRenderer(gris, Color.black));
                            tColumn.setCellRenderer(new ColumnColorRenderer(gris, Color.black));                  
                        } else  if(grayCol.contains(i)){
                            TableColumn tColumn = table.getColumnModel().getColumn(i);
                            tColumn.setHeaderRenderer(new ColumnColorRenderer(bleu, Color.black));
                            tColumn.setCellRenderer(new ColumnColorRenderer(bleu, Color.black));                  
                        } else  if(greenCol.contains(i)){
                            TableColumn tColumn = table.getColumnModel().getColumn(i);
                            tColumn.setHeaderRenderer(new ColumnColorRenderer(vert, Color.black));
                            tColumn.setCellRenderer(new ColumnColorRenderer(vert, Color.black));
                        } else  if(blueCol.contains(i)){
                            TableColumn tColumn = table.getColumnModel().getColumn(i);
                            tColumn.setHeaderRenderer(new ColumnColorRenderer(jaune, Color.black));
                            tColumn.setCellRenderer(new ColumnColorRenderer(jaune, Color.black));
                        }else
                        {
                            TableColumn tColumn = table.getColumnModel().getColumn(i);
                            tColumn.setHeaderRenderer(new ColumnColorRenderer(rouge, Color.black));
                            tColumn.setCellRenderer(new ColumnColorRenderer(rouge.brighter(), Color.black));
                        }
                }

			while (searchResult.next()) {
				row = new Object[44];
				for (int i = 0; i < row.length; i++) {
					row[i] = searchResult.getString(i + 1);
				}

				tableModel.addRow(row);
			}
                        
                        jPanelLegend.setVisible(true);

			stGetSearch.close();
                        searchResult.close();

			//
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// GEN-LAST:event_jButtonValidateSearchActionPerformed

	private void jComboBoxFieldsActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jComboBoxFieldsActionPerformed
		String selectedField = jComboBoxFields.getSelectedItem().toString().toLowerCase();

		if (TextFields.contains(selectedField)) {
			jLabelLowerB.setVisible(false);
			spinnerLowerB.setVisible(false);
			jLabelHigherB.setVisible(false);
			spinnerHeigherB.setVisible(false);

			jLabelTap.setVisible(true);
			jTextFieldSearch.setVisible(true);

			jTextFieldSearch.setEditable(true);

			jComboBoxMT.setVisible(true);

		} else if (NumberFields.contains(selectedField)) {
			jLabelTap.setVisible(false);
			jTextFieldSearch.setVisible(false);

			jTextFieldSearch.setEditable(false);

			jLabelLowerB.setVisible(true);
			spinnerLowerB.setVisible(true);
			jLabelHigherB.setVisible(true);
			spinnerHeigherB.setVisible(true);

			jComboBoxMT.setVisible(true);
		} else {// "all field" selected
			jLabelLowerB.setVisible(false);
			spinnerLowerB.setVisible(false);
			jLabelHigherB.setVisible(false);
			spinnerHeigherB.setVisible(false);

			jLabelTap.setVisible(false);
			jTextFieldSearch.setVisible(false);
			jTextFieldSearch.setEditable(false);

			jComboBoxMT.setVisible(true);
		}

	}// GEN-LAST:event_jComboBoxFieldsActionPerformed

	private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextFieldSearchActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextFieldSearchActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel.
		 * For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ManageMesocosmsFormLONG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ManageMesocosmsFormLONG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ManageMesocosmsFormLONG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ManageMesocosmsFormLONG.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManageMesocosmsFormLONG().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonClearCheckboxes;
    private javax.swing.JButton jButtonExportResults;
    private javax.swing.JButton jButtonPrintResults;
    private javax.swing.JButton jButtonReturn;
    private javax.swing.JButton jButtonValidateSearch;
    private javax.swing.JComboBox<String> jComboBoxFields;
    private javax.swing.JComboBox<String> jComboBoxMT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelHigherB;
    private javax.swing.JLabel jLabelLowerB;
    private javax.swing.JLabel jLabelTap;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemBibliograpgy;
    private javax.swing.JMenuItem jMenuItemDescription;
    private javax.swing.JMenuItem jMenuItemDictionary;
    private javax.swing.JMenuItem jMenuItemDiscover;
    private javax.swing.JMenuItem jMenuItemHelp;
    private javax.swing.JMenuItem jMenuItemInstitutions;
    private javax.swing.JMenuItem jMenuItemInstruments;
    private javax.swing.JMenuItem jMenuItemMeasurements;
    private javax.swing.JMenuItem jMenuItemMesocosms;
    private javax.swing.JMenuItem jMenuItemPeople;
    private javax.swing.JMenuItem jMenuItemProtocols;
    private javax.swing.JMenuItem jMenuItemPublications;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanelLegend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JSpinner spinnerHeigherB;
    private javax.swing.JSpinner spinnerLowerB;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
