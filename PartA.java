import java.sql.*;

public class PartA {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_43ztunuvy"; 
        String user = "user_43ztunuvy";
        String password = "p43ztunuvy";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" +
                                   rs.getString("Name") + "\t" +
                                   rs.getDouble("Salary"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
