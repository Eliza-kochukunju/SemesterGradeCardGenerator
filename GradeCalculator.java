public class GradeCalculator {

    // =====================================================
    // CONVERT GRADE INTO GRADE POINT
    // =====================================================

    public static double getGradePoint(String grade) {

        if (grade == null) {
            return 0.0;
        }

        switch (grade.trim().toUpperCase()) {

            case "S":
                return 10.0;

            case "A+":
                return 9.0;

            case "A":
                return 8.5;

            case "B+":
                return 8.0;

            case "B":
                return 7.5;

            case "C+":
                return 7.0;

            case "C":
                return 6.5;

            case "D":
                return 6.0;

            case "P":
                return 5.5;

            case "F":
                return 0.0;

            case "PASS":
                return 0.0;

            default:
                return 0.0;
        }
    }

    // =====================================================
    // CHECK WHETHER A GRADE IS PASS
    // =====================================================

    private static boolean isPassGrade(String grade) {

        return grade != null
                && grade.trim().equalsIgnoreCase("PASS");
    }

    // =====================================================
    // CALCULATE SGPA
    // =====================================================

    public static double calculateSGPA(
            Semester semester) {

        if (semester == null) {
            return 0.0;
        }

        double totalCredits = 0.0;

        double totalCreditPoints = 0.0;

        for (Subject subject :
                semester.getSubjects()) {

            // PASS subjects are NOT included
            // in SGPA calculation.

            if (isPassGrade(subject.getGrade())) {
                continue;
            }

            totalCredits +=
                    subject.getCredits();

            totalCreditPoints +=
                    subject.getCredits()
                    *
                    subject.getGradePoint();
        }

        if (totalCredits == 0.0) {
            return 0.0;
        }

        return totalCreditPoints
                / totalCredits;
    }

    // =====================================================
    // CALCULATE CGPA
    // =====================================================

    public static double calculateCGPA(
            Student student) {

        if (student == null) {
            return 0.0;
        }

        double totalCredits = 0.0;

        double totalCreditPoints = 0.0;

        for (Semester semester :
                student.getSemesters()) {

            if (semester == null) {
                continue;
            }

            for (Subject subject :
                    semester.getSubjects()) {

                // PASS subjects are NOT included
                // in CGPA calculation.

                if (isPassGrade(subject.getGrade())) {
                    continue;
                }

                totalCredits +=
                        subject.getCredits();

                totalCreditPoints +=
                        subject.getCredits()
                        *
                        subject.getGradePoint();
            }
        }

        if (totalCredits == 0.0) {
            return 0.0;
        }

        return totalCreditPoints
                / totalCredits;
    }
}