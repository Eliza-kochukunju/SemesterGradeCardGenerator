
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SemesterGradeCardGenerator extends JFrame {

    // =====================================================
    // APPLICATION DATA
    // =====================================================

    private List<Student> students = new ArrayList<>();

    private Student selectedStudent;
    private Semester selectedSemester;

    // =====================================================
    // GUI COMPONENTS
    // =====================================================

    private JTextField nameField;
    private JTextField registerField;
    private JTextField departmentField;
    private JTextField academicYearField;

    private JTextField subjectCodeField;
    private JTextField subjectNameField;
    private JTextField creditsField;

    private JComboBox<String> semesterComboBox;
    private JComboBox<String> gradeComboBox;

    private JTable subjectTable;
    private DefaultTableModel tableModel;
    private int editingSubjectRow = -1;

    private JLabel selectedStudentLabel;
    private JLabel sgpaLabel;
    private JLabel cgpaLabel;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color NAVY =
            new Color(25, 45, 85);

    private final Color BLUE =
            new Color(45, 95, 180);

    private final Color GREEN =
            new Color(40, 150, 90);

    private final Color RED =
            new Color(200, 60, 60);

    private final Color BACKGROUND =
            new Color(242, 246, 252);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

   public SemesterGradeCardGenerator() {

    setTitle("Semester Grade Card Generator");

    setSize(1100, 750);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLocationRelativeTo(null);

    createGUI();

    loadStudentsFromDatabase();
}

    // =====================================================
    // CREATE GUI
    // =====================================================

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                NAVY
        );

        header.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "SEMESTER GRADE CARD GENERATOR"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Student Academic Performance Management System"
                );

        subtitle.setForeground(
                new Color(
                        220,
                        230,
                        245
                )
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setOpaque(
                false
        );

        titlePanel.add(
                title
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        5
                )
        );

        titlePanel.add(
                subtitle
        );

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =================================================
        // CONTENT
        // =================================================

        JPanel content =
                new JPanel();

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBackground(
                BACKGROUND
        );

        content.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );

        // Add panels

        content.add(
                createStudentPanel()
        );

        content.add(
                Box.createVerticalStrut(
                        10
                )
        );

        content.add(
                createSemesterPanel()
        );

        content.add(
                Box.createVerticalStrut(
                        10
                )
        );

        content.add(
                createSubjectPanel()
        );

        content.add(
                Box.createVerticalStrut(
                        10
                )
        );

        content.add(
                createResultPanel()
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        content
                );

        scrollPane.setBorder(
                null
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                mainPanel
        );
    }

    // =====================================================
    // STUDENT PANEL
    // =====================================================

    private JPanel createStudentPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                10,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory
                        .createTitledBorder(
                                "Student Information"
                        )
        );

        nameField =
                new JTextField();

        registerField =
                new JTextField();

        departmentField =
                new JTextField();

        academicYearField =
                new JTextField();

        panel.add(
                createField(
                        "Student Name",
                        nameField
                )
        );

        panel.add(
                createField(
                        "Register Number",
                        registerField
                )
        );

        panel.add(
                createField(
                        "Department",
                        departmentField
                )
        );

        panel.add(
                createField(
                        "Academic Year",
                        academicYearField
                )
        );

        JButton addStudentButton =
                createButton(
                        "Add Student",
                        BLUE
                );

        JButton selectStudentButton =
                createButton(
                        "Select Student",
                        GREEN
                );

        JButton deleteStudentButton =
                createButton(
                        "Delete Student",
                        RED
                );

        selectedStudentLabel =
                new JLabel(
                        "No student selected"
                );

        panel.add(
                addStudentButton
        );

        panel.add(
                selectStudentButton
        );

        panel.add(
                deleteStudentButton
        );

        panel.add(
                selectedStudentLabel
        );

        // Button actions

        addStudentButton
                .addActionListener(
                        e ->
                                addStudent()
                );

        selectStudentButton
                .addActionListener(
                        e ->
                                selectStudent()
                );

        deleteStudentButton
                .addActionListener(
                        e ->
                                deleteStudent()
                );

        return panel;
    }

    // =====================================================
    // SEMESTER PANEL
    // =====================================================

    private JPanel createSemesterPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                15,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory
                        .createTitledBorder(
                                "Semester Management"
                        )
        );

        semesterComboBox =
                new JComboBox<>(
                        new String[]{
                                "Semester 1",
                                "Semester 2",
                                "Semester 3",
                                "Semester 4",
                                "Semester 5",
                                "Semester 6",
                                "Semester 7",
                                "Semester 8"
                        }
                );

        JButton loadButton =
                createButton(
                        "Load Semester",
                        BLUE
                );

        panel.add(
                new JLabel(
                        "Select Semester:"
                )
        );

        panel.add(
                semesterComboBox
        );

        panel.add(
                loadButton
        );

        loadButton
                .addActionListener(
                        e ->
                                loadSemester()
                );

        return panel;
    }

    // =====================================================
    // SUBJECT PANEL
    // =====================================================

    private JPanel createSubjectPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory
                        .createTitledBorder(
                                "Subject Details"
                        )
        );

        // Input panel

        JPanel inputPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                5,
                                10,
                                10
                        )
                );

        inputPanel.setBackground(
                Color.WHITE
        );

        subjectCodeField =
                new JTextField();

        subjectNameField =
                new JTextField();

        creditsField =
                new JTextField();

        gradeComboBox =
                new JComboBox<>(
                        new String[]{
                                "S",
                                "A+",
                                "A",
                                "B+",
                                "B",
                                "C+",
                                "C",
                                "D",
                                "P",
                                "F"
                        }
                );

        JButton addSubjectButton =
                createButton(
                        "Add Subject",
                        BLUE
                );
                JButton editSubjectButton =
        createButton(
                "Edit Subject",
                GREEN
        );

JButton deleteSubjectButton =
        createButton(
                "Delete Subject",
                RED
        );

        inputPanel.add(
                createField(
                        "Subject Code",
                        subjectCodeField
                )
        );

        inputPanel.add(
                createField(
                        "Subject Name",
                        subjectNameField
                )
        );

        inputPanel.add(
                createField(
                        "Credits",
                        creditsField
                )
        );

        JPanel gradePanel =
                new JPanel(
                        new BorderLayout()
                );

        gradePanel.setBackground(
                Color.WHITE
        );

        gradePanel.add(
                new JLabel(
                        "Grade"
                ),
                BorderLayout.NORTH
        );

        gradePanel.add(
                gradeComboBox,
                BorderLayout.CENTER
        );

        inputPanel.add(
                gradePanel
        );

       JPanel buttonPanel = new JPanel(new GridLayout(3,1,5,5));

buttonPanel.add(addSubjectButton);
buttonPanel.add(editSubjectButton);
buttonPanel.add(deleteSubjectButton);

inputPanel.add(buttonPanel);

        panel.add(
                inputPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Subject Code",
                                "Subject Name",
                                "Credits",
                                "Grade",
                                "Grade Point"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        subjectTable =
                new JTable(
                        tableModel
                );

        subjectTable.setRowHeight(
                28
        );

        JScrollPane tableScroll =
                new JScrollPane(
                        subjectTable
                );

        panel.add(
                tableScroll,
                BorderLayout.CENTER
        );

        addSubjectButton.addActionListener(
                e -> addSubject()
        );

        editSubjectButton.addActionListener(
                e -> editSubject()
        );

        deleteSubjectButton.addActionListener(
                e -> deleteSubject()
        );

                return panel;
    }

    // =====================================================
    // RESULT PANEL
    // =====================================================

    private JPanel createResultPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                80,
                                15
                        )
                );

        panel.setBackground(
                NAVY
        );

        sgpaLabel =
                new JLabel(
                        "SGPA: 0.00"
                );

        cgpaLabel =
                new JLabel(
                        "CGPA: 0.00"
                );

        sgpaLabel.setForeground(
                Color.WHITE
        );

        cgpaLabel.setForeground(
                Color.WHITE
        );

        sgpaLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        cgpaLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        panel.add(
                sgpaLabel
        );

        panel.add(
                cgpaLabel
        );

        return panel;
    }

    // =====================================================
    // FIELD CREATOR
    // =====================================================

    private JPanel createField(
            String label,
            JTextField field
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.add(
                new JLabel(
                        label
                ),
                BorderLayout.NORTH
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =====================================================
    // BUTTON CREATOR
    // =====================================================

    private JButton createButton(
            String text,
            Color color
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setBackground(
                color
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFocusPainted(
                false
        );

        return button;
    }

    // =====================================================
    // ADD STUDENT
    // =====================================================

    private void addStudent() {

        String name =
                nameField
                        .getText()
                        .trim();

        String registerNumber =
                registerField
                        .getText()
                        .trim();

        String department =
                departmentField
                        .getText()
                        .trim();

        String academicYear =
                academicYearField
                        .getText()
                        .trim();
 // Check for duplicate register number
for (Student student : students) {

    if (student.getRegisterNumber().equalsIgnoreCase(registerNumber)) {

        JOptionPane.showMessageDialog(
                this,
                "A student with this Register Number already exists."
        );

        return;
    }
}

        if (
                name.isEmpty()
                        ||
                registerNumber.isEmpty()
                        ||
                department.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all required student details."
            );

            return;
        }

        // Check duplicate register number

        for (
                Student student :
                students
        ) {

            if (
                    student
                            .getRegisterNumber()
                            .equalsIgnoreCase(
                                    registerNumber
                            )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "A student with this register number already exists."
                );

                return;
            }
        }

       Student student =
                new Student(
                        name,
                        registerNumber,
                        department,
                        academicYear
                );
                DatabaseManager.insertStudent(
        name,
        registerNumber,
        department,
        academicYear
);

        students.add(
                student
        ); 

        selectedStudent =
                student;

        selectedStudentLabel.setText(
                "Selected: "
                        +
                        name
        );
        // Clear input fields
        nameField.setText("");
        registerField.setText("");
        departmentField.setText("");
        academicYearField.setText("");
        JOptionPane.showMessageDialog(
                this,
                "Student added successfully!"
        );
    }

    // =====================================================
    // SELECT STUDENT
    // =====================================================

    private void selectStudent() {

        if (
                students.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No students have been added yet."
            );

            return;
        }

        String[] studentOptions =
                new String[
                        students.size()
                ];

        for (
                int i = 0;
                i < students.size();
                i++
        ) {

            Student student =
                    students.get(
                            i
                    );

            studentOptions[i] =
                    student.getName()
                            +
                            " - "
                            +
                            student
                                    .getRegisterNumber();
        }

        String selected =
                (String)
                        JOptionPane.showInputDialog(
                                this,
                                "Select Student:",
                                "Student Selection",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                studentOptions,
                                studentOptions[0]
                        );

        if (
                selected == null
        ) {

            return;
        }

        for (
                Student student :
                students
        ) {

            String display =
                    student.getName()
                            +
                            " - "
                            +
                            student
                                    .getRegisterNumber();

            if (
                    display.equals(
                            selected
                    )
            ) {

                selectedStudent =
                        student;

                selectedSemester =
                        null;

                nameField.setText(
                        student.getName()
                );

                registerField.setText(
                        student
                                .getRegisterNumber()
                );

                departmentField.setText(
                        student
                                .getDepartment()
                );

                academicYearField.setText(
                        student
                                .getAcademicYear()
                );

                selectedStudentLabel.setText(
                        "Selected: "
                                +
                                student.getName()
                );

                tableModel.setRowCount(
                        0
                );

                sgpaLabel.setText(
                        "SGPA: 0.00"
                );

                updateCGPA();

                break;
            }
        }
    }

    // =====================================================
    // DELETE STUDENT
    // =====================================================

    private void deleteStudent() {

        if (
                selectedStudent == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student first."
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete "
                                +
                                selectedStudent
                                        .getName()
                                +
                                "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result
                        ==
                        JOptionPane.YES_OPTION
        ) {

            students.remove(
                    selectedStudent
            );
DatabaseManager.deleteStudent(
        selectedStudent.getRegisterNumber()
);
            selectedStudent =
                    null;

            selectedSemester =
                    null;

            tableModel.setRowCount(
                    0
            );

            selectedStudentLabel.setText(
                    "No student selected"
            );

            sgpaLabel.setText(
                    "SGPA: 0.00"
            );

            cgpaLabel.setText(
                    "CGPA: 0.00"
            );
        }
    }

    // =====================================================
    // LOAD SEMESTER
    // =====================================================

    private void loadSemester() {

        if (
                selectedStudent == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please add or select a student first."
            );

            return;
        }

        String semesterName =
                (String)
                        semesterComboBox
                                .getSelectedItem();

        selectedSemester =
                selectedStudent
                        .getSemester(
                                semesterName
                        );

        if (
                selectedSemester == null
        ) {

            selectedSemester =
                    new Semester(
                            semesterName
                    );

            selectedStudent
                    .addSemester(
                            selectedSemester
                    );
        }

        tableModel.setRowCount(
                0
        );

        for (
                Subject subject :
                selectedSemester
                        .getSubjects()
        ) {

            tableModel.addRow(
                    new Object[]{
                            subject
                                    .getSubjectCode(),

                            subject
                                    .getSubjectName(),

                            subject
                                    .getCredits(),

                            subject
                                    .getGrade(),

                            subject
                                    .getGradePoint()
                    }
            );
        }

        updateResults();
    }
// =====================================================
// DELETE SUBJECT
// =====================================================
private void deleteSubject() {

    int row = subjectTable.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a subject to delete."
        );

        return;
    }

    if (selectedStudent == null) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a student first."
        );

        return;
    }

    if (selectedSemester == null) {

        JOptionPane.showMessageDialog(
                this,
                "Please load a semester first."
        );

        return;
    }

    int result =
            JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this subject?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

    if (result != JOptionPane.YES_OPTION) {
        return;
    }

    // Get subject information BEFORE removing it
    Subject subject =
            selectedSemester
                    .getSubjects()
                    .get(row);

    String subjectCode =
            subject.getSubjectCode();

    String semester =
            semesterComboBox
                    .getSelectedItem()
                    .toString();

    // =====================================================
    // DELETE FROM DATABASE
    // =====================================================

    DatabaseManager.deleteSubject(
            selectedStudent.getRegisterNumber(),
            semester,
            subjectCode
    );

    // =====================================================
    // REMOVE FROM JAVA MEMORY
    // =====================================================

    selectedSemester
            .getSubjects()
            .remove(row);

    // =====================================================
    // REMOVE FROM TABLE
    // =====================================================

    tableModel.removeRow(row);

    // =====================================================
    // UPDATE SGPA / CGPA
    // =====================================================

    updateResults();

    JOptionPane.showMessageDialog(
            this,
            "Subject deleted successfully."
    );
}
    // =====================================================
    // ADD SUBJECT
    // =====================================================

    private void editSubject() {

    int row = subjectTable.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Please select a subject to edit."
        );

        return;
    }

    subjectCodeField.setText(
            tableModel.getValueAt(row, 0).toString()
    );

    subjectNameField.setText(
            tableModel.getValueAt(row, 1).toString()
    );
System.out.println("COLUMN 2 (CREDITS) = " +
        tableModel.getValueAt(row, 2));

System.out.println("COLUMN 4 (GRADE POINT) = " +
        tableModel.getValueAt(row, 4));
    creditsField.setText(
            tableModel.getValueAt(row, 2).toString()
    );

    gradeComboBox.setSelectedItem(
            tableModel.getValueAt(row, 3).toString()
    );

    tableModel.removeRow(row);

    if (selectedSemester != null &&
            row < selectedSemester.getSubjects().size()) {

        selectedSemester.getSubjects().remove(row);
    }

    updateResults();

    JOptionPane.showMessageDialog(
            this,
            "Edit the values and click 'Add Subject' to save the changes."
    );
}

    private void addSubject() {

        if (
                selectedStudent == null
        ) {


            JOptionPane.showMessageDialog(
                    this,
                    "Please add or select a student first."
            );

            return;
        }

        if (
                selectedSemester == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load a semester first."
            );

            return;
        }

        String code =
                subjectCodeField
                        .getText()
                        .trim();

        String name =
                subjectNameField
                        .getText()
                        .trim();

        String creditsText =
                creditsField
                        .getText()
                        .trim();

        String grade =
                (String)
                        gradeComboBox
                                .getSelectedItem();
// Check for duplicate subject code

        for (Subject subject : selectedSemester.getSubjects()) {

        if (subject.getSubjectCode().equalsIgnoreCase(code)) {

        JOptionPane.showMessageDialog(
                this,
                "Subject Code already exists in this semester."
        );

        return;
    }
}
        if (
                code.isEmpty()
                        ||
                name.isEmpty()
                        ||
                creditsText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all subject details."
            );

            return;
        }

        try {
double creditsValue =
        Double.parseDouble(
                creditsText
        );

if (creditsValue <= 0 || creditsValue != Math.floor(creditsValue)) {

    throw new NumberFormatException();
}

int credits =
        (int) creditsValue;
        System.out.println("CREDITS ENTERED = " + creditsText);
System.out.println("CREDITS SAVED = " + credits);
            if (
                    credits <= 0
            ) {

                throw new NumberFormatException();
            }

            double gradePoint =
                    GradeCalculator
                            .getGradePoint(
                                    grade
                            );

            Subject subject =
                    new Subject(
                            code,
                            name,
                            credits,
                            grade,
                            gradePoint
                    );

        selectedSemester
        .addSubject(
                subject
        );

System.out.println("Calling insertSubject...");

DatabaseManager.insertSubject(
        selectedStudent.getRegisterNumber(),
        (String) semesterComboBox.getSelectedItem(),
        code,
        name,
        credits,
        grade,
        gradePoint
);

tableModel.addRow(
        new Object[]{
                code,
                name,
                credits,
                grade,
                gradePoint
        }
);

updateResults();
            subjectCodeField.setText(
                    ""
            );

            subjectNameField.setText(
                    ""
            );

            creditsField.setText(
                    ""
            );
            
gradeComboBox.setSelectedIndex(0);
        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Credits must be a valid positive number."
            );
        }
    }

    // =====================================================
    // UPDATE RESULTS
    // =====================================================

    private void updateResults() {

        if (
                selectedStudent == null
        ) {

            return;
        }

        double sgpa =
                GradeCalculator
                        .calculateSGPA(
                                selectedSemester
                        );

        sgpaLabel.setText(
                String.format(
                        "SGPA: %.2f",
                        sgpa
                )
        );

        updateCGPA();
    }

    // =====================================================
    // UPDATE CGPA
    // =====================================================

    private void updateCGPA() {

        if (
                selectedStudent == null
        ) {

            return;
        }

        double cgpa =
                GradeCalculator
                        .calculateCGPA(
                                selectedStudent
                        );

        cgpaLabel.setText(
                String.format(
                        "CGPA: %.2f",
                        cgpa
                )
        );
    }

    // =====================================================
    // LOAD STUDENTS FROM DATABASE
    // =====================================================
private void loadStudentsFromDatabase() {

    students.clear();

    students.addAll(
            DatabaseManager.getAllStudents()
    );

    // Load saved semesters and subjects for every student
    for (Student student : students) {

        for (int i = 1; i <= 8; i++) {

            String semesterName =
                    "Semester " + i;

            List<Subject> savedSubjects =
                    DatabaseManager.getSubjects(
                            student.getRegisterNumber(),
                            semesterName
                    );

            // Only create the semester if
            // there are saved subjects
            if (!savedSubjects.isEmpty()) {

                Semester semester =
                        student.getSemester(
                                semesterName
                        );

                if (semester == null) {

                    semester =
                            new Semester(
                                    semesterName
                            );

                    student.addSemester(
                            semester
                    );
                }

                for (Subject subject : savedSubjects) {

                    semester.addSubject(
                            subject
                    );
                }
            }
        }
    }

    System.out.println(
            students.size()
                    +
            " student(s) loaded from database."
    );
}
    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(
            String[] args
    ) {
       DatabaseManager.connect();
        SwingUtilities.invokeLater(
                () -> {

                    SemesterGradeCardGenerator app =
                            new SemesterGradeCardGenerator();

                    app.setVisible(
                            true
                    );
                }
        );
    }
}