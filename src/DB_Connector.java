import java.sql.*;

public class DB_Connector {

    // PostgreSQL JDBC Driver
    private static String url = "jdbc:postgresql://localhost:5432/Test";
    // user name
    private static String user = "postgres";
    // pass word
    private static String pass = "";
    private static Connection conn = null;

    public static Connection connect(){
        System.out.println("\n--Connecting to postgreSQL JDBC--");
        // Locate PostgreSQL JDBC Driver
        try {
            conn = DriverManager.getConnection(url, user, pass);
        }
        // Catch exceptions if JDBC is not found
        catch (Exception ex){
            ex.printStackTrace();
        }

        // if connection successful
        if (conn != null) {
            System.out.println("\n--It worked! Connection successful--");
        } else {
            System.out.println("\n--Connection failed--");
        }
        return conn;

    }
}
