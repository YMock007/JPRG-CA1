/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeyin
 * @author shinn
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class StudentManagementModel {

    public static String getOptionsForSystem(String system) {
        if(system.equals("Student Admin System")) {
            // Define admin options menu
            return "1. Add new student \n2. Delete student \n3. Add new module for student \n4. Previous";
        } else {
            system = "Student Enquire System";
            return "1. Display all students \n2. Search students by class \n3. Search student by name \n4. View General Statistics \n5. Previous";
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
            StudentManagementView.displayModuleCodeError();
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
        if(m.equals("ST0000")){
            StudentManagementView.displayCannotModuleCodeError(m);
            return false;
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
    
    public static void getErrorClassCode(String [] classArray) {
        String output = "";
        if(!containsOnlyLetters(classArray[0])) {
            output += "The course code must contain only letters and be between 3 and 5 characters long.\n";
        } 

        if(!containsOnlyPTorFT(classArray[1])) {
            output += "The student type can only be FT or PT\n";
        }

        if(!containsOnlyAlphanumeric(classArray[2])) {
            output += "The year code must be a number follow by an alphabet.\n";
        }

        if(!containsOnlyDigits(classArray[3])) {
            output += "The class number must be positive integer";
        }

        StudentManagementView.displayClassCodeError(output, "Student Admin System");
    }
    
    //--------------------------------------------------------------------------
    // Displaying All Student report 
    //--------------------------------------------------------------------------
 
    public static void displayAllStudent(ArrayList<Student> students) {
        StringBuilder report = new StringBuilder();
        report.append("<html>");
        report.append("<table border='1' style='border-collapse:collapse;'>");
        report.append("<tr>");
        report.append("<th>Student</th>");
        report.append("<th>Name</th>");
        report.append("<th>Admin Number</th>");
        report.append("<th>Class</th>");
        report.append("<th>Modules Taken</th>");
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

            report.append("</td>")
                    .append("</tr>");
        }

        report.append("</table>");
        report.append("</html>");    
        
        StudentManagementView.displayAllStudentinTabularFormat(report);
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
     
}
