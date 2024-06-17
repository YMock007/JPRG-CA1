/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeyin
 */


import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.Random;
import java.util.regex.Pattern;

public class StudentManagentController {
    //Getting user option select
    
    private static int getSelect(String system) {
        // Initialize variables to store user input
        String userSelectStr;
        int userSelectInt;
        String options;
                
        if(system.equals("Student Admin System")) {
            // Define admin options menu
            options = "1. Add new student \n2. Delete student \n3. Add new module for student \n4. Quit";
        } else {
            options = "1. Display all students \n2. Search students by class \n3. Search student by name \n4. Quit";
        }

        // Loop until valid input is received
        while (true) {
            try {
                userSelectStr = JOptionPane.showInputDialog(null,
                        options,
                        system,
                        JOptionPane.QUESTION_MESSAGE
                );
                
                // Check if the user clicked the cancel button or closed the dialog
                if (userSelectStr == null) {
                    StudentManagementView.showFinishMessage("Program terminated. Thank you!", system);
                    System.exit(0);
                }
                
                // Check if user input is empty
                if (StudentManagementModel.isEmpty(userSelectStr)) {
                    StudentManagementView.showErrorMessage("Input cannot be empty!");
                    continue;
                }

                // Parse user input to integer
                userSelectInt = Integer.parseInt(userSelectStr);

                return userSelectInt;
            } catch (NumberFormatException e) {
                // Catch exception if input cannot be parsed to integer
                // Show error message and continue loop
                StudentManagementView.showErrorMessage("Please enter a valid input!");
            }
        }
    }
    
//*****************************************************************************
}
