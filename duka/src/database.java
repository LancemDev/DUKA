import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class database  {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //jdbc:mysql://localhost:3306/mysql

    //com.mysql.cj.jdbc.Driver
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_lance_munyao150772";

    static final String USER = "root";
    static final String PASSWORD = "";
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

            statement.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException m) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, m);
        }
        return success;
    }
}