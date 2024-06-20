/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeyin
 */

import javax.swing.JOptionPane;


public class StudentManagementView {
    //--------------------------------------------------------------------------
    // Display error message
    //--------------------------------------------------------------------------
    public static void displayErrorMessage(String message, String system) {
        if(system == null) {
            system = "Student Enquiry System";
        }
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    //--------------------------------------------------------------------------
    // Display information message  
    //--------------------------------------------------------------------------
    public static void displayFinishMessage(String message, String system) {
        JOptionPane.showMessageDialog(null, 
                message, 
                system, 
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    //--------------------------------------------------------------------------
    // Display terminate message
    //--------------------------------------------------------------------------
    public static void displayTerminateMessage() {
        JOptionPane.showMessageDialog(null,
                        "Program terminated. \nThank you!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
    }
    
    //--------------------------------------------------------------------------
    // Display Class Summary 
    //--------------------------------------------------------------------------
    public static void displayClassSummary(String classCode, int studentCount, double avgGPA) {
        String output = "Number of student(s) in " + classCode + ": " + studentCount
                        + "\nAverage GPA: " + avgGPA;
        JOptionPane.showMessageDialog(null,
                        output,
                        "Class Summary",
                        JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
