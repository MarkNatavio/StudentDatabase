# StudentDatabase

============================================
* THIS PROGRAM WAS MADE USING INTELIJ IDEA *

============================================


1. Consider the Student database and Listing 25.2 in Chapter 25 of the textbook. Your tasks are:
  b. Amend the database to include the following Tables:
    Students(empID, firstName, lastName, email, sex)
    Courses(courseID, courseTitle, department)
    Classes(courseID, studentID, sectionNo, year, semester, grade)
    
    The underlined attributes are the primary keys of theirs corresponding tables.
    The value of attribute sex may be either ‘F’, ‘M’, or ‘U’. The only letter grades allowed in the database are A, B, C, D, F, and W.
  c. Amend Listing 25.2 to build and test a Java application that connects to the database, creates, populates, and updates the Students, Courses, and Classes Tables.
  d. Amend Listing 25.2 to return and display the number of students for each letter grade in CSc 22000 [Algorithms] in the Fall 2020 semester.  
  e. Utilize the Java classes in Assignment 3 to build and display a pie chart showing the proportion of students for each grade. In the pie chart:
    
    i. Each segment has a different color;
    ii. Each segment has a legend showing the corresponding grade and number of students;
    iii. The segments for the grades are displayed in alphabetical order.

2. The report should show sample input tables and output table for the stated query and corresponding pie chart for a sufficient amount of input data.

3. You may only use JavaFX graphics and your own classes and methods for the operations included. Further,
  a. The code is applicable to canvases of variable height and width
  b. The size of the pie chart are proportional to the smallest dimension of the canvas
  c. The segments of the pie chart are filled with different colors of your choice, specified through a MyColor enum reference type.

4. Explicitly specify all the classes imported and used in your Java application.

5. Your application should utilize “PreparedStatement” objects for the execution of SQL statements/queries.

6. You may use any Relational Database Management System (RDBMS) of your choice.
