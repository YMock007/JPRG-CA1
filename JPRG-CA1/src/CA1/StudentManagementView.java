/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author Naing Shin Thant Zaw 
 * @class DIT/FT/2B/23
 * @admission_number p2340797
 * @system Admin System
 * 
 * @author Ye Yint Aung
 * @class DIT/FT/2B/23
 * @admission_number p2340656
 * @system Enquiry System
 * 
 */
import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class StudentManagementView {
    final static String adminSystem = "Student Admin System";
    //--------------------------------------------------------------------------
    // Display error message
    //-------------------------------------------------------------------------- 
    public static void displayErrorGenerateCSV() {
        String message = "Generating CSV failed.";
        String system = "View General Statistics";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayErrorGenerateTXT() {
        String message = "Generating TXT failed.";
        String system = "View General Statistics";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidName() {
        String message = "Invalid Input! Input can not be empty and contain only alphabets.";
        String system = "Student Info";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayBothSystemErrorInput(String system) {
        String message = "Input cannot be empty!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayNoStudentFoundError(String stdName) {
        String message = "Cannot find the student \"" + stdName + "\"!!";
        String system = "Info";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }



    public static void displayCannotAdminNumberMessage(String adminNo) {
        String message ="Admission number with " + adminNo + " cannot be added!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void displayBlankInputMessage() {
        String message = "Input cannot be empty. Please enter a valid input.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayInvalidStudentNameMessage() {
        String message = "Name cannot contain number(s) or special character(s). Please enter a valid name.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displaySpaceContainMessage() {
        String message = "Input cannot contain space(s). Please enter a valid Input.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayAdmNoLengthErrorMessage() {
        String message = "Invalid input. Please enter a valid Admin Number.\n e.g. p1234567";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayFirstOneIsNotpErrorMessage() {
        String message = "Admin Number must start with 'p'. Please enter a valid Admin Number.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }


    public static void displayExceptFirstOneIsNumberErrorMessage() {
        String message = "All characters after the first one must be numbers. Please enter a valid Admin Number.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayClassLengthErrorMessage() {
        String message = "Invalid input. Please enter a valid class.\n e.g.DIT/FT/1A/01";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }


    public static void displayClassCodeError(String message, String system) {
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayMissingClassInformationErrorMessage() {
        String message = "Some information are missing. Please fill as the valid format.(e.g.DIT/FT/1B/11)";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayAtLeastOneModuleErrorMessage() {
        String system = "Student Admin System";
        String message = "Student must take at least one module!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayNumberFormatExceptionErrorMessage() {
        String message = "Invalid Input! Please enter a number!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayNumberFormatExceptionErrorMessage(String system) {
        String message = "Invalid Input! Please enter a number!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayModuleNameErrorMessage() {
        String message = "Module name cannot contain special characters.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidOperationErrorMessage() {

        String message = "Invalid operation selected.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidOperationErrorMessage(String system) {

        String message = "Invalid operation selected.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayStudentNotExistErrorMessage(String adminNo) {
        String message = "Student with Admin Number " + adminNo + " does not exist. Please try again.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displaySystemSelectingErrorMessage() {
        String system = "System Selection";
        String message = "Input cannot be empty!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displaySystemSelectingErrorInvalidInput() {
        String system = "System Selection";
        String message = "Please enter a valid input!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayAdminNumExistError(String adminNo) {
        String message = "Admission number " + adminNo + " already exists!";
        JOptionPane.showMessageDialog(null, 
        message, 
        adminSystem,
        JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayNotPositiveError() {
        String system = "Number Validation";
        String message = "Number must be positive.";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayOutOfRangeError(int rangeNumber) {
        String system = "Number Validation";
        String message = "Number is out of range. It should be within 1 and " + rangeNumber + ".";
        JOptionPane.showMessageDialog(null, 
        message, 
        system,
        JOptionPane.ERROR_MESSAGE);
    }

    
    public static void displayFirst2CharacterModuleCodeError() {
        String message = "First 2 characters must be alphabets in module code.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayLast4CharacterModuleCodeError() {
        String message = "Last 4 characters must be digits in module code.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayDuplicateModuleCodeError(String m) {
        String message = "The module code " + m + " is already added!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayCannotModuleCodeError(String m) {
        String message = "The module code " + m + " cannot be added!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }
    
    public static void displayDuplicateModuleNameError(String m) {
        String message = "The module name " + m + " is already added!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayFileNotFoundMessage() {
        String message = "File not found. Please enter a valid file path.";
        JOptionPane.showMessageDialog(null,
                message,
                "File Not Found",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayInvalidFilePathMessage() {
        String message = "Invalid file path. Please enter a valid path.";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem, 
                JOptionPane.ERROR_MESSAGE);
    }
    
    //--------------------------------------------------------------------------
    // Display information message  
    //--------------------------------------------------------------------------

    public static void displayCreateNewStudentMessage() {
        String message = "New student added successfully!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displaySuccessGenerateCSV() {
        String message = "Generating CSV Success and Place in JPRG-CA1 folder.";
        String system = "View General Statistics";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displaySuccessGenerateTXT() {
        String message = "Generating TEXT Success and Place in JPRG-CA1 folder.";
        String system = "View General Statistics";
        JOptionPane.showMessageDialog(null, 
                message, 
                system,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayAllStudentInTabularFormat(StringBuilder report) {
        String system = "All Student Report";

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(report.toString());
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(800, 600)); // Set the preferred size

        JOptionPane.showMessageDialog(null, 
            scrollPane, 
            system, 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayStudentsByName(String message) {
        String system = "Student Info";
        JOptionPane.showMessageDialog(null, 
        message, 
        system,
        JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayNoStudentCountNeedingImprovement(double threshold) {
        String message = "No student found less than " + (int) threshold + " GPA.";
        String system = "All Student Report";
        JOptionPane.showMessageDialog(null, 
        message, 
        system,
        JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayStudentCountExceedMessage() {
        String message = "The students with same name exceeds 2.";
        String system = "Info";
        JOptionPane.showMessageDialog(null, 
        message, 
        system,
        JOptionPane.INFORMATION_MESSAGE);
    }   

    public static void displayTerminateMessage() {
        String system = "Selecting System";
        String message = "Program terminated.\nThank You!";
        JOptionPane.showMessageDialog(null, 
                message, 
                system, 
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayStudentDeletedMessage() {
        String message = "Student deleted!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayModulesAddedMessage() {
        String message = "Module added successfully!s";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem, 
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayStudentsAddedCSVMessage() {
        String message = "Students from CSV added successfully!";
        JOptionPane.showMessageDialog(null, 
                message, 
                adminSystem, 
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void displayNoStudentCountMessage() {
        String message = "No student found from the class!";
        String system = "Class Summary";
        JOptionPane.showMessageDialog(null, 
        message, 
        system,
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
    
    //--------------------------------------------------------------------------
    // Display question message  
    //--------------------------------------------------------------------------
    
    public static String getStdName() {
        return JOptionPane.showInputDialog(null, "Enter Name:", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getAdmNo() {
        return JOptionPane.showInputDialog(null, "Enter Admin:", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getClassCode(String system) {
        String message = "Enter Class:";
        if(system == "Search") {
            message = "Enter the class name to search";
        }
        return JOptionPane.showInputDialog(null, message, system, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getModuleCount() {
        return JOptionPane.showInputDialog(null, "Enter number of modules taken:", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getModuleCode(int i) {
        return JOptionPane.showInputDialog(null, "Enter module code for module " + i + ":", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getModuleName(int i) {
        return JOptionPane.showInputDialog(null, "Enter module name for module " + i + ":", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getModuleCredit(int i) {
        return JOptionPane.showInputDialog(null, "Enter credit unit for module " + i + ":", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getModuleMark(int i) {
        return JOptionPane.showInputDialog(null, "Enter module mark for module " + i + ":", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getAdmNoOfStudent() {
        return JOptionPane.showInputDialog(null, "Enter admin number of student:", adminSystem, JOptionPane.QUESTION_MESSAGE);
    }

    public static String getFilePath() {
        return JOptionPane.showInputDialog(null, "Enter the CSV file path:", adminSystem, JOptionPane.QUESTION_MESSAGE);
    } 
}
