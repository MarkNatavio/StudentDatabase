package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import static java.lang.Integer.MIN_VALUE;

public class HistogramAlphaBet {
    private Map<Character, Integer> MAP; // map of all characters in text file
    private int frequencyOfAllEvents; // frequency of all items (# of letters)

    public void createHashMap() { // get frequency of letter
        try {
            File file = new File("/Users/marknatavio/Desktop/CSC 221 - Software Design Laboratory/Assignments/Assignment 3/Alice In Wonderland.txt"); // file path name
            Scanner scan = new Scanner(file); // scan file
            String story = "";
            while (scan.hasNextLine()) { // make file into single line and make it a string
                story = story.concat(scan.nextLine());
            }
            story = story.replaceAll("[^A-Za-z]", "").toLowerCase(); // make string only contain lowercase letters, no symbols
            frequencyOfAllEvents = story.length();
            for (int i = 0; i < story.length(); i++) {
                char letter = story.charAt(i);
                if (!MAP.containsKey(letter)) { // if letter not in map
                    MAP.put(letter,1); // add letter with an occurrence of 1
                } else { // else letter is in map
                    int freq = MAP.get(letter);
                    MAP.replace(letter, ++freq); // add frequency of letter by 1
                }
            }
        }

        catch (FileNotFoundException e) { System.out.println("File not found"); } // if pathname not found display error
    }

    public Map<Character, Float> getNMostFreqEvents(int n) {
        Map<Character, Float> nMostFreqEvents = new HashMap<>(n); // make a hashmap of size n
        for (int i = 0; i < n; i++) {
            char val = 'a';
            int freq = MIN_VALUE;
            for (Map.Entry<Character, Integer> MapEntry : MAP.entrySet()) { // find entry with highest value
                if (freq < MapEntry.getValue()) {
                    val = MapEntry.getKey(); // save character
                    freq = MapEntry.getValue(); // save character's freq
                }
            }
            nMostFreqEvents.put(val, (float)freq/frequencyOfAllEvents); // add highest value to map to be returned
            MAP.remove(val, freq); // remove highest value from actual map
        }
        createHashMap(); // restore original HashMap (in case more pie charts are made)
        return nMostFreqEvents; // return map with n most frequent events
    }

    public HistogramAlphaBet() {
        MAP = new HashMap<>(26); // MAP of all characters (26 letters of the alphabet)
        createHashMap();
    }
}
