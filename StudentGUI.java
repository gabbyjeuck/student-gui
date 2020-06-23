/*
 * Filename: StudentGUI.java 
 * Author:  Gabrielle Jeuck
 * Purpose: Creates GUI for Student and runs all operations of the GUI itself. 
 *          Program takes user input and stores students in database.  
 *          Update adds grades.  Find searches database and calculates GPA of student.    
 */
package studentgui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.*;

/**
 *
 * @author gwins
 */
public final class StudentGUI extends JFrame {

    // frame declaration
    private final JFrame frame;
    // content for frame declaration
    private JLabel idLabel, nameLabel, majorLabel, selectLabel;
    private JTextField idTextField, nameTextField, majorTextField;
    private JComboBox<String> selectCombo;
    private JButton processBtn;
    private String id, name, major, select;
    private HashMap<String, Student> studentData;
    private String[] selectStr = {"Insert", "Delete", "Find", "Update"};
    private String[] gradeStr = {"A", "B", "C", "D", "F"};
    private String[] creditsStr = {"3", "6"};

    // constructor
    public StudentGUI() throws IOException {
        //frame settings
        frame = new JFrame("Project 4"); // frame title
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // when clicking "x" closes program
        frame.setPreferredSize(new Dimension(350, 235));
        frame.setResizable(false);  // prevents resizing of screen
        frame.setLocationRelativeTo(null); // centers app to screen
        frame.setVisible(true); // allows window to be visible
        // add container
        addComponentsToPane(frame.getContentPane());
        frame.pack();
    } // end studentGUI

    public void addComponentsToPane(Container pane) throws IOException {
        // layout setup
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Database details
        studentData = new HashMap<>();
        String[] selectStr = {"Insert", "Delete", "Find", "Update"};
        String[] gradeStr = {"A", "B", "C", "D", "F"};
        String[] creditsStr = {"3", "6"};

        // ID LABEL
        idLabel = new JLabel("ID: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.5;
        //                    top, left, bottom, right
        c.insets = new Insets(0, 10, 0, 10);
        pane.add(idLabel, c);

        // ID TextField
        idTextField = new JTextField("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .5;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(idTextField, c);

        //Name Label
        nameLabel = new JLabel("Name: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.insets = new Insets(0, 10, 0, 10);
        pane.add(nameLabel, c);

        // Name TextField
        nameTextField = new JTextField("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.5;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(nameTextField, c);

        // Major Label
        majorLabel = new JLabel("Major: ");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        c.insets = new Insets(0, 10, 0, 10);
        pane.add(majorLabel, c);

        // Major TextField
        majorTextField = new JTextField("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.5;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(majorTextField, c);

        // Select Label
        selectLabel = new JLabel("Choose Selection:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.0;
        c.weighty = 0.5;
        c.insets = new Insets(0, 10, 0, 10);
        pane.add(selectLabel, c);

        // Select ComboBox Dropdown
        selectCombo = new JComboBox(selectStr);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.0;
        c.weighty = 0.5;
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(selectCombo, c);

        // Process Button
        processBtn = new JButton("Process Request");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = .30;
        processBtn.setPreferredSize(new Dimension(75, 25));
        c.insets = new Insets(0, 0, 0, 0);
        pane.add(processBtn, c);
        processBtn.addActionListener(new ButtonListener());
    } // end addComponentsToPane

    private class ButtonListener implements ActionListener {

        public ButtonListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // gathers information from textFields and stores into new Strings
            id = idTextField.getText();
            name = nameTextField.getText();
            major = majorTextField.getText();
            select = selectCombo.getSelectedItem().toString();

            try {

                // if primary key ID is not provided will throw NullPointerException
                if (id.isEmpty()) {
                    throw new NullPointerException();
                    // else will run per selection
                } else {
                    switch (select) {
                        // Insert will place id, name, major textfields into hashmap if not already in database.
                        case "Insert":
                            if (studentData.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID already exists.", "Error Duplicate ID Detected", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // makes user input all data instead of just an ID for database
                                if (id.isEmpty() || name.isEmpty() || major.isEmpty()) {
                                    throw new NullPointerException();
                                } else {
                                    // if all input is inserted, will place student into database
                                    studentData.put(id, new Student(name, major));
                                    JOptionPane.showMessageDialog(null, "Student has been added to database.", "Successfully Added", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            break;
                        // Delete will remove id, name, major from hashmap if in database.
                        case "Delete":
                            if (!studentData.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID was not found in database.", "Error ID not Found", JOptionPane.ERROR_MESSAGE);
                            } else {
                                studentData.remove(id);
                                JOptionPane.showMessageDialog(null, "Student has been removed.", "Successfully Deleted", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                        case "Find":
                            // Find will show message with Students info and calculate GPA
                            if (!studentData.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID not found in database.", "Error ID not Found", JOptionPane.ERROR_MESSAGE);
                            } else {
                                studentData.get(id);
                                String toString = studentData.get(id).toString();
                                JOptionPane.showMessageDialog(null, "Found Student Details: \n" + toString, "Successfully Found", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                        case "Update":
                            // Update will allow to insert grades and credits to allow calculations
                            if (studentData.containsKey(id)) {
                                String grade = (String) JOptionPane.showInputDialog(null, "Choose grade: ", "Input Completed Grade", JOptionPane.QUESTION_MESSAGE, null, gradeStr, gradeStr[0]);
                                // as long as a grade is inserted will be prompted to insert credit hours
                                if (grade != null) {
                                    String credits = (String) JOptionPane.showInputDialog(null, "Choose credits: ", "Input Completed Credits", JOptionPane.QUESTION_MESSAGE, null, creditsStr, creditsStr[0]);
                                    // if credits is not empty will parse to int to calculate gpa
                                    if (credits != null) {
                                        studentData.get(id).courseCompleted(grade, Integer.parseInt(credits));
                                        JOptionPane.showMessageDialog(null, "Grades Updated", "Successfully Updated", JOptionPane.INFORMATION_MESSAGE);
                                        // else error message for no credits being inserted
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Credits were not entered", "Error", JOptionPane.ERROR_MESSAGE);
                                    }// end else credits not inserted
                                    // else error message for no grades being inserted
                                } else {
                                    JOptionPane.showMessageDialog(null, "Grades were not entered", "Error", JOptionPane.ERROR_MESSAGE);
                                } // end else Grades not inserted 
                                // else ID was not inserted into TextField which results in error message
                            } else {
                                JOptionPane.showMessageDialog(null, "ID not found in database.", "Error", JOptionPane.ERROR_MESSAGE);
                            } // end else ID not found
                    } // end select
                } // end if ID is empty throw null
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Please insert an ID, Name, and Major", "Error", JOptionPane.ERROR_MESSAGE);

            }// end catch

        }// end actionevent
    } // end button listener

} // end class
