
import java.util.ArrayList;
import java.util.List;

public class Semester {

    private String semesterName;

    private List<Subject> subjects;

    public Semester(String semesterName) {

        this.semesterName = semesterName;

        this.subjects = new ArrayList<>();
    }

    public String getSemesterName() {
        return semesterName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {

        subjects.add(subject);
    }
}

