/*
 * Filename: Student.java 
 * Author:  Gabrielle Jeuck
 * Purpose: Houses main and student constructor.  In addition houses methods 
 *          for computing GPA for Find selection on GUI.
 */
package studentgui;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * @author gwins
 */
public class Student {

    // declarations
    private String name;
    private String major;
    private double gpa = 0.0;
    private double qualityPoints;
    private double gradedPoints;
    private int credits;
    private static DecimalFormat df = new DecimalFormat("#0.00");

    // constructor 
    Student(String name, String major) {
        this.name = name;
        this.major = major;
        qualityPoints = 0.0;
        credits = 0;
    }

    /* 
     * Calculation for determining GPA 
     * Takes input of letter grade and credit hours from program
     * Calculates gpa by dividing credits by qualityPoints
     */
    public double courseCompleted(String grade, int creditHours) {
        switch (grade) {
            case "A":
                gradedPoints = 4.0;
                break;
            case "B":
                gradedPoints = 3.0;
                break;
            case "C":
                gradedPoints = 2.0;
                break;
            case "D":
                gradedPoints = 1.0;
                break;
            case "F":
                gradedPoints = 0.0;
                break;
        }

        gradedPoints = gradedPoints * creditHours;
        qualityPoints += gradedPoints;
        credits += creditHours;
        gpa = qualityPoints / credits;
        return gpa;
    }

    // toString to perform on Find comboBox
    public String toString() {
        String str = "Name: " + name + "\nMajor: " + major + "\nGPA: " + df.format(gpa);
        return str;
    }

    public static void main(String[] args) throws IOException {
        StudentGUI gui = new StudentGUI();
    }

}
