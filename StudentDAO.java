package Controller;
import Model.Student;
import java.sql.*;
import java.util.*;

public class StudentDAO {
    private Connection con;

    public StudentDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_43ztunuvy", "user_43ztunuvy", "p43ztunuvy");
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        }
        return list;
    }

    public void updateStudentMarks(int id, double marks) throws SQLException {
        String sql = "UPDATE Student SET Marks=? WHERE StudentID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, marks);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
