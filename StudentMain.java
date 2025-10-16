package View;
import Model.Student;
import Controller.StudentDAO;
import java.util.*;

public class StudentMain {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            StudentDAO dao = new StudentDAO();

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Marks");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Dept: ");
                        String dept = sc.next();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(id, name, dept, marks));
                        System.out.println("Student added.");
                        break;
                    case 2:
                        for (Student s : dao.getAllStudents()) {
                            System.out.println(s.getStudentID() + " " + s.getName() + " " + s.getDepartment() + " " + s.getMarks());
                        }
                        break;
                    case 3:
                        System.out.print("Enter ID to update: ");
                        id = sc.nextInt();
                        System.out.print("Enter new Marks: ");
                        marks = sc.nextDouble();
                        dao.updateStudentMarks(id, marks);
                        System.out.println("Updated successfully.");
                        break;
                    case 4:
                        System.out.print("Enter ID to delete: ");
                        id = sc.nextInt();
                        dao.deleteStudent(id);
                        System.out.println("Deleted successfully.");
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
