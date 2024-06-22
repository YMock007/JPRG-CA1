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
import java.util.regex.Pattern;

public class StudentManagementModel {
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

    // Check if a string contains only letters and whitespace characters
    public static boolean containsOnlyLetters(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    // Check if first character is a digit and second character is a letter
    public static boolean containsOnlyAlphanumeric(String s) {
        return !(!Character.isDigit(s.charAt(0)) || !Character.isLetter(s.charAt(1)));
    }

    // Check if a string is empty or contains only whitespace characters
    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

     //Method to check not to add existing module code
    public static boolean checkDuplicateAdminNo(ArrayList<Student> Students, String adminNo) {
        for (Student student : Students) {
            if (student.getAdminNo().equals(adminNo)) {
                StudentManagementView.displayErrorMessage("Admission number " + adminNo + " already exists!", "Student Admin System");
                return false;
            }
        }
        return true;
    }

    //Method to check if number is within range or not
    public static boolean isNumberInRange(int checkNumber, int rangeNumber) {
        // Check if the checkNumber is positive
        if (checkNumber <= 0) {
            StudentManagementView.displayErrorMessage("Number must be positive.", "Number Validation");
            return false;
        }
    
        // Check if checkNumber is within the range (1, rangeNumber), inclusive
        if (checkNumber > rangeNumber) {
            StudentManagementView.displayErrorMessage("Number is out of range. It should be within 1 and " + rangeNumber + ".", "Number Validation");
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

    // Method to check the lengths of components of the class code
    public static boolean checkClassCodeLength(String[] classArray) {
        // Check the length of each component and show error messages if invalid
        if (classArray[0].length() < 3 || classArray[0].length() > 5 ||  !checkUpperCase(classArray[0])) {
            StudentManagementView.displayErrorMessage("Invalid input in course of class.", "Student Admin System");
            return false;
        }
        if (classArray[1].length() != 2 || !checkUpperCase(classArray[1])) {
            StudentManagementView.displayErrorMessage("Invalid input in type of class.", "Student Admin System");
            return false;
        }
        if (classArray[2].length() != 2 || !Character.isUpperCase(classArray[2].charAt(1))) {
            StudentManagementView.displayErrorMessage("Invalid input in stage/path of class.", "Student Admin System");
            return false;
        }
        if (classArray[3].length() != 2) {
            StudentManagementView.displayErrorMessage("Invalid input in class number.", "Student Admin System");
            return false;
        }
        return true;
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
            StudentManagementView.displayErrorMessage("Module code must be 6 characters long.", "Student Admin System");
            return false;
        } 
        // Check if the first 2 characters are alphabets
        else if (!Character.isLetter(mArray[0]) || !Character.isLetter(mArray[1])) {
            StudentManagementView.displayErrorMessage("First 2 characters must be alphabets in module code.", "Student Admin System");
            return false;
        }

        // Check if the last 4 characters are digits
        for (int i = 2; i < mArray.length; i++) {
            if (!Character.isDigit(mArray[i])) {
                StudentManagementView.displayErrorMessage("Last 4 characters must be digits in module code.", "Student Admin System");
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
                    StudentManagementView.displayErrorMessage("The module code " + m + " is already added!", "Student Admin System");
                    return false;
                }
            }
        return true;
    }
        
    //Method to check not to add existing module name
    public static boolean checkDuplicateModuleName(ArrayList<Module> modules, String m){
        for(int j = 0; j < modules.size(); j++){
                if(modules.get(j).getModuleName().equals(m)){
                    StudentManagementView.displayErrorMessage("The module name " + m + " is already added!", "Student Admin System");
                    return false;
                }
            }
        return true;
    }
    
        //Method to check student student exists or not
    public static int checkStudentExists(String admNo, ArrayList<Student> students){
        //loop through the students arrayList
        for(int j = 0; j < students.size(); j++){
            //Check exitsing admin numbers and input admin number equal or not
                if(students.get(j).getAdminNo().equals(admNo)){
                    return j;
                }
            }
        return -1;
    }
    
    
    //--------------------------------------------------------------------------
    // Displaying All Student report 
    //--------------------------------------------------------------------------
    public static void displayAllStudent(ArrayList<Student> students) {
        
        int studentIndex = 1;
        int moduleIndex;
        
        String output = "Display first three students from all students " + students.size() + "\n\n";
        
        for (int i = 0; i < 3; i++) {
            Student student = students.get(i);
            moduleIndex = 1;
            output += "Student " + studentIndex++ + ":\n" +
                      "Name: " + student.getStdName() + "\n" + 
                      "Admin: " + student.getAdminNo() + "\n" +
                      "Class: " + student.getClassCode() + "\n" +
                      "Modules Taken\n";

            for(Module module: student.getModules()) {
                output += moduleIndex++ + "." + module.getModuleCode() + "/" +
                          module.getModuleName() + "/" + + module.getCreditUnit() +
                          ": " + module.calculateGrade() + "\n";
            }
            output +=  "----------------------------\n";
        }
        
        StudentManagementView.displayFinishMessage(output, "All Student Report");       
    }

    public static String getClassCode() {
        String userInput;
        String question = "Enter the class name to search";

        while (true) {
            userInput = JOptionPane.showInputDialog(null,
                    question,
                    "Search",
                    JOptionPane.QUESTION_MESSAGE);

            if (userInput == null) {  // User clicked cancel or closed the dialog
                return null;
            }

            userInput = userInput.trim().toUpperCase();  // Trim leading and trailing whitespace and Uppercasing

            if (validatingClassCode(userInput)) {
                return userInput;
            } else {
                StudentManagementView.displayErrorMessage("Please enter a valid class code in the format Course/FTorPT/Year/ClassNumber (e.g., DIT/FT/2B/22)", "Error");
            }
        }
    }

    public static boolean validatingClassCode(String userInput) {
        // Regular expression patterns for each part of the class code
        String coursePattern = "[A-Z]+"; // One or more uppercase letters
        String ftPtPattern = "(FT|PT)";
        String yearPattern = "[12][A-Z]"; // One digit (1 or 2) followed by one uppercase letter
        String classPattern = "\\d{2}"; // Two digits

        // Combined regular expression for the whole class code
        String classCodePattern = coursePattern + "/" + ftPtPattern + "/" + yearPattern + "/" + classPattern;

        // Compile the pattern
        Pattern pattern = Pattern.compile("^" + classCodePattern + "$");
        System.out.println(classCodePattern);
        System.out.println(pattern);
        System.out.println(pattern.matcher(userInput).matches());
        // Match the given class code against the pattern
        return pattern.matcher(userInput).matches();
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
                StudentManagementView.displayErrorMessage("Please enter a valid input. Input cannot be blank.", "Error");
                continue;
            }

            return userInput;
        }
    }
     
}
