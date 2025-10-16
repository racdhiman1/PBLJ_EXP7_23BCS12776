import java.sql.*;
import java.util.*;

public class PartB {
    static String url = "jdbc:mysql://localhost:3306/db_43ztunuvy";
    static String user = "user_43ztunuvy";
    static String password = "p43ztunuvy";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false);  // Enable transaction control

            while (true) {
                System.out.println("\n1. Insert Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        insertProduct(con, sc);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                    case 5:
                        con.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID: ");
        int id = sc.nextInt();
        System.out.print("Enter Product Name: ");
        String name = sc.next();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        String sql = "INSERT INTO Product VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();
            con.commit();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            con.rollback();
            System.out.println("Insertion failed. Rolled back.");
        }
    }

    static void viewProducts(Connection con) throws SQLException {
        String sql = "SELECT * FROM Product";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ProductID\tName\tPrice\tQuantity");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getInt(4));
            }
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new price: ");
        double price = sc.nextDouble();

        String sql = "UPDATE Product SET Price=? WHERE ProductID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setInt(2, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Product updated successfully.");
        } catch (SQLException e) {
            con.rollback();
            System.out.println("Update failed. Rolled back.");
        }
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to delete: ");
        int id = sc.nextInt();
        String sql = "DELETE FROM Product WHERE ProductID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            con.commit();
            System.out.println("Product deleted successfully.");
        } catch (SQLException e) {
            con.rollback();
            System.out.println("Deletion failed. Rolled back.");
        }
    }
}
