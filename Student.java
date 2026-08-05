
import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String registerNumber;
    private String department;
    private String academicYear;

    private List<Semester> semesters;

    public Student(
            String name,
            String registerNumber,
            String department,
            String academicYear) {

        this.name = name;
        this.registerNumber = registerNumber;
        this.department = department;
        this.academicYear = academicYear;

        this.semesters = new ArrayList<>();
    }

    // Get student name
    public String getName() {
        return name;
    }

    // Get register number
    public String getRegisterNumber() {
        return registerNumber;
    }

    // Get department
    public String getDepartment() {
        return department;
    }

    // Get academic year
    public String getAcademicYear() {
        return academicYear;
    }

    // Get all semesters
    public List<Semester> getSemesters() {
        return semesters;
    }

    // Add a semester
    public void addSemester(
            Semester semester) {

        semesters.add(semester);
    }
 
public Semester getSemester(
        String semesterName) {

    for (Semester semester :
            semesters) {

        if (semester
                .getSemesterName()
                .equals(semesterName)) {

            return semester;
        }
    }

    return null;
}


}




