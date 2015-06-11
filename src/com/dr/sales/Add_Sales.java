/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.sales;

import com.dr.login.MainScreen;
import com.dr.connection.DatabaseConnection;
import com.dr.utils.Calendarium;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import com.dr.utils.Validation;

/**
 *
 * @author RAM
 */
public class Add_Sales extends javax.swing.JFrame implements ActionListener{

    DatabaseConnection db=new DatabaseConnection();
    Validation valid=new Validation();
    private static final int DELAY_TIME = 165;
    
    JComboBox ItemDescComboBox;
    /**
     * Creates new form Add_Sales
     */
    public Add_Sales() {
        initComponents();
        Timer timer = new Timer(DELAY_TIME,this);
        timer.start();
        setLocationRelativeTo(null);
        
        int lastInvNo=db.getLast_Inv_No();
        jTextField3.setText(String.valueOf(++lastInvNo));
        
        int lastdcNo=db.getLast_dc_No();
        jTextField1.setText(String.valueOf(++lastdcNo));
        
        
        Iterator<String> iterator = db.getBuyerName().iterator();
		while (iterator.hasNext()) {
                        jComboBox1.addItem(iterator.next());
                }
                
                
        ItemDescComboBox = new JComboBox(new Object[] {});
//        ItemDescComboBox.setEditable(true);
//        ItemDescComboBox.addItem("Select");
//        ItemDescComboBox.setSelectedIndex(0);
//        for(int i=0;i<jTable1.getRowCount();i++)
//        {
//            jTable1.setValueAt("Select", i, 1);
//        }
        
        jTable1.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor (ItemDescComboBox));
        
        Iterator<String> iterator2 = db.getItemDescription().iterator();
		while (iterator2.hasNext()) {
                        ItemDescComboBox.addItem(iterator2.next());       
		}
                
        ItemDescComboBox.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent evt) {
                      
                      if(ItemDescComboBox.getSelectedItem()==null || ItemDescComboBox.getSelectedItem().equals(""))
                      {
//                          try{
//                           int row=jTable1.getSelectedRow();
//                           jTable1.setValueAt("", row, 2);
//                           jTable1.setValueAt("", row, 3);
//                           jTable1.setValueAt("", row, 4);
//                          }
//                          catch(Exception e){
//                              e.getMessage();
//                          }
                      }
                      else
                      {
                          try
                          {
                            int row;
                            ResultSet rs;
                            row=jTable1.getSelectedRow();
                            String desc=ItemDescComboBox.getSelectedItem().toString();
                            db.dbConn();
                            String sql="Select * FROM master_items WHERE description='"+desc+"'";
                            System.out.println(sql);
                            rs=db.statement.executeQuery(sql);
                            while(rs.next())
                            {
                                jTable1.setValueAt(rs.getDouble("cost_per_mt"), row, 2);
                                jTable1.setValueAt(rs.getDouble("cut"), row, 3);
                                jTable1.setValueAt(rs.getDouble("rate_per_mt"), row, 4);
                            }
                            db.dbClose();
                           }
                           catch(SQLException e)
                           {
                                e.printStackTrace();
                           }
                           catch(Exception e)
                           {
                                e.printStackTrace();
                           }
                          
                          
                          
                      }
                  // throw new UnsupportedOperationException("Not supported yet.");
                    
                      }
                  });
                
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e)  {
      String oldText = scrollLabel.getText();
      String newText = oldText.substring(1) + oldText.substring(0, 1);
      scrollLabel.setText( newText );
    }
    
    public void cleartable()
    {
         for (int i = 0; i < jTable1.getRowCount(); i++){
                     for(int k = 1; k < jTable1.getColumnCount(); k++) {
                      jTable1.setValueAt(null, i, k);
                      }
                 }
                 jTable1.changeSelection(0,0, true, false);
                 jTable1.clearSelection();
                 jTable1.editCellAt(0,0);
    }

    public void cleartext()
    {
        jComboBox1.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        
        int lastInvNo=db.getLast_Inv_No();
        jTextField3.setText(String.valueOf(++lastInvNo));
        
        int lastdcNo=db.getLast_dc_No();
        jTextField1.setText(String.valueOf(++lastdcNo));
        
        cleartable();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        scrollLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Sales Invoice");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setText("Sales Invoice");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 153));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dr/images/DINESH LOGO_small.png"))); // NOI18N
        jLabel6.setText("DINESH RIBBONS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        scrollLabel.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        scrollLabel.setForeground(new java.awt.Color(0, 204, 204));
        scrollLabel.setText("    Developed and Designed by - RAM DOCTOR                                                                                                                                                                                                                           ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(scrollLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sales Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Bill To :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 130, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Delivery Challan No. :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 130, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Delivery Date :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 130, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Invoice No. :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 130, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Invoice Date :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 130, -1));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 180, -1));

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 180, -1));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 180, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 510, -1, -1));

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jPanel2.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 180, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Payment Due Date :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 130, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Total Amount :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 130, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Total Costing :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 130, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Gross Profit :");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 130, -1));

        jTextField5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jPanel2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 180, -1));

        jTextField6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField6MouseClicked(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 180, -1));

        jTextField7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, 180, -1));

        jTextField8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 180, -1));

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 180, -1));

        jScrollPane2.setBackground(new java.awt.Color(0, 204, 153));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null}
            },
            new String [] {
                "Sr No.", "Description", "Cost/mt", "Cut (mt)", "Rate/mt", "Quantity", "Amount", "Costing"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(0, 204, 153));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 670, 160));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dr/images/calendar_icon1(2).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 30, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dr/images/calendar_icon1(2).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, 30, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dr/images/calendar_icon1(2).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 30, -1));

        jButton6.setText("Clear Table");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 380, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        valid.varcharValidator(evt, jTextField1, 20);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        valid.integerValidator(evt, jTextField3, 6);
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean Flag=false;
        String bill_to=jComboBox1.getSelectedItem().toString();
        String dc_no=jTextField1.getText();
        String inv_no=jTextField3.getText();
        String t_amt=jTextField6.getText();
        String t_costing=jTextField7.getText();
        String gross_profit=jTextField8.getText();
        String dc_date=null;
        String inv_date=null;
        String due_date=null;

        Date d = new Date();
        DateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String udate= df.format(d);

        if(Flag==false)
        {
            if(jComboBox1.getSelectedItem().equals("Select"))
            {
                String message = "First Select Bill To: ..";
                JOptionPane.showMessageDialog(new JFrame(), message,
                    "WARNING", JOptionPane.WARNING_MESSAGE);
                Flag = true;
            }
        }

        if(Flag==false)
        {
            if(jTextField2.getText().equals("") || jTextField2.getText()==null)
            {
                String message1 = "Delivery_Challan Date  Should not be left empty!!";
                JOptionPane.showMessageDialog(new JFrame(), message1,
                    "Warning", JOptionPane.WARNING_MESSAGE);
                Flag=true;
            }
            
            if(jTextField4.getText().equals("") || jTextField4.getText()==null)
            {
                String message1 = "Invoice_Date  Should not be left empty!!";
                JOptionPane.showMessageDialog(new JFrame(), message1,
                    "Warning", JOptionPane.WARNING_MESSAGE);
                Flag=true;
            }

        }
        
        if(Flag==false)
        {
            String dt=jTextField2.getText().toString();
            
            try {
                Date d1=new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                 System.out.println(d1);
                 
                 DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
                 dc_date=df1.format(d1);
                 System.out.println(dc_date);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,"Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                Flag=true;
                //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

            }
 
        }
        
        if(Flag==false)
        {
            
                String dt=jTextField4.getText().toString();

                try {
                    Date d1=new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                     System.out.println(d1);

                     DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
                     inv_date=df1.format(d1);
                     System.out.println(inv_date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                    Flag=true;
                    //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

                }
            
        }
        
        if(Flag==false)
        {
            if(!(jTextField5.getText().equals("")))
            {
                String dt=jTextField5.getText().toString();

                try {
                    Date d1=new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                     System.out.println(d1);

                     DateFormat df1=new SimpleDateFormat("yyyy-MM-dd");
                     due_date=df1.format(d1);
                     System.out.println(due_date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null,"Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                    Flag=true;
                    //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }

        if(Flag==false)
        {

            try{
                db.dbConn();
                String sql="INSERT INTO sales_details VALUES ('"+bill_to+"','"+dc_no+"','"+dc_date+"','"+inv_no+"','"+inv_date+"','"+due_date+"','"+t_amt+"','"+t_costing+"','"+gross_profit+"','a','"+db.getUser()+"','"+udate+"','','')";
                System.out.println(sql);
                int val=db.statement.executeUpdate(sql);
                db.dbClose();
                if(val!=0)
                {
                    int val1=db.addTable(jTable1, inv_no,"a");
                    if(val1!=0)
                    {
                    String message1 = "Sales Entry Added Successfully.....";
                    JOptionPane.showMessageDialog(new JFrame(), message1,
                        "Success", JOptionPane.PLAIN_MESSAGE);
                    cleartext();
                    }
                    else
                    {
                        db.dbConn();
                        String sql1="DELETE FROM sales_details WHERE inv_no='"+inv_no+"'";
                        System.out.println(sql1);
                        db.statement.executeUpdate(sql1);
                        db.dbClose();
                        String message1 = "Unable to Add Sales Entry.....";
                        JOptionPane.showMessageDialog(new JFrame(), message1,
                        "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    String message1 = "Unable to Add Sales Entry.....";
                    JOptionPane.showMessageDialog(new JFrame(), message1,
                        "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
                db.dbClose();
            }catch (SQLException sqle) {
                sqle.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cleartext();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        //valid.decimalValidator(evt, jTextField6,10,2);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        valid.decimalValidator(evt, jTextField7,10,2);
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        valid.decimalValidator(evt, jTextField8,10,2);
    }//GEN-LAST:event_jTextField8KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        jTextField2.setText(cal.setPickedDate());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        jTextField4.setText(cal.setPickedDate());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        jTextField5.setText(cal.setPickedDate());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        cleartable();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        int row=jTable1.getSelectedRow();
        int col=jTable1.getSelectedColumn();
        if(col==6 && row>=0)
        {
            switch(evt.getKeyCode())
                     {
                       case KeyEvent.VK_TAB: System.out.println("Tab Key Event performed");
                                             if(jTable1.getValueAt(row,5)==null || jTable1.getValueAt(row, 5).equals(""))
                                             {
                                                 
                                             }
                                             else
                                             {
                                                 double cost_per_mt= Double.parseDouble(jTable1.getValueAt(row,2).toString());
                                                 double cut= Double.parseDouble(jTable1.getValueAt(row,3).toString());
                                                 double rate_per_mt=Double.parseDouble(jTable1.getValueAt(row,4).toString());
                                                 double qnty=Double.parseDouble(jTable1.getValueAt(row,5).toString());
                                                 System.out.println("Cost_per_mt="+cost_per_mt+"\tCut="+cut+"\trate_per_mt="+rate_per_mt+"\tqnty"+qnty);
                                                 
                                                 double amount=Math.floor(cut*rate_per_mt*qnty);
                                                 System.out.println("amount="+amount);
                                                 jTable1.setValueAt(amount, row,6);
                                                 
                                                 double costing=Math.floor(cut*cost_per_mt*qnty);
                                                 System.out.println("costing="+costing);
                                                 jTable1.setValueAt(costing, row,7);
                                             }
                                             break;
                     }
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTextField6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField6MouseClicked
        // TODO add your handling code here:
        jTable1.editCellAt(0,0);
        double total_amt=0;
        double total_costing=0;
        for(int i=0;i<jTable1.getRowCount();i++)
        {
            
            if(jTable1.getValueAt(i,1) == null || jTable1.getValueAt(i,1).equals(""))
            {
              break;  
            }
            else
            {
                double amt=Double.parseDouble(jTable1.getValueAt(i, 6).toString());
                double costing=Double.parseDouble(jTable1.getValueAt(i, 7).toString());
                total_amt=total_amt+amt;
                total_costing=total_costing+costing;
            }
        }
        
        double gross_profit=total_amt-total_costing;
        jTextField6.setText(String.valueOf(total_amt));
        jTextField7.setText(String.valueOf(total_costing));
        jTextField8.setText(String.valueOf(gross_profit));
        
    }//GEN-LAST:event_jTextField6MouseClicked

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        valid.dateValidator(jTextField2);
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
        valid.dateValidator(jTextField4);
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        // TODO add your handling code here:
        valid.dateValidator(jTextField5);
    }//GEN-LAST:event_jTextField5FocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        MainScreen ss= new MainScreen();
        
        setVisible(false);
        ss.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add_Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Sales().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel scrollLabel;
    // End of variables declaration//GEN-END:variables
}
