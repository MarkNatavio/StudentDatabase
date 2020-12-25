package sample;

import javafx.scene.canvas.GraphicsContext;
import java.util.Map;
import static java.lang.Integer.MIN_VALUE;

public class MyPieChart {
    private HistogramAlphaBet histogram; // histogram of text
    private int n; // number of most freq characters
    private MyPoint center; // center point of pie chart
    private double r; // pie chart's radius
    // List of all 52 colors
    private MyColor[] colors = new MyColor[]{MyColor.MOCCASIN, MyColor.RED, MyColor.LIME,
            MyColor.YELLOW, MyColor.CYAN, MyColor.MAGENTA, MyColor.LAVENDERLUSH,
            MyColor.SILVER, MyColor.GRAY, MyColor.MAROON, MyColor.OLIVE,
            MyColor.GREEN, MyColor.PURPLE, MyColor.TEAL, MyColor.SKYBLUE,
            MyColor.BROWN, MyColor.DARKORANGE, MyColor.SALMON, MyColor.KHAKI,
            MyColor.YELLOWGREEN, MyColor.DARKGREEN, MyColor.DARKSEAGREEN, MyColor.TURQUOISE,
            MyColor.GREENYELLOW, MyColor.SADDLEBROWN, MyColor.CRIMSON, MyColor.BLACK, MyColor.ROYALBLUE};

    public void draw(GraphicsContext arc) {
        Map<Character, Float> mostFreqChars = histogram.getNMostFreqEvents(n); // hashmap of n most frequent characters
        MyRectangle rect = new MyRectangle(new MyPoint(0,0),200,20+20*n, MyColor.BLACK.getColor());
        rect.draw(arc);
        float sum = 0; // sum of all angles
        float startingAngle = 0; // starting angle of arcs
        double count = 20;
        for (int i = 0; i < n; i++) { // create arcs of n characters
            float probability = MIN_VALUE;
            char currentC = 0;
            // Get characters in order of probability (most probable to least probable)
            for (Map.Entry<Character, Float> findMax : mostFreqChars.entrySet()) {
                if (probability < findMax.getValue()) { // find item with highest probability in map
                    currentC = findMax.getKey(); // save character
                    probability = findMax.getValue(); // save # of occurrences
                }
            }

            // Setting variables used to draw the arc
            float length = probability*360; // get length of arc [(probability*100)*(360/100) = probability*360]

            // Draw arc
            MyArc characterArc = new MyArc(center,startingAngle,length,r,r, colors[i].getColor()); // make arc of current
            characterArc.draw(arc); // draw arc
            System.out.println(characterArc.toString() + ", and a probability of " + probability + "\n"); // print info on the arc

            // Write arc's probability
            arc.fillText(currentC + ", " + probability, 10, count);

            sum += probability; // add probability to solve for probability of all other letters
            startingAngle += probability * 360; // set next starting angle
            mostFreqChars.remove(currentC, probability); // remove from map after added to pie chart
            count += 20;
        }

        // Make arc for all other letters
        if (n != 26) { // if not all letters are shown, show probability of remaining ones
            MyArc otherLettersArc = new MyArc(center, startingAngle, 360 - startingAngle, r, r, colors[27].getColor());
            otherLettersArc.draw(arc); // draw arc of all other letters
            System.out.println(otherLettersArc.toString() + ", and a probability of " + (1-sum)); // print info on arc

            // Write text of all other letters
            arc.fillText("All other letters, " + (1 - sum), 10, count);
        }
    }

    public void drawTableData(GraphicsContext arc) {
        Map<Character, Integer> mostFreqChars = DatabaseModification.intoHashMap(); // hashmap of n most frequent characters

        // Set up legend
        MyRectangle rect = new MyRectangle(new MyPoint(0,0),200,20+20*6, MyColor.BLACK.getColor());
        rect.draw(arc);

        // Set up values
        float startingAng = 0; // starting angle of arcs
        double positionVal = 20; // position of arc info
        char[] grades = new char[]{'A', 'B', 'C', 'D', 'F', 'W'};
        int freq = 0;
        for (Map.Entry<Character, Integer> temp : mostFreqChars.entrySet()) {
            freq += temp.getValue();
        }

        for (int i = 0; i < 6 && mostFreqChars.get(grades[i]) != null; i++) { // create arcs of n characters
            arc.setFill(colors[i].getColor()); // set arc color

            float prob = (float)mostFreqChars.get(grades[i])/freq;
            // Draw arc
            float length = 360*prob;
            MyArc characterArc = new MyArc(center,startingAng,length,r,r, colors[i].getColor()); // make arc of current
            characterArc.draw(arc); // draw arc
            System.out.println(characterArc.toString() + ", and a probability of " + prob + "\n"); // print info on the arc

            // Write arc's probability
            arc.fillText(grades[i] + ", " + mostFreqChars.get(grades[i]), 10, positionVal);

            startingAng += length; // set next starting angle
            mostFreqChars.remove(grades[i], prob); // remove from map after added to pie chart
            positionVal += 20;
        }
    }

    // Pie Charts of histograms
    public MyPieChart(HistogramAlphaBet histogram, int n, MyPoint center, double radius) {
        this.histogram = histogram;
        this.n = n;
        this.center = center;
        this.r = radius;
    }

    // Pie Charts of data tables
    public MyPieChart(MyPoint center, double radius) {
        this.center = center;
        this.r = radius;
    }
}
