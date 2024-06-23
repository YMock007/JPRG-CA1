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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class StudentManagementModel {

    public static String getOptionsForSystem(String system) {
        if(system.equals("Student Admin System")) {
            // Define admin options menu
            return "1. Add new student \n2. Delete student \n3. Add new module for student \n4. Add students with csv file \n5. Previous \n6. Quit";
        } else {
            system = "Student Enquire System";
            return "1. Display all students \n2. Search students by class \n3. Search student by name \n4. View General Statistics \n5. Previous \n6. Quit";
        }
    }
    //Validating methods
    
    // Check if a string contains only digits
    public static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkYearPattern(String s) {
        // Check if the length of the string is exactly 2 characters
        if (s.length() != 2) {
            return false; // If not, return false
        }
        
        // Extract the characters from the string
        char firstChar = s.charAt(0);
        char secondChar = s.charAt(1);
        
        // Define valid characters for the first and second positions
        boolean validFirstChar = firstChar == '1' || firstChar == '2' || firstChar == '3';
        boolean validSecondChar = secondChar == 'A' || secondChar == 'B';
        
        // Return true only if both characters match the valid criteria
        return validFirstChar && validSecondChar;
    }
    

    public static boolean checkClassNumber(String str) {
        // Check if the string is null or empty
        if (str == null || str.isEmpty()) {
            return false;
        }
    
        // Check if the string is "00"
        if (str.equals("00")) {
            return false;
        }
    
        // Check the length of the string
        int len = str.length();
        if (len < 1 || len > 2) {
            return false; // Only allow strings of length 1 or 2
        }
    
        // Check each character in the string
        for (char c : str.toCharArray()) {
            // Check if the character is not a digit
            if (!Character.isDigit(c)) {
                return false;
            }
        }
    
        // Convert the string to an integer
        int number = Integer.parseInt(str);
    
        // Check the range
        return number >= 1 && number <= 99;
    }
      

    // Check if a string contains only letters and whitespace characters
    public static boolean containsOnlyLetters(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    // Check if a string contains only letters and digits, no special characters
    public static boolean containsOnlyLettersAndDigits(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                return false;
            }
            }
        return true;
    }

    // Check if first character is a digit and second character is a letter
    public static boolean containsOnlyAlphanumeric(String s) {
        return !(!Character.isDigit(s.charAt(0)) || !Character.isLetter(s.charAt(1)));
    }
    
    // Check if second character is PT or FT.
    public static boolean containsOnlyPTorFT(String s) {
        return s.equals("FT")|| s.equals("PT");
    }

    // Check if a string is empty or contains only whitespace characters
    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

     //Method to check not to add existing module code
    public static boolean checkDuplicateAdminNo(ArrayList<Student> Students, String adminNo) {
        for (Student student : Students) {
            if (student.getAdminNo().equals(adminNo)) {
                StudentManagementView.displayAdminNumExistError(adminNo);
                return false;
            }
        }
        return true;
    }

    public static boolean cannotAdminNumber(String adminNo){
        if(adminNo.equals("p0000000")){
            return true;
        }else return false;
    }

    //Method to check if number is within range or not
    public static boolean isNumberInRange(int checkNumber, int rangeNumber) {
        // Check if the checkNumber is positive
        if (checkNumber <= 0 || checkNumber % 1 != 00) {
            StudentManagementView.displayNotPositiveError();
            return false;
        }
    
        // Check if checkNumber is within the range (1, rangeNumber), inclusive
        if (checkNumber > rangeNumber) {
            StudentManagementView.displayOutOfRangeError(rangeNumber);
            return false;
        }
    
        // If all checks pass, the number is within the range
        return true;
    }
    

    public static boolean isValidFormat(String classCode) {
        if (classCode.length() == 12) {
            return classCode.charAt(3) == '/' && classCode.charAt(6) == '/' && classCode.charAt(9) == '/';
        } else if (classCode.length() == 13) {
            return classCode.charAt(4) == '/' && classCode.charAt(7) == '/' && classCode.charAt(10) == '/';
        } else if (classCode.length() == 14) {
            return classCode.charAt(5) == '/' && classCode.charAt(8) == '/' && classCode.charAt(11) == '/';
        }
        return false;
    }
    
    //Method to check user's input is Uppercase or not
    public static boolean checkUpperCase(String s){
        char[] sArray = s.toCharArray();
        for(char c : sArray){
            if(!Character.isUpperCase(c)){
                return false;
            }
        }
        return true;
    }
    
    // Method to validate module code
    public static boolean validateModuleCode(String m) {
        // Convert module code to character array
        char[] mArray = m.toCharArray();

        // Check if module code is 6 characters long
        if (mArray.length != 6) {
            StudentManagementView.displayCannotModuleCodeError(m);
            return false;
        } 
        // Check if the first 2 characters are alphabets
        else if (!Character.isLetter(mArray[0]) || !Character.isLetter(mArray[1])) {
            StudentManagementView.displayFirst2CharacterModuleCodeError();
            return false;
        }

        // Check if the last 4 characters are digits
        for (int i = 2; i < mArray.length; i++) {
            if (!Character.isDigit(mArray[i])) {
                StudentManagementView.displayLast4CharacterModuleCodeError();
                return false;
            }
        }

        // If all checks pass, the module code is valid
        return true;
    }

    //Method to check not to add existing module code
    public static boolean checkDuplicateModuleCode(ArrayList<Module> modules, String m){
        for(int j = 0; j < modules.size(); j++){
                if(modules.get(j).getModuleCode().equals(m)){
                    StudentManagementView.displayDuplicateModuleCodeError(m);
                    return false;
                }
            }
        return true;
    }
        
    //Method to check not to add existing module name
    public static boolean checkDuplicateModuleName(ArrayList<Module> modules, String m){
        for(int j = 0; j < modules.size(); j++){
                if(modules.get(j).getModuleName().equals(m)){
                    StudentManagementView.displayDuplicateModuleNameError(m);
                    return false;
                }
            }
         if(m.equals("ST0000")){
                StudentManagementView.displayCannotModuleCodeError(m);
                return false;
        }
        return true;
    }
    
        //Method to check student student exists or not
    public static int checkStudentExists(String admNo, ArrayList<Student> students){
        //loop through the students arrayList
        for(int j = 0; j < students.size(); j++){
            //Check existing admin numbers and input admin number equal or not
                if(students.get(j).getAdminNo().equals(admNo)){
                    return j;
                }
            }
        return -1;
    }
    
    //method to check class
    public static void getErrorClassCode(String [] classArray) {
        String output = "";
        if(!containsOnlyLetters(classArray[0])) {
            output += "The course code must contain only letters and be between 3 and 5 characters long.\n";
        } 

        if(!containsOnlyPTorFT(classArray[1])) {
            output += "The student type can only be FT or PT\n";
        }

        if (!checkYearPattern(classArray[2])) {
            output += "The year code must be 1, 2, or 3 followed by A or B.\n";
        }
        
        if(!checkClassNumber(classArray[3])) {
            output += "Class number must consist only of digits and be within the range 01 to 99 inclusive.";
        }

        StudentManagementView.displayClassCodeError(output, "Student Admin System");
    }

    //Validating file path
    public static boolean validateFilePath(String filePath) {
        try {
            // Normalize the path to handle different path separators and relative paths
            Path path = Paths.get(filePath).normalize();

            // Check if path exists and is a regular file
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return true;
            } else if (!Files.exists(path)) {
                // Display file not found message
                StudentManagementView.displayFileNotFoundMessage();
                return false;
            } else {
                // Display invalid file path message (not a regular file)
                StudentManagementView.displayInvalidFilePathMessage();
                return false;
            }

        } catch (InvalidPathException e) {
            // Handle invalid file path format
            StudentManagementView.displayInvalidFilePathMessage();
            return false;
        }
    }

    public static boolean validatingClassCode(String userInput) {
        // Regular expression patterns for each part of the class code
        String coursePattern = "[A-Z]+"; // One or more uppercase letters
        String ftPtPattern = "(FT|PT)";
        String yearPattern = "[123][A-B]"; // One digit (1 or 2) followed by one uppercase letter
        String classPattern = "(0[1-9]|[1-9][0-9])"; // Two digits

        // Combined regular expression for the whole class code
        String classCodePattern = coursePattern + "/" + ftPtPattern + "/" + yearPattern + "/" + classPattern;

        // Compile the pattern
        Pattern pattern = Pattern.compile("^" + classCodePattern + "$");
        // Match the given class code against the pattern
        return pattern.matcher(userInput).matches();
    }

    //--------------------------------------------------------------------------
    // Displaying All Student report 
    //--------------------------------------------------------------------------
 
    public static void displayAllStudent(ArrayList<Student> students) {
        StringBuilder report = new StringBuilder();
        report.append("<html>");
        report.append("<style>");
        report.append("table { width: 100%; border-collapse: collapse; }");
        report.append("th, td { padding: 8px; text-align: left; border: 1px solid black; }");
        report.append("th { background-color: #f2f2f2; }");
        report.append("</style>");
        report.append("<table>");
        report.append("<tr>");
        report.append("<th style='width: 5%;'>Student</th>");
        report.append("<th style='width: 20%;'>Name</th>");
        report.append("<th style='width: 15%;'>Admin Number</th>");
        report.append("<th style='width: 10%;'>Class</th>");
        report.append("<th style='width: 40%;'>Modules Taken</th>");
        report.append("<th style='width: 10%;'>CGPA</th>");
        report.append("</tr>");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            report.append("<tr>");
            report.append("<td>").append(i + 1).append("</td>");
            report.append("<td>").append(student.getStdName()).append("</td>");
            report.append("<td>").append(student.getAdminNo()).append("</td>");
            report.append("<td>").append(student.getClassCode()).append("</td>");
            report.append("<td>");
            
            for (int j = 0; j < student.getModules().size(); j++) {
                Module module = student.getModules().get(j);
                report.append(module.getModuleCode()).append("/").append(module.getModuleName())
                .append("/").append(module.getCreditUnit()).append(":")
                .append(module.calculateGrade()).append("<br>");
            }
            
            report.append("</td>");
                       
            report.append("<td>").append(student.calculateGPA()).append("</td>")
            .append("</tr>");
        }

        report.append("</table>");
        report.append("</html>");

        StudentManagementView.displayAllStudentInTabularFormat(report);
    }
    
    public static int getGradePoint(int mark) {
        if(mark >= 80) {
            return 4;
        } else if(mark >= 70) {
            return 3;
        } else if(mark >=60) {
            return 2;
        } else if(mark >= 50) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static double calculateAvgGPA(double totalGPA, double studentCount) {
        return totalGPA / studentCount;
    }
    
     public static String getStdName() {
        String userInput;
        String question = "Enter the name of student to search";

        while (true) {
            userInput = JOptionPane.showInputDialog(null,
                    question,
                    "Search",
                    JOptionPane.QUESTION_MESSAGE);
            
            if (userInput == null) {  // User clicked cancel or closed the dialog
                return null;
            }
            
            // Trim leading and trailing whitespace and Uppercasing
            userInput = userInput.trim();
            
            // Check if the trimmed input is empty
            if (isEmpty(userInput) || !containsOnlyLetters(userInput)) {
                StudentManagementView.displayInvalidName();;
                continue;
            }

            return userInput;
        }
    }
     
    public static String getUserInput(String options, String system) {
        while (true) {
            String s = JOptionPane.showInputDialog(null, options, system, JOptionPane.QUESTION_MESSAGE);
            if(s == null) {
                return null;
            }
            if(StudentManagementModel.isEmpty(s)) {
                StudentManagementView.displayBothSystemErrorInput(system);
                continue;
            }
            return s;
        }
    }

    public static boolean validateTopN(int i, int size) {
        return i > 0 && i <= size;
    }

    public static boolean validateThreshold(double d, double max) {
        return d > 0.0 && d <= max;
    }

}
