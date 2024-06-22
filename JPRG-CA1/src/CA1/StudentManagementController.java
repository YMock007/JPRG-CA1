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
                        "Selecting System",
                        JOptionPane.QUESTION_MESSAGE
                );

                // Check if the user clicked the cancel button or closed the dialog
                if (system == null) {
                    StudentManagementView.displayFinishMessage("Program terminated. Thank you!", "Selecting System");
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
                        createNewStudent(students, system);
                    }

                    case 2->{
                        StudentManagementController.getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
                    }

                    case 3->{
                        StudentManagementController.getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
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
//--------------------------------------------------------------------------
// Create New Student
//--------------------------------------------------------------------------
    public static void createNewStudent(ArrayList<Student> students, String system){
        String stdName;
                String adminNo;
                String classCode; //naming variable as "class" will lead to error, it is reserved keyword
                ArrayList<Module> modules = new ArrayList<>();// Creating a list to store modules
                
                // Prompting user to input student name, admin number, class, and modules
                stdName = getStdName(system);
                adminNo = getAdminNo(system, students);
                classCode = getClassCode(system);
                getModule(modules, system);
                
                // Creating a student object with the input data
                Student student = new Student(stdName, adminNo, classCode, modules);
                students.add(student);// Adding the student to the list of students
                StudentManagementView.displayCreateNewStudentMessage();
    }

//*****************************************************************************
    
    //Method to prompt student name
        
    private static String getStdName(String system) {
        while (true) {
            String name = JOptionPane.showInputDialog(null, 
                    "Enter Name:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if user clicked Cancel or closed the dialog
            if (name== null) {
                System.exit(0);
            }
            // Check if name is empty
            if (StudentManagementModel.isEmpty(name)) {
                StudentManagementView.displayBlankInputMessage();
            } 
            // Check if name contains only letters
            else if (!StudentManagementModel.containsOnlyLetters(name)) {
                StudentManagementView.displayInvalidStudentNameMessage();
            } 
            else {
                // If name is valid, format it and return
                return setName(name);
            }
        }
    }

    public static String setName(String name) {
        // Trim leading and trailing spaces, and replace multiple spaces with a single space
        String modifiedName = name.trim().replaceAll("\\s+", " ");
        // Split name into words
        String[] words = modifiedName.split("\\s+");
        // Initialize StringBuilder to construct formatted name
        StringBuilder result = new StringBuilder();

        // Loop through each word in the name
        for (String word : words) {
            // Capitalize the first letter of each word and convert the rest to lowercase
            result.append(Character.toUpperCase(word.charAt(0)));
            if (word.length() > 1) {
                result.append(word.substring(1).toLowerCase());
            }
            // Append a space after each word
            result.append(" ");
        }

        // Convert StringBuilder to String, trim trailing space, and return
        return result.toString().trim();
    }

    //*****************************************************************************

    //Method to prompt student Admin Number

    public static String getAdminNo(String system,  ArrayList<Student> students) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if user clicked Cancel or closed the dialog
            if (adminNo == null) {
                System.exit(0);
            }
            // Check if admin number is empty
            if (StudentManagementModel.isEmpty(adminNo)) {
                StudentManagementView.displayBlankInputMessage();
            } 
            // Check if admin number contains spaces
            else if (adminNo.contains(" ")) {
                StudentManagementView.displaySpaceContainMessage();
            } 
            // Check if admin number length is not equal to 8
            else if (adminNo.length() != 8) {
                StudentManagementView.displayAdmNoLengthErrorMessage();
            } 
            // Check if first character of admin number is not 'p' or a letter
            else if (adminNo.charAt(0) != 'p') {
                StudentManagementView.displayFirstOneIsNotpErrorMessage();
            } 
            // Check if all characters after the first one are digits
            else if (!StudentManagementModel.containsOnlyDigits(adminNo.substring(1))) {
                StudentManagementView.displayExceptFirstOneIsNumberErrorMessage();
            } else if (!StudentManagementModel.checkDuplicateAdminNo(students, adminNo)) {
                // No additional error message here since checkDuplicateAdminNo already handles it
            } else {
                return adminNo;
            }
        }
    }

    //*****************************************************************************

    //Method to prompt student's Class

    // Method to prompt the user to enter a class code and validate the input
    public static String getClassCode(String system) {
        while (true) {
            String classCode = JOptionPane.showInputDialog(null,
                    "Enter Class:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if user clicked Cancel or closed the dialog
            if (classCode == null) {
                System.exit(0);
            }

            // Check if class code is empty
            if (StudentManagementModel.isEmpty(classCode)) {
                StudentManagementView.displayBlankInputMessage();
                continue;
            } 
            // Check if class code contains spaces
            else if (classCode.contains(" ")) {
                StudentManagementView.displaySpaceContainMessage();
                continue;
            } 
            // Check if class code length is either 12 or 13 characters
            if (classCode.length() != 12 && classCode.length() != 13 && classCode.length() != 14) {
                StudentManagementView.displayClassLengthErrorMessage();
                continue;
            }

            if (!StudentManagementModel.isValidFormat(classCode)) {
                StudentManagementView.displayClassValidFormatErrorMessage();
                continue;
            }

            // Split class code into an array based on '/'
            String[] classArray = classCode.split("/");

            // Check if all components of the class code are present and have valid lengths
            if (classArray.length != 4) {
                StudentManagementView.displayMissingClassInformationErrorMessage();
            } else {
                // Check individual components for validity
                if (!StudentManagementModel.checkClassCodeLength(classArray)) {
                    continue;
                }
                // Validate the format of each component
                if (!StudentManagementModel.containsOnlyLetters(classArray[0]) || !StudentManagementModel.containsOnlyLetters(classArray[1]) || !StudentManagementModel.containsOnlyAlphanumeric(classArray[2]) || !StudentManagementModel.containsOnlyDigits(classArray[3])) {
                    StudentManagementView.displayClassValidFormatErrorMessage();
                }
                // Check if the type of class is either "FT" or "PT"
                if (!(classArray[1].equals("FT") || classArray[1].equals("PT"))) {
                    StudentManagementView.displayFTOrPTErrorMessage();
                    continue;
                }
            }
            return classCode;
        }
    }

    //*****************************************************************************

    //Method to get student's modules

    // Method to prompt the user to input module information and validate it
    public static void getModule(ArrayList<Module> modules, String system) {
        while (true) {
            try {
                // Prompt user to input number of modules taken
                String numberStr = JOptionPane.showInputDialog(null,
                        "Enter number of modules taken:", 
                        system, 
                        JOptionPane.QUESTION_MESSAGE);
                int numberInt = Integer.parseInt(numberStr);

                // Check if user clicked Cancel or closed the dialog
                if (numberStr == null) {
                    System.exit(0);
                }

                // Check if number of modules is at least 1
                if (numberInt <= 0) {
                    StudentManagementView.displayAtLeastOneModuleErrorMessage();
                    continue;
                }

                // Loop to prompt user for module information for each module
                for (int i = 1; i <= numberInt; i++) {
                    String moduleCode = getModuleCode(i, system, modules); // Get module code
                    String moduleName = getModuleName(i, system, modules); // Get module name
                    int creditUnit = getCreditUnit(i, system); // Get credit unit
                    int studentMark = getMark(i, system); // Get student mark
                    Module module = new Module(moduleCode, moduleName, creditUnit, studentMark); // Create module object
                    modules.add(module); // Add module to the list
                }
                break; // Exit loop if all modules are successfully added
            } catch (NumberFormatException e) {
                StudentManagementView.displayNumberFormatExceptionErrorMessage();
            }
        }
    }

    // Method to prompt user for module code input and validate it
    public static String getModuleCode(int i, String system, ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module code for a specific module
            String mc = JOptionPane.showInputDialog(null,
                    "Enter module code for module " + i + ":",
                    system,
                    JOptionPane.QUESTION_MESSAGE);

            // Check if user clicked Cancel or closed the dialog
            if (mc == null) {
                System.exit(0);
            }

            // Check if input is empty
            if (StudentManagementModel.isEmpty(mc)) {
                StudentManagementView.displayBlankInputMessage();
                continue;
            }
            // Validate module code
            if (!StudentManagementModel.validateModuleCode(mc)) {
                continue;
            }else if(!StudentManagementModel.checkDuplicateModuleCode(modules, mc)){
                continue;
            }
            return mc; // Return valid module code
        }
    }

    // Method to prompt user for module name input and validate it
    public static String getModuleName(int i, String system,  ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module name for a specific module
            String mn = JOptionPane.showInputDialog(null, 
                    "Enter module name for module " + i + ":",
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if user clicked Cancel or closed the dialog
            if (mn == null) {
                System.exit(0);
            }
            // Check if input is empty
            if (StudentManagementModel.isEmpty(mn)) {
                StudentManagementView.displayBlankInputMessage();
                continue;
            }

            // Check if module name contains only letters
            if (!StudentManagementModel.containsOnlyLetters(mn)) {
                StudentManagementView.displayModuleNameErrorMessage();
                continue;
            }
            
            if(!StudentManagementModel.checkDuplicateModuleName(modules, mn)){
                continue;
            }

            return mn; 
        }
    }

    // Method to prompt user for credit unit input and validate it
    public static int getCreditUnit(int i, String system) {
        while (true) {
            try {
                // Prompt user for credit unit for a specific module
                String cu = JOptionPane.showInputDialog(null,
                        "Enter credit unit for module " + i + ":",
                        system, 
                        JOptionPane.QUESTION_MESSAGE);

                // Check if user clicked Cancel or closed the dialog
                if (cu == null) {
                    System.exit(0);
                }
                // Check if input is empty
                if (StudentManagementModel.isEmpty(cu)) {
                    StudentManagementView.displayBlankInputMessage();
                    continue;
                }

                int creditUnit = Integer.parseInt(cu); 
                if(!StudentManagementModel.isNumberInRange(creditUnit, 6)){
                    continue;
                }else return creditUnit;
                
            } catch (NumberFormatException e) {
                StudentManagementView.displayNumberFormatExceptionErrorMessage();
            }
        }
    }

    // Method to prompt user for module mark input and validate it
    public static int getMark(int i, String system) {
        while (true) {
            try {
                // Prompt user for module mark for a specific module
                String m = JOptionPane.showInputDialog(null, 
                        "Enter module mark for module " + i + ":",
                        system, 
                        JOptionPane.QUESTION_MESSAGE);
                        
                // Check if user clicked Cancel or closed the dialog
                if (m == null) {
                    System.exit(0);
                }
                // Check if input is empty
                if (StudentManagementModel.isEmpty(m)) {
                    StudentManagementView.displayBlankInputMessage();
                    continue;
                }

                int mark = Integer.parseInt(m); 
                if(!StudentManagementModel.isNumberInRange(mark, 100)){
                    continue;
                }else return mark; 
                
            } catch (NumberFormatException e) {
                StudentManagementView.displayNumberFormatExceptionErrorMessage();
            }
        }
    }

//--------------------------------------------------------------------------
// Delete OR Update Student
//--------------------------------------------------------------------------
    
    // Method to prompt user admin number for adding new modules or deleting student
    public static void getAdminNoForUpdateOrDeleteBothInOne(String system, int select, ArrayList<Student> students) {
        while (true) {
            // Prompt user for the Admin Number
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number of student:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Handle Cancel or X button click
            if (adminNo == null) {
                System.exit(0);
            }

            adminNo = adminNo.trim(); // Remove leading and trailing spaces

            // Validate the Admin Number
            if (StudentManagementModel.isEmpty(adminNo)) {
                StudentManagementView.displayBlankInputMessage();
            } else if (adminNo.contains(" ")) {
                StudentManagementView.displaySpaceContainMessage();
            } else if (adminNo.length() != 8) {
                StudentManagementView.displayAdmNoLengthErrorMessage();
            } else if (adminNo.charAt(0) != 'p' && !Character.isLetter(adminNo.charAt(0))) {
                StudentManagementView.displayFirstOneIsNotpErrorMessage();
            } else if (!StudentManagementModel.containsOnlyDigits(adminNo.substring(1))) {
                StudentManagementView.displayExceptFirstOneIsNumberErrorMessage();
            } else {
                // Check if the student exists
                int index = StudentManagementModel.checkStudentExists(adminNo, students);
                if (index != -1) {
                    // Perform the selected operation
                    switch (select) {
                        case 2 -> { // Delete student
                            deleteExistingStudent(index, students);
                            return; // Exit method after deletion
                        }
                        case 3 -> { // Add new modules
                            updateStudentModule(index, students.get(index).getModules(), students, system);
                            return; // Exit method after module update
                        }
                        default -> StudentManagementView.displayInvalidOperationErrorMessage();
                    }
                } else {
                    StudentManagementView.displayStudentNotExistErrorMessage(index);
                    // Continue loop to prompt for admin number again
                }
            }
        }
    }
    
    //Method to delete existing student
    public static void deleteExistingStudent(int index, ArrayList<Student> students){
        //Delete student using remove method declared in Student Class
        students.remove(index);
        StudentManagementView.displayStudentDeletedMessage();
    }
    
    // Method to prompt the user to input module information and validate it
    public static void updateStudentModule(int index, ArrayList<Module> modules, ArrayList<Student> students, String system) {
        //Get number of modules of specific student
        int i = students.get(index).getModules().size() + 1;
        String moduleCode = getModuleCode(i, system, modules); // Get module code
        String moduleName = getModuleName(i, system, modules); // Get module name
        int creditUnit = getCreditUnit(i, system); // Get credit unit
        int studentMark = getMark(i, system); // Get student mark
        Module module = new Module(moduleCode, moduleName, creditUnit, studentMark); // Create module object
        //Add new module to modules of specific student using index and addModule method declared in Student Class
        students.get(index).addModule(module);
        StudentManagementView.displayModulesAddedMessage();
    }

//*****************************************************************************

}
