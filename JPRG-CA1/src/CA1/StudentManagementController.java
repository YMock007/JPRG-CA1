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


public class StudentManagementController {
    //--------------------------------------------------------------------------
    // Selecting System
    //--------------------------------------------------------------------------
    public static int getSystem() {
        String system;
        int systemInt;
        // Define system options menu
        String systemOptions = "1. Admin System \n2. User system \n3. Quit";
        
        // Loop until valid input is received
        while (true) {
            try {
                system = JOptionPane.showInputDialog(null,
                        systemOptions,
                        "Selecing System",
                        JOptionPane.QUESTION_MESSAGE
                );

                // Check if the user clicked the cancel button or closed the dialog
                if (system == null) {
                    StudentManagementView.displayFinishMessage("Program terminated. Thank you!", "Seleting System");
                    System.exit(0);
                }
                
                // Check if user input is empty
                if (StudentManagementModel.isEmpty(system)) {
                    StudentManagementView.displayErrorMessage("Input cannot be empty!", "System Selection");
                    continue;
                }

                // Parse user input to integer
                systemInt = Integer.parseInt(system);

                return systemInt;
            } catch (NumberFormatException e) {
                // Catch exception if input cannot be parsed to integer
                // Show error message and continue loop
                StudentManagementView.displayErrorMessage("Please enter a valid input!", "System Selection");
            }
        }       
    }
    
    //--------------------------------------------------------------------------
    // Show the admin system options
    //--------------------------------------------------------------------------
    public static void showAdminSystem(ArrayList<Student> students) {
        boolean quit = false;
        final String system = "Student Admin System";// Setting the system name
        Integer select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = getSelect(system);
            if(select != null) {
                switch(select){
                    case 1 -> {
                        StudentManagement.createNewStudent(students, system);
                    }

                    case 2->{
                        StudentManagement.getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
                    }

                    case 3->{
                        StudentManagement.getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
                    }

                    case 4->{
                        quit = true;
                    }
                } 
            } else {
                quit = true;
            }
        } 
    }
    
        //--------------------------------------------------------------------------
    // User system option
    //--------------------------------------------------------------------------
    public static void showUserSystem(ArrayList<Student> students) {
        boolean quit = false;
        final String system = "Student Enquiry System";// Setting the system name
        Integer select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = getSelect(system);
            if(select != null) {
                switch(select){
                    case 1 -> {
                        StudentManagementModel.displayAllStudent(students);
                    }

                    case 2->{
                        searchStudentByClass(students);
                    }

                    case 3->{
                        searchStudentByName(students);
                    }

                    case 4->{
                        quit = true;
                    }
                }
            } else {
                quit = true;
            }
        }
    }
    
    //--------------------------------------------------------------------------
    //Getting user option select  
    //--------------------------------------------------------------------------
    public static Integer getSelect(String system) {
        // Initialize variables to store user input
        String userSelectStr;
        int userSelectInt;
        String options;
                
        if(system.equals("Student Admin System")) {
            // Define admin options menu
            options = "1. Add new student \n2. Delete student \n3. Add new module for student \n4. Previous";
        } else {
            options = "1. Display all students \n2. Search students by class \n3. Search student by name \n4. Previous";
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
                    return null;
                }
                
                // Check if user input is empty
                if (StudentManagementModel.isEmpty(userSelectStr)) {
                    StudentManagementView.displayErrorMessage("Input cannot be empty!", system);
                    continue;
                }

                // Parse user input to integer
                userSelectInt = Integer.parseInt(userSelectStr);

                return userSelectInt;
            } catch (NumberFormatException e) {
                // Catch exception if input cannot be parsed to integer
                // Show error message and continue loop
                StudentManagementView.displayErrorMessage("Please enter a valid input!", system);
            }
        }
    }
    
    //--------------------------------------------------------------------------
    // Search student by Class 
    //--------------------------------------------------------------------------
    public static void searchStudentByClass(ArrayList<Student> students) {
        String classCode = StudentManagementModel.getClassCode();
        if(classCode != null){
            int studentCount = 0;
            double totalGPA = 0.0;
            double avgGPA;
            Student student;
            
            for(int i = 0; i < students.size(); i++ ) {
                student = students.get(i);
                if(student.getClassCode().equals(classCode)){
                    studentCount++;
                    totalGPA += student.calculateGPA();
                }
            }
            
            if(studentCount == 0) {
                StudentManagementView.displayErrorMessage("No student found from the class", "Class Summary");
            } else {
                avgGPA = totalGPA / studentCount;
                avgGPA = Double.parseDouble(String.format("%.2f", avgGPA));
                StudentManagementView.displayClassSummary(classCode,studentCount,avgGPA);    
            }
        }
    }
    
    //--------------------------------------------------------------------------
    // Search student by Name 
    //--------------------------------------------------------------------------
    public static void searchStudentByName(ArrayList<Student> students) {
        String stdName;
        String stdInfo = "";
        int count = 0;
        
        while (true) {
            stdName = StudentManagementModel.getStdName();
            
            if (stdName == null) {
                // User clicked cancel or closed the dialog
                return;
            }

            System.out.println(stdName);
            
            for (Student student : students) {
                if (student.getStdName().toUpperCase().equals(stdName.toUpperCase())) {
                    count++;
                    stdInfo += student.toString() + "\n";                  
                }
            }
            

            if (stdInfo.isBlank()) {
                String errorMessage = "Cannot find the student \"" + stdName + "\"!!";
                StudentManagementView.displayErrorMessage(errorMessage, "Info");
                break;
            } else if (count > 2){
                StudentManagementView.displayFinishMessage("The students with same name exceeds 2.", "Info");
            } else {
                StudentManagementView.displayFinishMessage(stdInfo, "Student Info");
                break;
            }
        }
    }       
}
