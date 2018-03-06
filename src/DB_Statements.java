import java.io.*;
import java.sql.*;

public class DB_Statements {

    private static Statement stmt = null;
    private static Connection con = DB_Connector.connect();
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;

    public void insertData(){

        Employee employee = new Employee(23, "Mads", 30000.0);
        String query1 = "insert into employee (emp) values(?)";
        String query2 = "select * from employee";

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(employee);

            byte[] employeeAsByte = baos.toByteArray();
            pst = con.prepareStatement(query1);

            ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsByte);

            pst.setBinaryStream(1, bais, employeeAsByte.length);

            pst.executeUpdate();
            System.out.println("\n--Query1 executed--");

        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("\n--Query1 failed to execute--");

        }

        try{
            stmt = con.createStatement();

            rs = stmt.executeQuery(query2);
            while(rs.next()){
                byte[] st = (byte[]) rs.getObject(1);
                ByteArrayInputStream baip = new ByteArrayInputStream(st);
                ObjectInputStream ois = new ObjectInputStream(baip);
                Employee emp = (Employee) ois.readObject();
            }

            System.out.println("\n--Retrieve executed--");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("\n--Retrieve failed to execute");
        }

    }

}
