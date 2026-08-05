
public class GradeCalculator {

    // Convert grade into grade point
    public static double getGradePoint(String grade) {

        switch (grade) {

            case "S":
                return 10.0;

            case "A+":
                return 9.0;

            case "A":
                return 8.0;

            case "B+":
                return 7.0;

            case "B":
                return 6.0;

            case "C+":
                return 5.0;

            case "C":
                return 4.0;

            case "D+":
                return 3.0;

            case "D":
                return 2.0;

            case "P":
                return 1.0;

            case "F":
                return 0.0;

            default:
                return 0.0;
        }
    }

    // Calculate SGPA
    public static double calculateSGPA(
            Semester semester) {

        double totalCredits = 0.0;

        double totalCreditPoints = 0.0;

        for (Subject subject :
                semester.getSubjects()) {

            totalCredits +=
                    subject.getCredits();

            totalCreditPoints +=
                    subject.getCredits()
                    *
                    subject.getGradePoint();
        }

        if (totalCredits == 0) {

            return 0.0;
        }

        return totalCreditPoints
                / totalCredits;
    }

    // Calculate CGPA
    public static double calculateCGPA(
            Student student) {

        double totalCredits = 0.0;

        double totalCreditPoints = 0.0;

        for (Semester semester :
                student.getSemesters()) {

            for (Subject subject :
                    semester.getSubjects()) {

                totalCredits +=
                        subject.getCredits();

                totalCreditPoints +=
                        subject.getCredits()
                        *
                        subject.getGradePoint();
            }
        }

        if (totalCredits == 0) {

            return 0.0;
        }

        return totalCreditPoints
                / totalCredits;
    }
}


