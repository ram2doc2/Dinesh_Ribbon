/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.sales;

import com.dr.print.PrintChallan;
import com.dr.login.MainScreen;
import com.dr.connection.DatabaseConnection;
import com.dr.cutomerDetails.Add_Customer_entry;
import com.dr.items.Add_Items;
import com.dr.utils.Calendarium;
import com.dr.utils.Utilities;
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
import java.util.Calendar;
import javax.swing.JTable;

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
        
        grossProfitLabel.setVisible(false);
        grossProfitTextField.setVisible(false);
        totalCostingLabel.setVisible(false);
        totalCostingTextField.setVisible(false);
        
        int dcNo  = db.getLastChallanNo();
        Calendar cal = Calendar.getInstance();
        if(cal.get(Calendar.MONTH) == 3 && cal.get(Calendar.DATE) < 10 && dcNo > 70) {
            challanNoTextField.setText("1");
        } else {
            challanNoTextField.setText(dcNo + 1 + "");
        }
        
        setCurrentDateToAllDatesFields();
        
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
        deliveryDateTextField.setText("");
        challanDateTextField.setText("");
        paymentDueDateTextField.setText("");
        totalAmountTextField.setText("");
        totalCostingTextField.setText("");
        grossProfitTextField.setText("");
        
        challanNoTextField.setText(db.getLastChallanNo() + 1 + "");
        setCurrentDateToAllDatesFields();
        cleartable();
        
    }
    
    private void setCurrentDateToAllDatesFields() {
        deliveryDateTextField.setText(Utilities.getCurrentStadardDate());
        challanDateTextField.setText(Utilities.getCurrentStadardDate());
        paymentDueDateTextField.setText(Utilities.getCurrentStadardDate());
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
        billToLabel = new javax.swing.JLabel();
        deliveryDateLabel = new javax.swing.JLabel();
        challanNoLabel = new javax.swing.JLabel();
        challanDateLabel = new javax.swing.JLabel();
        deliveryDateTextField = new javax.swing.JTextField();
        challanNoTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        challanDateTextField = new javax.swing.JTextField();
        paymentDueDateLabel = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        totalCostingLabel = new javax.swing.JLabel();
        grossProfitLabel = new javax.swing.JLabel();
        paymentDueDateTextField = new javax.swing.JTextField();
        totalAmountTextField = new javax.swing.JTextField();
        totalCostingTextField = new javax.swing.JTextField();
        grossProfitTextField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        addNewCustomerButton = new javax.swing.JButton();
        addNewItemButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();

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
                .addComponent(scrollLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 899, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sales Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 0, 24))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        billToLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        billToLabel.setText("Bill To :");
        jPanel2.add(billToLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 130, -1));

        deliveryDateLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        deliveryDateLabel.setText("Delivery Date :");
        jPanel2.add(deliveryDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 130, -1));

        challanNoLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        challanNoLabel.setText("Challan No. :");
        jPanel2.add(challanNoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 130, -1));

        challanDateLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        challanDateLabel.setText("Challan Date :");
        jPanel2.add(challanDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 130, -1));

        deliveryDateTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        deliveryDateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                deliveryDateTextFieldFocusLost(evt);
            }
        });
        jPanel2.add(deliveryDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 180, -1));

        challanNoTextField.setEditable(false);
        challanNoTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        challanNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                challanNoTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(challanNoTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 180, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 490, -1, -1));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 490, -1, -1));

        challanDateTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        challanDateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                challanDateTextFieldFocusLost(evt);
            }
        });
        jPanel2.add(challanDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 180, -1));

        paymentDueDateLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        paymentDueDateLabel.setText("Payment Due Date :");
        jPanel2.add(paymentDueDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 130, -1));

        totalAmountLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        totalAmountLabel.setText("Total Amount :");
        jPanel2.add(totalAmountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 90, 20));

        totalCostingLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        totalCostingLabel.setText("Total Costing :");
        jPanel2.add(totalCostingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 90, 20));

        grossProfitLabel.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        grossProfitLabel.setText("Gross Profit :");
        jPanel2.add(grossProfitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, 90, 20));

        paymentDueDateTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        paymentDueDateTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                paymentDueDateTextFieldFocusLost(evt);
            }
        });
        jPanel2.add(paymentDueDateTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 180, -1));

        totalAmountTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        totalAmountTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalAmountTextFieldMouseClicked(evt);
            }
        });
        totalAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalAmountTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(totalAmountTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 120, -1));

        totalCostingTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        totalCostingTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalCostingTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(totalCostingTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 120, -1));

        grossProfitTextField.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        grossProfitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossProfitTextFieldActionPerformed(evt);
            }
        });
        grossProfitTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                grossProfitTextFieldKeyReleased(evt);
            }
        });
        jPanel2.add(grossProfitTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 120, -1));

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
                {"6", null, null, null, null, null, null, null},
                {"7", "", null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null}
            },
            new String [] {
                "Sr No.", "Description", "Yarn/mt", "Cut (mt)", "Rate/mt", "Quantity", "Amount", "Yarn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, false, true
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 670, 230));

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
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        addNewCustomerButton.setText("Add New Customer");
        addNewCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCustomerButtonActionPerformed(evt);
            }
        });
        jPanel2.add(addNewCustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 150, -1));

        addNewItemButton.setText("Add New Item");
        addNewItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewItemButtonActionPerformed(evt);
            }
        });
        jPanel2.add(addNewItemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, -1, -1));

        printButton.setText("Print");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });
        jPanel2.add(printButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void challanNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_challanNoTextFieldKeyReleased
        // TODO add your handling code here:
        valid.integerValidator(evt, challanNoTextField, 6);
    }//GEN-LAST:event_challanNoTextFieldKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveSales();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cleartext();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void totalAmountTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalAmountTextFieldKeyReleased
        // TODO add your handling code here:
        //valid.decimalValidator(evt, jTextField6,10,2);
    }//GEN-LAST:event_totalAmountTextFieldKeyReleased

    private void totalCostingTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalCostingTextFieldKeyReleased
        // TODO add your handling code here:
        valid.decimalValidator(evt, totalCostingTextField,10,2);
    }//GEN-LAST:event_totalCostingTextFieldKeyReleased

    private void grossProfitTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grossProfitTextFieldKeyReleased
        // TODO add your handling code here:
        valid.decimalValidator(evt, grossProfitTextField,10,2);
    }//GEN-LAST:event_grossProfitTextFieldKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        deliveryDateTextField.setText(cal.setPickedDate());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        challanDateTextField.setText(cal.setPickedDate());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Calendarium cal = new Calendarium(new JFrame());
        cal.displayDate();
        paymentDueDateTextField.setText(cal.setPickedDate());
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
            switch (evt.getKeyCode()) {
                case KeyEvent.VK_TAB:
                    System.out.println("Tab Key Event performed");
                    if (jTable1.getValueAt(row, 5) == null || jTable1.getValueAt(row, 5).equals("")) {

                    } else {
                        double cost_per_mt = Double.parseDouble(jTable1.getValueAt(row, 2).toString());
                        double cut = Double.parseDouble(jTable1.getValueAt(row, 3).toString());
                        double rate_per_mt = Double.parseDouble(jTable1.getValueAt(row, 4).toString());
                        double qnty = Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                        System.out.println("Cost_per_mt=" + cost_per_mt + "\tCut=" + cut + "\trate_per_mt=" + rate_per_mt + "\tqnty" + qnty);

                        double amount = Math.floor(cut * rate_per_mt * qnty);
                        System.out.println("amount=" + amount);
                        jTable1.setValueAt(amount, row, 6);

                        double costing = Math.floor(cut * cost_per_mt * qnty);
                        System.out.println("costing=" + costing);
                        jTable1.setValueAt(costing, row, 7);
                    }
                    break;
            }
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void totalAmountTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalAmountTextFieldMouseClicked
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
               /* String qntyVal = String.valueOf(jTable1.getValueAt(i, 5));
                if (qntyVal.equals("null") || qntyVal.equals("") || qntyVal.isEmpty()) {
                    String message1 = "Enter quantity for Sr.No " + (i + 1) + ", then press tab";
                    JOptionPane.showMessageDialog(new JFrame(), message1,
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }*/
                double amt=Double.parseDouble(jTable1.getValueAt(i, 6).toString());
                double costing=Double.parseDouble(jTable1.getValueAt(i, 7).toString());
                total_amt=total_amt+amt;
                total_costing=total_costing+costing;
            }
        }
        
        double gross_profit=total_amt-total_costing;
        totalAmountTextField.setText(String.valueOf(total_amt));
        totalCostingTextField.setText(String.valueOf(total_costing));
        grossProfitTextField.setText(String.valueOf(gross_profit));
        
    }//GEN-LAST:event_totalAmountTextFieldMouseClicked

    private void deliveryDateTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deliveryDateTextFieldFocusLost
        // TODO add your handling code here:
        valid.dateValidator(deliveryDateTextField);
    }//GEN-LAST:event_deliveryDateTextFieldFocusLost

    private void challanDateTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_challanDateTextFieldFocusLost
        // TODO add your handling code here:
        valid.dateValidator(challanDateTextField);
    }//GEN-LAST:event_challanDateTextFieldFocusLost

    private void paymentDueDateTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_paymentDueDateTextFieldFocusLost
        // TODO add your handling code here:
        valid.dateValidator(paymentDueDateTextField);
    }//GEN-LAST:event_paymentDueDateTextFieldFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        MainScreen ss= new MainScreen();
        
        setVisible(false);
        ss.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void grossProfitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossProfitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossProfitTextFieldActionPerformed

    private void addNewCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCustomerButtonActionPerformed
        // TODO add your handling code here:
        Add_Customer_entry customer_entry = new Add_Customer_entry();
        this.setVisible(false);
        customer_entry.setVisible(true);
    }//GEN-LAST:event_addNewCustomerButtonActionPerformed

    private void addNewItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewItemButtonActionPerformed
        // TODO add your handling code here:
        Add_Items add_Items = new Add_Items();
        this.setVisible(false);
        add_Items.setVisible(true);
    }//GEN-LAST:event_addNewItemButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        // TODO add your handling code here:
        boolean print = true;
        if (!isItemTableEmpty(jTable1, 3)) {
            int val = JOptionPane.showOptionDialog(null, "Do you want save this current invoice", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (val == 0) {
                boolean saved = saveSales();
                if (!saved) {
                    print = false;
                }
            }
        }
        if (print) {
            PrintChallan printChallan = new PrintChallan();
            this.setVisible(false);
            printChallan.setVisible(true);
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private boolean isItemTableEmpty(JTable jTable, int atColumnIndex) {
        boolean result = true;
        for (int i = 0; i < jTable.getRowCount(); i++) {
            String value = String.valueOf(jTable.getValueAt(i, atColumnIndex - 1));
            if (!(value == null || value.equals("null") || value.trim().isEmpty())) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    private boolean saveSales() {
        boolean flag = true;
        String bill_to = jComboBox1.getSelectedItem().toString();
        String challanNo = challanNoTextField.getText();
        String t_amt = totalAmountTextField.getText();
        String t_costing = totalCostingTextField.getText();
        String gross_profit = grossProfitTextField.getText();
        String dc_date = null;
        String inv_date = null;
        String due_date = null;

        Date d = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String udate = df.format(d);

        if (flag) {
            if (jComboBox1.getSelectedItem().equals("Select")) {
                String message = "First Select Bill To: ..";
                JOptionPane.showMessageDialog(new JFrame(), message,
                        "WARNING", JOptionPane.WARNING_MESSAGE);
                flag = false;
            }
        }

        if (flag) {
            if (deliveryDateTextField.getText().equals("") || deliveryDateTextField.getText() == null) {
                String message1 = "Delivery_Challan Date  Should not be left empty!!";
                JOptionPane.showMessageDialog(new JFrame(), message1,
                        "Warning", JOptionPane.WARNING_MESSAGE);
                flag = false;
            }
        }

        if(flag) {
            if (challanDateTextField.getText().equals("") || challanDateTextField.getText() == null) {
                String message1 = "Invoice_Date  Should not be left empty!!";
                JOptionPane.showMessageDialog(new JFrame(), message1,
                        "Warning", JOptionPane.WARNING_MESSAGE);
                flag = false;
            }
        }
        
        if (flag) {
            String dt = deliveryDateTextField.getText().toString();

            try {
                Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                System.out.println(d1);

                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                dc_date = df1.format(d1);
                System.out.println(dc_date);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                flag = false;
                //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

        if (flag) {

            String dt = challanDateTextField.getText();

            try {
                Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                System.out.println(d1);

                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                inv_date = df1.format(d1);
                System.out.println(inv_date);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                flag = false;
                //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

        if (flag) {
            if (!(paymentDueDateTextField.getText().equals(""))) {
                String dt = paymentDueDateTextField.getText().toString();

                try {
                    Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(dt);
                    System.out.println(d1);

                    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                    due_date = df1.format(d1);
                    System.out.println(due_date);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Date.\nDate Format should be (dd-MM-yyyy)");
                    flag = false;
                    //Logger.getLogger(Amount_paid.class.getName()).log(Level.SEVERE, null, ex);

                }
            }
        }
        
        if(flag && totalAmountTextField.getText().equals("")) {
            totalAmountTextFieldMouseClicked(null);
        }

        if (flag) {

            try {
                String inv_no = db.getLastInvNo() + 1 + "";
                db.dbConn();
                String sql = "INSERT INTO sales_details VALUES ('" + bill_to + "','" + challanNo + "','" + dc_date + "','" + inv_no + "','" + inv_date + "','" + due_date + "','" + t_amt + "','" + t_costing + "','" + gross_profit + "','a','" + db.getUser() + "','" + udate + "','','')";
                System.out.println(sql);
                int val = db.statement.executeUpdate(sql);
                db.dbClose();
                if (val != 0) {
                    int val1 = db.addTable(jTable1, inv_no, "a");
                    if (val1 != 0) {
                        String message1 = "Sales Entry Added Successfully.....";
                        JOptionPane.showMessageDialog(new JFrame(), message1,
                                "Success", JOptionPane.PLAIN_MESSAGE);
                        cleartext();
                    } else {
                        db.dbConn();
                        String sql1 = "DELETE FROM sales_details WHERE inv_no='" + inv_no + "'";
                        System.out.println(sql1);
                        db.statement.executeUpdate(sql1);
                        db.dbClose();
                        String message1 = "Unable to Add Sales Entry.....";
                        JOptionPane.showMessageDialog(new JFrame(), message1,
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    String message1 = "Unable to Add Sales Entry.....";
                    JOptionPane.showMessageDialog(new JFrame(), message1,
                            "Error", JOptionPane.ERROR_MESSAGE);

                }
                db.dbClose();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return flag;
    }
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
    private javax.swing.JButton addNewCustomerButton;
    private javax.swing.JButton addNewItemButton;
    private javax.swing.JLabel billToLabel;
    private javax.swing.JLabel challanDateLabel;
    private javax.swing.JTextField challanDateTextField;
    private javax.swing.JLabel challanNoLabel;
    private javax.swing.JTextField challanNoTextField;
    private javax.swing.JLabel deliveryDateLabel;
    private javax.swing.JTextField deliveryDateTextField;
    private javax.swing.JLabel grossProfitLabel;
    private javax.swing.JTextField grossProfitTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel paymentDueDateLabel;
    private javax.swing.JTextField paymentDueDateTextField;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel scrollLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JTextField totalAmountTextField;
    private javax.swing.JLabel totalCostingLabel;
    private javax.swing.JTextField totalCostingTextField;
    // End of variables declaration//GEN-END:variables
}
