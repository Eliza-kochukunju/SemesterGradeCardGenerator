import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:gradecard.db";

    public static Connection connect() {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(URL);

            System.out.println("Database Connected Successfully!");

            createTables(connection);

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("SQLite JDBC Driver Not Found!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
        }

        return null;
    }

    private static void createTables(Connection connection) {

        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    register_no TEXT UNIQUE NOT NULL,
                    department TEXT NOT NULL,
                    academic_year TEXT NOT NULL
                );
                """;

        try (Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

            System.out.println("Students table created successfully!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void insertStudent(String name,
                                     String registerNo,
                                     String department,
                                     String academicYear) {

        String sql =
                "INSERT INTO students(name, register_no, department, academic_year) VALUES(?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, registerNo);
            pstmt.setString(3, department);
            pstmt.setString(4, academicYear);

            pstmt.executeUpdate();

            System.out.println("Student Saved Successfully!");

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student(
                        rs.getString("name"),
                        rs.getString("register_no"),
                        rs.getString("department"),
                        rs.getString("academic_year")
                );

                students.add(student);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return students;
    }
}