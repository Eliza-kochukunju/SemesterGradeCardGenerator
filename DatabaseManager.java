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

    // =====================================================
    // DATABASE CONNECTION
    // =====================================================

    public static Connection connect() {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection =
                    DriverManager.getConnection(URL);

            createTables(connection);

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println(
                    "SQLite JDBC Driver Not Found!"
            );

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println(
                    "Database Connection Failed!"
            );

            e.printStackTrace();
        }

        return null;
    }

    // =====================================================
    // CREATE TABLES
    // =====================================================

    private static void createTables(
            Connection connection) {

        String studentTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    register_no TEXT UNIQUE NOT NULL,
                    department TEXT NOT NULL,
                    academic_year TEXT NOT NULL
                );
                """;

        String semesterTable = """
                CREATE TABLE IF NOT EXISTS semesters (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_register_no TEXT NOT NULL,
                    semester_number TEXT NOT NULL,
                    sgpa REAL DEFAULT 0
                );
                """;

        String subjectTable = """
                CREATE TABLE IF NOT EXISTS subjects (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_register_no TEXT NOT NULL,
                    semester_number TEXT NOT NULL,
                    subject_code TEXT NOT NULL,
                    subject_name TEXT NOT NULL,
                    credits INTEGER NOT NULL,
                    grade TEXT NOT NULL,
                    grade_point REAL NOT NULL
                );
                """;

        try (Statement stmt =
                     connection.createStatement()) {

            stmt.execute(studentTable);

            stmt.execute(semesterTable);

            stmt.execute(subjectTable);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // INSERT STUDENT
    // =====================================================

    public static void insertStudent(
            String name,
            String registerNo,
            String department,
            String academicYear) {

        String sql = """
                INSERT INTO students
                (name, register_no, department, academic_year)
                VALUES (?, ?, ?, ?)
                """;

        try (
                Connection connection = connect();
                PreparedStatement pstmt =
                        connection.prepareStatement(sql)
        ) {

            pstmt.setString(1, name);

            pstmt.setString(2, registerNo);

            pstmt.setString(3, department);

            pstmt.setString(4, academicYear);

            pstmt.executeUpdate();

            System.out.println(
                    "Student Saved Successfully!"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    public static List<Student> getAllStudents() {

        List<Student> students =
                new ArrayList<>();

        String sql =
                "SELECT * FROM students ORDER BY id";

        try (
                Connection connection = connect();
                Statement stmt =
                        connection.createStatement();
                ResultSet rs =
                        stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                Student student =
                        new Student(
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

    // =====================================================
    // INSERT SUBJECT
    // =====================================================

    public static void insertSubject(
            String registerNo,
            String semester,
            String subjectCode,
            String subjectName,
            int credits,
            String grade,
            double gradePoint) {

        String sql = """
                INSERT INTO subjects
                (
                    student_register_no,
                    semester_number,
                    subject_code,
                    subject_name,
                    credits,
                    grade,
                    grade_point
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = connect();
                PreparedStatement pstmt =
                        connection.prepareStatement(sql)
        ) {

            pstmt.setString(1, registerNo);

            pstmt.setString(2, semester);

            pstmt.setString(3, subjectCode);

            pstmt.setString(4, subjectName);

            pstmt.setInt(5, credits);

            pstmt.setString(6, grade);

            pstmt.setDouble(7, gradePoint);

            pstmt.executeUpdate();

            System.out.println(
                    "Subject Saved Successfully!"
            );

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // GET SUBJECTS FOR A STUDENT + SEMESTER
    // =====================================================

    public static List<Subject> getSubjects(
            String registerNo,
            String semester) {

        List<Subject> subjects =
                new ArrayList<>();

        String sql = """
                SELECT
                    subject_code,
                    subject_name,
                    credits,
                    grade,
                    grade_point
                FROM subjects
                WHERE student_register_no = ?
                  AND semester_number = ?
                ORDER BY id
                """;

        try (
                Connection connection = connect();
                PreparedStatement pstmt =
                        connection.prepareStatement(sql)
        ) {

            pstmt.setString(1, registerNo);

            pstmt.setString(2, semester);

            try (
                    ResultSet rs =
                            pstmt.executeQuery()
            ) {

                while (rs.next()) {

                    Subject subject =
                            new Subject(
                                    rs.getString(
                                            "subject_code"
                                    ),

                                    rs.getString(
                                            "subject_name"
                                    ),

                                    rs.getInt(
                                            "credits"
                                    ),

                                    rs.getString(
                                            "grade"
                                    ),

                                    rs.getDouble(
                                            "grade_point"
                                    )
                            );

                    subjects.add(subject);
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return subjects;
    }
    // =====================================================
// DELETE STUDENT FROM DATABASE
// =====================================================

public static void deleteStudent(String registerNo) {

    String deleteSubjects =
            "DELETE FROM subjects WHERE student_register_no = ?";

    String deleteSemesters =
            "DELETE FROM semesters WHERE student_register_no = ?";

    String deleteStudent =
            "DELETE FROM students WHERE register_no = ?";

    try (Connection connection = connect()) {

        try (PreparedStatement pstmt =
                     connection.prepareStatement(deleteSubjects)) {

            pstmt.setString(1, registerNo);
            pstmt.executeUpdate();
        }

        try (PreparedStatement pstmt =
                     connection.prepareStatement(deleteSemesters)) {

            pstmt.setString(1, registerNo);
            pstmt.executeUpdate();
        }

        try (PreparedStatement pstmt =
                     connection.prepareStatement(deleteStudent)) {

            pstmt.setString(1, registerNo);
            pstmt.executeUpdate();
        }

        System.out.println("Student deleted from database.");

    } catch (SQLException e) {

        e.printStackTrace();
    }
}


// =====================================================
// DELETE SUBJECT FROM DATABASE
// =====================================================

public static void deleteSubject(
        String registerNo,
        String semester,
        String subjectCode) {

    String sql =
            "DELETE FROM subjects " +
            "WHERE student_register_no = ? " +
            "AND semester_number = ? " +
            "AND subject_code = ?";

    try (Connection connection = connect();
         PreparedStatement pstmt =
                 connection.prepareStatement(sql)) {

        pstmt.setString(1, registerNo);
        pstmt.setString(2, semester);
        pstmt.setString(3, subjectCode);

        pstmt.executeUpdate();

        System.out.println("Subject deleted from database.");

    } catch (SQLException e) {

        e.printStackTrace();
    }
}
}