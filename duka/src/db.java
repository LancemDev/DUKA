import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class db {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_lance_munyao150772";

    //MySQL Connector/J
    static final String USER = "root";
    static final String PASSWORD = "";


    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM tbl_attendant");

            int i = 0;
            while(resultSet.next()){
                String author =(String)resultSet.getObject(2);
                String title = (String) resultSet.getObject(3);
                System.out.printf("%s: %s\n", author, title);

            }
        }catch(ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null,ex);

        }catch(SQLException m) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, m);
        }
    }}