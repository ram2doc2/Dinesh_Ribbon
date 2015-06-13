/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dr.connection;

import com.dr.login.MainScreen;
import com.mysql.jdbc.MysqlDataTruncation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;

/**
 *
 * @author Eesha
 */
public class DatabaseConnection {

    private String driver = null;
    private String URL = null;
    private String USER = null;
    private String PASS = null;
    //Ram Doctor
    private Connection connection = null;
    public Statement statement = null;
    private MainScreen rm = new MainScreen();
    private static boolean status;
    private static String userType;
    private static String CONFIG_FILE_LOCATION = "d:\\config\\config.properties";
    //login user variable
    private static String user_name;
    SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /* Open MySQL database connection
     */
    public Connection dbConn() {
        try {
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream(CONFIG_FILE_LOCATION));
            } catch (FileNotFoundException fnfe) {
                String message = "Configuration file not found " + CONFIG_FILE_LOCATION;
                JOptionPane.showMessageDialog(new JFrame(), message, "Error!",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException ie) {
                String message = "Unable to load Configuration file ....";
                JOptionPane.showMessageDialog(new JFrame(), message, "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            driver = prop.getProperty("driver");
            URL = prop.getProperty("database");
            USER = prop.getProperty("dbuser");
            PASS = prop.getProperty("dbpassword");
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, USER, PASS);
            statement = connection.createStatement();
        } catch (ClassNotFoundException cnfe) {
            String message = "Database server not available ....";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            String message = "Database server offline!! Contact Administrator to check Config file to configure database";
            JOptionPane.showMessageDialog(new JFrame(), message, "Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        return connection;
    } //end dbConn method

    /*
     * Close MySQL database connection
     */
    public void dbClose() {
        try {
            // close all connections
            connection.close();
            statement.close();
        } catch (SQLException sql) {
        }
    }//end dbClose method

    /*
     * User type Getter & Setter method
     */
    public void setUserType(String userTy) {
        userType = userTy;
    }

    public String getUserType() {
        return userType;
    }

    /*
     * login status Getter & Setter method
     */
    public void setStatus(boolean stat) {
        status = stat;
    }

    public boolean getStatus() {
        return status;
    }

    /*
     * login user getter & setter method
     */
    public String getUser() {
        return user_name;
    }

    public void setUser(String username) {
        user_name = username;
    }

    /*
     * Method to insert user login status
     */
    public void loginDetails(String username) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
        String formatedDate = dateFormat.format(date);
        try {
            statement.executeUpdate("insert into login_details values('" + username + "','" + formatedDate + "')");
            statement.clearBatch();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }//end noteLogin method

    /*
     * check user type
     */
    public void checkUserType(String pass) {
        String SQLStatement = "Select * from login where password=md5('" + pass + "')";
        try {
            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            statement.clearBatch();
            while (rs.next()) {
                if (rs.getString(3).equals("Admin")) {
                    userType = "Admin";
                    setUserType(userType);
                } else {
                    userType = "User";
                    setUserType(userType);
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        dbClose();
    }//end checkUserType method

    /*
     * Method to Check password & userID........
     */
    public boolean checkLogin(String userID, String pass) {
        String user = userID;
        setUser(user);
        try {
            status = false;
            String SQLStatement = "Select * from login where password = md5(\""
                    + pass + "\")" + "and userID=\"" + userID + "\"";

            //Connect to database
            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            statement.clearBatch();

            if (!rs.next()) {
                String message = "Login Unsuccesful....";
                JOptionPane.showMessageDialog(new JFrame(), message,
                        "Fail!", JOptionPane.ERROR_MESSAGE);
                setStatus(false);
                return false;
            } else {
                loginDetails(user);
                setStatus(true);
                rm.setVisible(true);

                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        //close database connection        
        dbClose();

        return false;
    }//end checkLogin method

    public Boolean checkPass(String pass) throws SQLException {
        dbConn();
        String SQLStatement = "Select * from login where password=md5(\"" + pass + "\")";
        ResultSet rs = statement.executeQuery(SQLStatement);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public void adminChange(String userName, String currentPassword, String newPassword, String userType, JPasswordField pas1, JPasswordField pas2) throws SQLException {
        // if(getStatus() == true)
        //  {
        dbConn();
        boolean check = checkPass(currentPassword);
        System.out.println(check);
        if (check == true) {
            try {
                String SQLStatement = "update login set password = md5(\"" + newPassword + "\") where password = md5(\"" + currentPassword + "\")" + "AND userID='" + userName + "'";
                System.out.println(SQLStatement);
                int val = statement.executeUpdate(SQLStatement);
                System.out.println(val);
                if (val == 1) {
                    String message = "Changed " + userName + " password successfully....";
                    JOptionPane.showMessageDialog(new JFrame(), message,
                            "Success!", JOptionPane.PLAIN_MESSAGE);
                    pas1.setText("");
                    pas2.setText("");
                }
            } catch (SQLException sqle) {
                String message = "Unable to Change password, user /password invalid....";
                JOptionPane.showMessageDialog(new JFrame(), message,
                        "Error!", JOptionPane.ERROR_MESSAGE);
                sqle.printStackTrace();
                pas1.setText("");
                pas2.setText("");
            }
        } else {
            String message = "Unable to Change password, password invalid....";
            JOptionPane.showMessageDialog(new JFrame(), message,
                    "Erroor!", JOptionPane.ERROR_MESSAGE);
            pas1.setText("");
            pas2.setText("");
        }
        /* }else {
         String message = "Login Problem, Contact ur administrator.....";
         JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
         JOptionPane.ERROR_MESSAGE);
         }*/
    }

    public ArrayList getSupplierName() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT * FROM customer_details WHERE cust_ind='Supplier'";
            statement = null;

            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getString("cust_name"));
            }

            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }

    public ArrayList getBuyerName() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT * FROM customer_details WHERE cust_ind='Buyer'";
            statement = null;

            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getString("cust_name"));
            }

            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }

    public ArrayList getCustomerNameFromSaleDetails() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT DISTINCT bill_to FROM sales_details";
            statement = null;

            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getString("bill_to"));
            }

            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }
    
    public ArrayList getItemDescription() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT description FROM master_items ";
            statement = null;

            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getString("description"));
            }

            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }

    public int getLast_Inv_No() {
        dbConn();
        int invNo = 0;
        try {
            String SQLStatement = "SELECT inv_no FROM `sales_details` ORDER BY `inv_no` DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {
                invNo = Integer.parseInt(rs.getString("inv_no"));
            }
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return invNo;

    }

    public int getLast_dc_No() {
        dbConn();
        int dcNo = 0;
        try {
            String SQLStatement = "SELECT dc_no FROM `sales_details` ORDER BY `dc_no` DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {
                dcNo = Integer.parseInt(rs.getString("dc_no"));
            }
            //dcNo++;
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return dcNo;

    }

    public int getLast_pur_No() {
        dbConn();
        int purNo = 0;
        try {
            String SQLStatement = "SELECT pur_no FROM `purchase_details` ORDER BY `pur_no` DESC LIMIT 1";
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {
                purNo = Integer.parseInt(rs.getString("pur_no"));
            }
            purNo++;
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return purNo;

    }

    public Object getData(JTable table, int row_index, int col_index) {
        return table.getValueAt(row_index, col_index);
    }

//Add JTable Contents into databases
//Ram Doctor
    public int addTable(JTable jtable, String Invoice_No, String status_cd) {

        int addValue = 0;

        int count = jtable.getRowCount();
        try {
            dbConn();
            for (int i = 0; i < count; i++) {
                Object obj0 = getData(jtable, i, 0);
                Object obj1 = getData(jtable, i, 1);

                if (obj1 == null || obj1.equals("")) {
                    break;
                } else {

                    Object obj2 = getData(jtable, i, 2);
                    Object obj3 = getData(jtable, i, 3);
                    Object obj4 = getData(jtable, i, 4);
                    Object obj5 = getData(jtable, i, 5);
                    Object obj6 = getData(jtable, i, 6);
                    Object obj7 = getData(jtable, i, 7);

                    String Srno = obj0.toString();
                    String desc = obj1.toString();
                    String cost_p_mt = obj2.toString();
                    String cut = obj3.toString();
                    String rate_p_mt = obj4.toString();
                    String qnty = obj5.toString();
                    String amt = obj6.toString();
                    String costing = obj7.toString();

                    if (status_cd.equals("a")) {

                        String SQLStatement = "insert into sales_items values('" + Invoice_No + "','" + Srno + "','" + desc + "','" + cost_p_mt + "','" + cut + "','" + qnty + "','" + rate_p_mt + "','" + amt + "','" + costing + "')";
                        addValue = statement.executeUpdate(SQLStatement);
                    }//end of if(add)
                }//end of else
            }//end for loop

        } catch (MysqlDataTruncation msql) {
            String message = "Unable to add Sales items details,Wrong Data entry .....";
            JOptionPane.showMessageDialog(new JFrame(), message,
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sql) {
            String message = "Unable to add Sales items details,Invoice No Already exists.....";
            JOptionPane.showMessageDialog(new JFrame(), message,
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }
        dbClose();
        return addValue;
    }

    public int addTable_pur(JTable jtable, String pur_no, String status_cd) {

        int addValue = 0;

        int count = jtable.getRowCount();
        try {
            dbConn();
            for (int i = 0; i < count; i++) {
                Object obj0 = getData(jtable, i, 0);
                Object obj1 = getData(jtable, i, 1);

                if (obj1 == null || obj1.equals("")) {
                    break;
                } else {

                    Object obj2 = getData(jtable, i, 2);
                    Object obj3 = getData(jtable, i, 3);
                    Object obj4 = getData(jtable, i, 4);


                    String Srno = obj0.toString();
                    String desc = obj1.toString();
                    String weight = obj2.toString();
                    String rate = obj3.toString();
                    String amt = obj4.toString();


                    if (status_cd.equals("a")) {

                        String SQLStatement = "insert into purchase_items values('" + pur_no + "','" + Srno + "','" + desc + "','" + weight + "','" + rate + "','" + amt + "')";
                        addValue = statement.executeUpdate(SQLStatement);
                    }//end of if(add)
                }//end of else
            }//end for loop

        } catch (MysqlDataTruncation msql) {
            String message = "Unable to add Sales items details,Wrong Data entry .....";
            JOptionPane.showMessageDialog(new JFrame(), message,
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sql) {
            String message = "Unable to add Sales items details,Invoice No Already exists.....";
            JOptionPane.showMessageDialog(new JFrame(), message,
                    "Error!", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }
        dbClose();
        return addValue;
    }

    public ArrayList getInvoiceNo() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT inv_no FROM sales_details";
            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getInt("inv_no"));
            }
            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }

    public ArrayList getPurNo() {
        ArrayList list = new ArrayList();
        try {

            String SQLStatement = "SELECT pur_no FROM purchase_details";
            dbConn();
            ResultSet rs = statement.executeQuery(SQLStatement);
            while (rs.next()) {

                list.add(rs.getInt("pur_no"));
            }

            //close all database connections
            dbClose();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return list;
    }
    
    public ResultSet getSalesDetailsByInvoiceNo(String invoiceNo) {
        ResultSet resultSet = null;
        try {
            String SQLStatement = "SELECT * FROM sales_details Where inv_no='"+invoiceNo+"'";
            dbConn();
            resultSet = statement.executeQuery(SQLStatement);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultSet;
    }
    
    public ResultSet getItemIableByInvoiceNo(String invoiceNo) {
         ResultSet resultSet = null;
        try {
            String SQLStatement = "Select sr_no As 'Sr.No',description AS 'Description(Design No.)',cut AS 'cut(mt)',qnty AS 'Quantiy(Rolls)',rate AS 'Rate(per mt)',CAST((cut*qnty*rate) AS decimal(12,2)) 'Amount(Rs.)' from sales_items where inv_no='"+invoiceNo+"'";
            dbConn();
            resultSet = statement.executeQuery(SQLStatement);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultSet;
    }
}//end class DbConnection

