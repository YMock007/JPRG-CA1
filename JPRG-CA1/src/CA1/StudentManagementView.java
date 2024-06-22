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

    public static void displayCreateNewStudentMessage() {
        String system = "Student Enquiry System";
        String message = "New student added successfully!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayBlankInputMessage() {
        String system = "Student Enquiry System";
        String message = "Input cannot be empty. Please enter a valid input.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidStudentNameMessage() {
        String system = "Student Enquiry System";
        String message = "Name cannot contain numbers. Please enter a valid name.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displaySpaceContainMessage() {
        String system = "Student Enquiry System";
        String message = "Input cannot contain spaces. Please enter a valid Input.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayAdmNoLengthErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Invalid input. Please enter a valid Admin Number.\n e.g. p1234567";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayFirstOneIsNotpErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Admin Number must start with 'p'. Please enter a valid Admin Number.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }


    public static void displayExceptFirstOneIsNumberErrorMessage() {
        String system = "Student Enquiry System";
        String message = "All characters after the first one must be numbers. Please enter a valid Admin Number.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayClassLengthErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Invalid input. Please enter a valid class.\n e.g.DIT/FT/1A/01";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayClassValidFormatErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Please enter the class in valid format!.\n e.g.DIT/FT/1A/01";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayMissingClassInformationErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Some information is missing! Please enter a valid input.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
  
    public static void displayFTOrPTErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Invalid input! Type should be only \"FT\" and \"PT\" \n e.g.DIT/FT/1A/01\"";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayAtLeastOneModuleErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Student must take at least one module!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayNumberFormatExceptionErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Invalid Input! Please enter a number!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayModuleNameErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Module name can only contain letters.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidOperationErrorMessage() {
        String system = "Student Enquiry System";
        String message = "Invalid operation selected.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayStudentNotExistErrorMessage(int adminNo) {
        String system = "Student Enquiry System";
        String message = "Student with Admin Number " + adminNo + " does not exist. Please try again.";
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

    public static void displayStudentDeletedMessage() {
        String system = "Student Enquiry System";
        String message = "Student deleted!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayModulesAddedMessage() {
        String system = "Student Enquiry System";
        String message = "Module added successfully";
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
