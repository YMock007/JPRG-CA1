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

public class StudentManagementView {
    // Method to display an error message dialog box
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, 
                message, 
                "Student Enquiry System", 
                JOptionPane.ERROR_MESSAGE);
    }
    
    // Method to display an error message dialog box
    public static void showFinishMessage(String message, String system) {
        JOptionPane.showMessageDialog(null, 
                message, 
                system, 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showTerminateMessage() {
        JOptionPane.showMessageDialog(null,
                        "Program terminated. \nThank you!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
    }
}
