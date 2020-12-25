package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) {
        // Setting up the display
        Group root = new Group(); // creates root node where all other java classes link back to
        Scene scene = new Scene(root); // creates container window where all visuals will appear
        int w = 1220, h = 700; // set the canvas' width and height
        Canvas canvas = new Canvas(w, h); // creates a canvas of size wxh
        GraphicsContext graphics = canvas.getGraphicsContext2D(); // put all graphics into the canvas
        root.getChildren().add(canvas); // send all children to the canvas
        stage.setScene(scene); // set the scene to display to be the window container

        double smallest = (w < h)? w:h; // get smallest dimension
        double r = smallest/4; // adapt radius of circles and polygons to smallest dimension

        // Reference point
        MyPoint center = new MyPoint(w/2,h/2);

        // Connect to the database
        DatabaseModification.connect();
        DatabaseModification.deleteTables(); // delete tables previously made

        // Create Student, Courses, and Classes tables
        DatabaseModification.CreateTables();

        // Populates Student, Courses, and Classes tables
        DatabaseModification.populateStudents();
        //DatabaseModification.updateStudent(23759236,"newFirstName", "newLastName", "newEmail", 'U');
        DatabaseModification.populateCourses();
        //DatabaseModification.updateCourses(20700, "physics", "new sciences");
        DatabaseModification.populateClasses();
        DatabaseModification.updateClasses(22000, 23759236, 51658, 2020, "Fall", 'W');

        // Display data in tables
        DatabaseModification.showValues();
        DatabaseModification.gradesOfStudentsIn220();

        // Create pie chart
        MyPieChart pieChart = new MyPieChart(center,r);
        pieChart.drawTableData(graphics);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
