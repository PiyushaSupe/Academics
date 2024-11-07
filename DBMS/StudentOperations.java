import java.sql.*;

public class StudentOperations {

    static Connection con;
    static Statement stmt;

    public static void main(String[] args) {
        try {
            // Replace with your database credentials
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
            stmt = con.createStatement();

            int choice;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Add Student");
                System.out.println("2. Update Student Details");
                System.out.println("3. Delete Student");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                choice = Integer.parseInt(System.console().readLine());

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 4);

            con.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void addStudent() throws SQLException {
        System.out.print("Enter Roll No: ");
        int rollNo = Integer.parseInt(System.console().readLine());
        System.out.print("Enter Name: ");
        String name = System.console().readLine();
        System.out.print("Enter Marks: ");
        int marks = Integer.parseInt(System.console().readLine());
        System.out.print("Enter Class: ");
        int class_ = Integer.parseInt(System.console().readLine());

        String query = "INSERT INTO student (roll_no, name, marks, class) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, rollNo);
        pstmt.setString(2, name);
        pstmt.setInt(3, marks);
        pstmt.setInt(4, class_);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student.");
        }
        pstmt.close();
    }

    public static void updateStudent() throws SQLException {
        System.out.print("Enter Roll No to update: ");
        int rollNo = Integer.parseInt(System.console().readLine());

        System.out.print("Enter new Name: ");
        String name = System.console().readLine();
        System.out.print("Enter new Marks: ");
        int marks = Integer.parseInt(System.console().readLine());
        System.out.print("Enter new Class: ");
        int class_ = Integer.parseInt(System.console().readLine());

        String query = "UPDATE student SET name = ?, marks = ?, class = ? WHERE roll_no = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setInt(2, marks);
        pstmt.setInt(3, class_);
        pstmt.setInt(4, rollNo);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student details updated successfully!");
        } else {
            System.out.println("Failed to update student details.");
        }
        pstmt.close();
    }

    public static void deleteStudent() throws SQLException {
        System.out.print("Enter Roll No to delete: ");
        int rollNo = Integer.parseInt(System.console().readLine());

        String query = "DELETE FROM student WHERE roll_no = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, rollNo);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Failed to delete student.");
        }
        pstmt.close();
    }
}