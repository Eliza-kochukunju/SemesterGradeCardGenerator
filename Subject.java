
public class Subject {

    private String subjectCode;
    private String subjectName;
    private int credits;
    private String grade;
    private double gradePoint;

    public Subject(
            String subjectCode,
            String subjectName,
            int credits,
            String grade,
            double gradePoint) {

        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.grade = grade;
        this.gradePoint = gradePoint;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return gradePoint;
    }
}

