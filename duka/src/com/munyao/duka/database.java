package com.munyao.duka;/*
*Lance Munyao
* 150772
* ICS 1.2D
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class database  {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jjdbc:mysql://duka.mysql.database.azure.com:3306/duka";

    static final String USER = "rootGuest";
    static final String PASSWORD = "mysql123LANCE";
    private Connection connection;
    private Statement statement;

    //The login function
    public boolean login(String username, String password) {
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT * FROM tbl_attendant WHERE attendant_username = '" +
                    username + "' AND attendant_password = '" + password + "'";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);
            success = rs.next();
            rs.close();
            //

            statement.close();
            connection.close();
            if(success){
                JOptionPane.showMessageDialog(null,"You have logged in successfully.\n Welcome");
            } else{
                JOptionPane.showMessageDialog(null,"You have not entered all the fields or Wrong credentials");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException m) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, m);
        }
        return success;
    }
    //The register function
    public boolean register(String names, String username, String password) {
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "INSERT INTO tbl_attendant (attendant_name," +
                    "attendant_username,attendant_password) VALUES ('" +
                    names + "', '" +
                    username + "', '" +
                    password + "')";
            System.out.println(query);
            statement.executeUpdate(query);
            statement.close();
            success = true;

            statement.close();
            connection.close();
            if(success){
                JOptionPane.showMessageDialog(null,"Welcome "+username+". Use "+password+" to login now");
            } else{
                JOptionPane.showMessageDialog(null,"");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException m) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, m);
        }
        return success;
    }

    //The upload items to db function
    public boolean upload(String itemName, int itemPrice, String itemDescription) {
        boolean success = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "INSERT INTO tbl_inventory (item_name," +
                    "item_price,item_description) VALUES ('" +
                    itemName + "', '" +
                    itemPrice + "', '" +
                    itemDescription + "')";
            System.out.println(query);
            statement.executeUpdate(query);
            statement.close();
            success = true;
            if(success){
                JOptionPane.showMessageDialog(null,itemName+" has successfully been uploaded to the inventory.");
            } else{
                JOptionPane.showMessageDialog(null,"Something is wrong!","Error",JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException m) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, m);
        }
        return success;
    }

    //View columns in combo box from the com.munyao.duka.database
    public void viewColumn(JComboBox comboBox) throws SQLException {
        boolean success=false;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            statement = connection.createStatement();

            String query = "SELECT Item_name FROM tbl_inventory;";
            System.out.println(query);
            ResultSet rs = statement.executeQuery(query);

            comboBox.removeAllItems();
            while(rs.next())
            {
                comboBox.addItem(rs.getString(1));
            }
            success = rs.next();
            rs.close();

            statement.close();
            connection.close();
        } catch (SQLException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    //Function that creates a model for the stock table:
    public DefaultTableModel createStockTableModel(){
        DefaultTableModel tableModelStock = new DefaultTableModel();
        tableModelStock.addColumn("Item ID");
        tableModelStock.addColumn("Item Name");
        tableModelStock.addColumn("Quantity");
        tableModelStock.addColumn("Item Price");

        return tableModelStock;
    }

    //Adding the model to the table:
    DefaultTableModel modelStock = createStockTableModel();

    //Delete row from the table
    //Takes in a textfield as the parameter
    public void deleteRecord(JTextField txtField) throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        String Options[] = {"Yes", "No"};
        int OptionSelection = JOptionPane.showOptionDialog(null,
                "Do you want to delete the record?", "Delete Record",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                Options, Options[1]);
        if (OptionSelection == 0) {
            String itemName = txtField.getText();
            try{
                String sql = "DELETE FROM tbl_inventory WHERE Item_name=?";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, itemName);

                int k = pst.executeUpdate();

                if(k==1){
                    JOptionPane.showMessageDialog(null, "Data has been deleted successfully.", "Modification Successful", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null, "Error: Record has not been deleted.");
                }

            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error: Record has not been deleted. Try again.");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);

            }


        }
    }

    //Create table

    public void table_update(JTable table1) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            PreparedStatement insert = connection.prepareStatement("SELECT * FROM tbl_inventory");
            ResultSet resultSet = insert.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            int col = rsmd.getColumnCount();
            String[] colName = new String[col];
            for (int i = 0; i < col; i++) {
                colName[i] = rsmd.getColumnName(i + 1);
                model.setColumnIdentifiers(colName);
            }
            String a, b, c, d, e;
            while (resultSet.next()) {
                a = resultSet.getString(1);
                b = resultSet.getString(2);
                c = resultSet.getString(3);
                d= resultSet.getString(4);

                String[] row = {a, b, c, d};
                model.addRow(row);


            }


        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}