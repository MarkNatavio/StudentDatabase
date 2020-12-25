package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;

public class DatabaseModification {
    private static Connection conn;

// ******************************** CONNECT TO DATA BASE ********************************
    public static void connect() { // connect to the database
        conn = null;
        try {
            // Loads the class object for the mysql driver into the DriverManager.
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to database...");

            //  Attempt to establish a connection to the specified database via the DriverManager
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "MyPassword");
            if (conn != null) { // Check the connection
                System.out.println("Database connected");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

// ******************************** CREATE DATA TABLES ********************************
    public static void CreateTables(){
        try {
            if (conn != null){ // Connection is established
                // Used PreparedStatement as I want to be able to change the data in the table later

                // Create table Students
                PreparedStatement Students = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Students"
                        // set table parameters
                        + "(empID INT UNSIGNED NOT NULL," // int student empID
                        + "PRIMARY KEY (empID)," // set student empID as the primary key
                        + "firstName varchar(255)," // String firstName
                        + "lastname varchar(255)," // String lastName
                        + "email varchar(255)," // String email
                        + "sex CHAR(1)," + "CHECK (sex = 'F' OR sex = 'M' OR sex = 'U'))"); // char sex (F/M/U)
                Students.execute();

                // Create table Courses
                PreparedStatement Courses = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Courses"
                        // set table parameters
                        + "(courseID INT UNSIGNED NOT NULL," // int courseID
                        + "PRIMARY KEY (courseID)," // set courseID as the primary key
                        + "courseTitle varchar(255)," // String courseTitle
                        + "department varchar(255))"); // String department
                Courses.execute();

                // Create table Classes
                PreparedStatement Classes = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Classes"
                        + "(courseID INT UNSIGNED NOT NULL," // int courseID
                        + "studentID INT UNSIGNED NOT NULL," // int empID
                        + "sectionNo INT UNSIGNED NOT NULL," // int sectionNo
                        + "PRIMARY KEY(courseID, studentID, sectionNo)," // set courseID, empID, and sectionNo as primary keys
                        + "FOREIGN KEY(courseID) REFERENCES Courses(courseID)," // course ID is a foreign key
                        + "FOREIGN KEY(studentID) REFERENCES Students(empID)," // empID is a foreign key
                        + "year INT UNSIGNED NOT NULL," // int year taken
                        + "semester varchar(255)," // String semester taken
                        + "grade CHAR(1)," + "CHECK (grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = 'F' OR grade = 'W'))");  // char grade (A/B/C/D/F/W)
                Classes.execute();

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

// ******************************** POPULATE DATA TABLES ********************************
    // Insert data into Students table
    public static void StudentsTableInsert(int empID, String firstName, String lastName, String email, char sex){
        try{
            PreparedStatement Students = conn.prepareStatement("INSERT INTO Students(empID, firstName, lastName, email, sex)"
                    + "VALUES('"+empID+"', '"+firstName+"', '"+lastName+"', '"+email+"', '"+sex+"')");
            Students.execute();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Populate Students table with random data of 20 students
    public static void populateStudents(){
        StudentsTableInsert(23759236,"John","Wick","jwick000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759237,"Najeeb", "Al-Awadhi", "nawadhi000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759238,"Joy", "Biswas", "jbiswas000@citymail.cuny.edu", 'F');
        StudentsTableInsert(23759239,"Jennifer", "Caceres", "jcaceres000@citymail.cuny.edu", 'F');
        StudentsTableInsert(23759240,"Tenzin", "Choklang", "tchokl000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759241,"Progga", "Chowdhury", "pchowdhur000@citymail.cuny.edu", 'F');
        StudentsTableInsert(23759242,"Sumit", "Das", "sdas000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759243,"Abir", "Das", "adas000@citymail.cuny.edu", 'U');
        StudentsTableInsert(23759244,"Thierno", "Dicko", "tdicko000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759245,"Derrick", "Duller", "dduller000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759246,"Tony", "Harryram", "tmarry000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759247,"Liana", "Hasan", "lhasan000@citymail.cuny.edu", 'F');
        StudentsTableInsert(23759248,"Shuren", "He", "she000@citymail.cuny.edu", 'U');
        StudentsTableInsert(23759249,"Md", "Islam", "mislam000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759250,"Ahmed", "Khater", "akhater000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759251,"Chelsea", "Lantigua", "clantig000@citymail.cuny.edu", 'F');
        StudentsTableInsert(23759252,"Byron", "Marcatoma", "bmarcat000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759253,"Refat", "Monjur", "rmonju000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759254,"Joseph", "Nicholas", "jnichol000@citymail.cuny.edu", 'M');
        StudentsTableInsert(23759255,"Sanjida", "Nisha", "snisha000@citymail.cuny.edu", 'F');
    }

    // Insert item into Courses table
    public static void CoursesTableInsert(int courseID, String courseTitle, String department){
        try{
            PreparedStatement Courses = conn.prepareStatement("INSERT INTO Courses(courseID, courseTitle, department)"
                    + "VALUES('"+courseID+"', '"+courseTitle+"', '"+department+"')");
            Courses.execute();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Populate Courses table with random data from 3 courses
    public static void populateCourses(){
        CoursesTableInsert(22000, "Algorithms", "CSC");
        CoursesTableInsert(34600, "Elements of Linear Algebra", "MATH");
        CoursesTableInsert(20700, "University Physics I", "PHYS");
    }

    // Insert item into Classes table
    public static void ClassesTableInsert(int courseID, int studentID, int sectionNo, int year, String semester, char grade){
        try{
            PreparedStatement Classes = conn.prepareStatement("INSERT INTO Classes(courseID, studentID, sectionNo, year, semester, grade)"
                    + "VALUES('"+courseID+"', '"+studentID+"', '"+sectionNo+"', '"+year+"', '"+semester+"', '"+grade+"')");
            Classes.execute();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Populate Classes table with random data from 20 students
    public static void populateClasses(){
        // Create Classes of CSC 22000 [Algorithms]
        ClassesTableInsert(22000,23759236,51658,2020, "Fall", 'A');
        ClassesTableInsert(22000,23759237,51658,2020, "Fall", 'B');
        ClassesTableInsert(22000,23759238,51658,2020, "Fall", 'C');
        ClassesTableInsert(22000,23759239,51658,2020, "Fall", 'D');
        ClassesTableInsert(22000,23759240,51658,2020, "Fall", 'F');
        ClassesTableInsert(22000,23759241,51658,2020, "Fall", 'W');
        ClassesTableInsert(22000,23759242,51658,2020, "Fall", 'A');
        ClassesTableInsert(22000,23759243,51658,2020, "Fall", 'A');
        ClassesTableInsert(22000,23759244,51658,2020, "Fall", 'D');
        ClassesTableInsert(22000,23759245,51658,2020, "Fall", 'W');
        ClassesTableInsert(22000,23759246,51658,2020, "Fall", 'B');
        ClassesTableInsert(22000,23759247,51658,2020, "Fall", 'B');
        ClassesTableInsert(22000,23759248,51658,2020, "Fall", 'A');
        ClassesTableInsert(22000,23759249,51658,2020, "Fall", 'C');
        ClassesTableInsert(22000,23759250,51658,2020, "Fall", 'F');

        // Create other classes
        ClassesTableInsert(34600,23759251,56668,2019, "Fall", 'A');
        ClassesTableInsert(34600,23759252,56668,2019, "Fall", 'A');
        ClassesTableInsert(34600,23759253,56668,2020, "Spring", 'A');
        ClassesTableInsert(20700,23759254,24675,2018, "Fall", 'B');
        ClassesTableInsert(20700,23759255,24675,2020, "Spring", 'A');
    }

// ******************************** UPDATE/DELETE DATA TABLES ********************************
    // Update data in Students table based on specific empID
    public static void updateStudent(int empID, String updateFirstName, String updateLastName, String updateEmail, char updateSex){
        try{
            PreparedStatement Student = conn.prepareStatement("UPDATE Students SET "
                    + "firstName = '"+updateFirstName+"', lastName = '"+updateLastName+"', email = '"+updateEmail+"', sex = '"+updateSex+"' "
                    + "WHERE empID = '"+empID+"'");
            Student.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Update date in Courses table based on specific courseID
    public static void updateCourses(int courseID, String updateCourseTitle, String updateDepartment){
        try{
            PreparedStatement Courses = conn.prepareStatement("UPDATE Courses SET "
                    + "courseTitle = '"+updateCourseTitle+"', department = '"+updateDepartment+"' "
                    + "WHERE courseID = '"+courseID+"'");
            Courses.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Update date in Classes table based on specific courseID, empID, and sectionNo
    public static void updateClasses(int courseID, int studentID, int sectionNo, int updateYear, String updateSemester, char updateGrade){
        try{
            PreparedStatement Classes = conn.prepareStatement("UPDATE Classes SET "
                    + "year = '"+updateYear+"', semester = '"+updateSemester+"', grade = '"+updateGrade+"' "
                    + "WHERE courseID = '"+courseID+"' AND studentID = '"+studentID+"' AND sectionNo = '"+sectionNo+"'");
            Classes.executeUpdate();
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Delete existing tables if restarting or needed
    public static void deleteTables() {
        try {
            if (conn != null) {
                PreparedStatement Classes = conn.prepareStatement("DROP TABLE IF EXISTS Classes");
                Classes.execute();

                PreparedStatement Students = conn.prepareStatement("DROP TABLE IF EXISTS Students");
                Students.execute();

                PreparedStatement Courses = conn.prepareStatement("DROP TABLE IF EXISTS Courses");
                Courses.execute();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

// ******************************** DISPLAY TABLES DATA ********************************
    public static void showValues(){ // show data in each data table
        try{
            PreparedStatement studentsTable = conn.prepareStatement("SELECT * FROM Students");
            ResultSet StudentData = studentsTable.executeQuery();
            DatabaseModification.showResults("Students", StudentData);

            PreparedStatement coursesTable = conn.prepareStatement("SELECT * FROM Courses");
            ResultSet CoursesData = coursesTable.executeQuery();
            DatabaseModification.showResults("Courses", CoursesData);

            PreparedStatement classTable = conn.prepareStatement("SELECT * FROM Classes");
            ResultSet ClassesData = classTable.executeQuery();
            DatabaseModification.showResults("Classes", ClassesData);
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void showResults(String tableName, ResultSet data) { // displays content of specific table
        try {
            ResultSetMetaData rsmd = data.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String resultString = null;
            if (numColumns > 0) {
                resultString = "\nTable: " + tableName + "\n=======================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++) resultString += rsmd.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println("=======================================================");
            while (data.next()) {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++) {
                    String column = data.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                System.out.println(resultString + "\n-------------------------------------------------------");
            }
            System.out.println("\n");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void gradesOfStudentsIn220(){ //  display the # of students for each grade in CSc 22000 in the Fall 2020 semester
        try{
            PreparedStatement StudentsInClass = conn.prepareStatement("SELECT grade, COUNT(studentID) FROM Classes WHERE courseID = '22000' AND year = 2020 AND semester = 'Fall' GROUP BY grade");
            ResultSet grades = StudentsInClass.executeQuery();
            DatabaseModification.showResults("Grades", grades);
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

// ******************************** MAKE HASHMAP OF # OF GRADES IN A CLASS ********************************
    public static HashMap<Character, Integer> intoHashMap(){
        HashMap<Character, Integer> gradesOfStudentsIn2020 = new HashMap<>(6);
        try{
            PreparedStatement gradesFreq = conn.prepareStatement("SELECT grade FROM Classes WHERE courseID = '22000' AND year = 2020 AND semester = 'Fall' ");
            ResultSet grades = gradesFreq.executeQuery();
            String allGrades = "";
            while (grades.next()) { // get all letter grades that
                String column = grades.getString("grade");
                if (column != null) {
                    allGrades += column + "";
                }
            }

            // Make a hashmap of letters and # occurrences
            for (int i = 0; i < allGrades.length(); i++) {
                char letterGrade = allGrades.charAt(i);
                if (!gradesOfStudentsIn2020.containsKey(letterGrade)) {
                    gradesOfStudentsIn2020.put(letterGrade, 1);
                } else {
                    int counter = gradesOfStudentsIn2020.get(letterGrade)+1;
                    gradesOfStudentsIn2020.replace(letterGrade, counter);
                }
            }
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
        return gradesOfStudentsIn2020;
    }
}
