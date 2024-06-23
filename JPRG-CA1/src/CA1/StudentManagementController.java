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
import java.util.ArrayList;


public class StudentManagementController {
    //--------------------------------------------------------------------------
    // Selecting System
    //--------------------------------------------------------------------------
    public static void run() {
        Integer system; // Variable to hold user input for system operation
        boolean quit = false;
        ArrayList<Student> students = new ArrayList<>(); // Creating a list to store students
        
        // Simulate adding students to the ArrayList
        SimulateStudents.addStudents(students);
    
        // Main loop to handle user interaction
        while (!quit) {
            system = StudentManagementController.getSystem(); // Get user input for system operation
    
            if (system != null) {
                // Handle different system operations based on user input
                switch (system) {
                    case 1:
                        // Show administrative system
                        StudentManagementController.showAdminSystem(students);
                        break;
                    case 2:
                        // Show enquiry system
                        StudentManagementController.showEnquirySystem(students);
                        break;
                    case 3:
                        // Terminate the program
                        StudentManagementView.displayTerminateMessage();
                        System.exit(0); 
                    default:
                        // Display error message for invalid operation
                        StudentManagementView.displayInvalidOperationErrorMessage("Selecting System");
                        break;
                }
            } else {
                // If system input is null, terminate the program
                quit = true;
                StudentManagementView.displayTerminateMessage();
            }
        }
    }
    
    public static Integer getSystem() {
        int systemInt;
        String options = "1. Admin System \n2. Enquiry System \n3. Quit"; // Define system options menu
    
        while (true) {
            try {
                // Display options and get user input
                String userInput = StudentManagementModel.getUserInput(options, "Selecting System");
    
                // Check if the user clicked the cancel button or closed the dialog
                if (userInput == null) {
                    return null; 
                }
    
                // Trim the input to remove any leading or trailing whitespace
                userInput = userInput.trim();
    
                // Check if user input is empty
                if (StudentManagementModel.isEmpty(userInput)) {
                    StudentManagementView.displaySystemSelectingErrorMessage();
                    continue; 
                }

                systemInt = Integer.parseInt(userInput);
    
                // Validate the range of input
                if (systemInt < 1 || systemInt > 3) {
                    StudentManagementView.displaySystemSelectingErrorMessage();
                    continue; // Ask for input again if it's out of range
                }
    
                return systemInt; // Return valid input
            } catch (NumberFormatException e) {
                StudentManagementView.displaySystemSelectingErrorInvalidInput();
            }
        }
    }

    //--------------------------------------------------------------------------
    // Show the admin system options
    //--------------------------------------------------------------------------
    public static void showAdminSystem(ArrayList<Student> students) {
        boolean quit = false;
        String system = "Student Admin System";// Setting the system name
        String select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = getSelect(system);
            if(select != null) {
                switch(select){
                    case "1":
                        StudentManagementController.createNewStudent(students, system);
                        break;

                    case "2":
                        StudentManagementController.getAdminNoForUpdateOrDeleteBothInOne(system, Integer.parseInt(select), students);
                        break;

                    case "3":
                        StudentManagementController.getAdminNoForUpdateOrDeleteBothInOne(system, Integer.parseInt(select), students);
                        break;

                    case "4":
                        StudentManagementController.getFilePath(students);
                        break;

                    case "5":
                        quit = true;
                        break;

                    case "6":
                        StudentManagementView.displayTerminateMessage();
                        System.exit(0);              

                    default:
                        StudentManagementView.displayInvalidOperationErrorMessage(system);
                        break;   
                } 
            } else {
                    quit = true;
            }
        } 
    }
    
        //--------------------------------------------------------------------------
    // User system option
    //--------------------------------------------------------------------------
    public static void showEnquirySystem(ArrayList<Student> students) {
        boolean quit = false;
        String system = "Student Enquire System";// Setting the system name
        String select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = getSelect(system);
            if(select != null) {
                switch(select){
                    case "1": 
                        StudentManagementModel.displayAllStudent(students);
                        break;

                    case "2":
                        searchStudentByClass(students);
                        break;

                    case "3":
                        searchStudentByName(students);
                        break;

                    case "4": 
                        viewGeneralStatistics(students);
                        break;

                    case "5":
                        quit = true;
                        break;

                    case "6":
                        StudentManagementView.displayTerminateMessage();
                        System.exit(0);                  

                    default:
                        StudentManagementView.displayInvalidOperationErrorMessage(system);
                        break;
                }
            } else {
                quit = true;
            }
        }
    }
    
    //--------------------------------------------------------------------------
    //Getting user option select  
    //--------------------------------------------------------------------------
    public static String getSelect(String system) {
        // Initialize variables to store user input
        String userSelectStr;
        String options;
                
        options = StudentManagementModel.getOptionsForSystem(system);

        // Loop until valid input is received
        while (true) {
            userSelectStr = StudentManagementModel.getUserInput(options, system);
                
               // Check if the user clicked the cancel button or closed the dialog
               if (userSelectStr == null) {
                return null;
            }
                
            // Check if user input is empty
            if (StudentManagementModel.isEmpty(userSelectStr)) {
                   StudentManagementView.displayBothSystemErrorInput(system);
                continue;
            }

        return userSelectStr;
        } 
    }

    
    public static void viewGeneralStatistics(ArrayList<Student> students) {
        boolean quit = false;
        
        while (!quit) {
            String[] options = {"1. Show Top Performing Students", "2. Show Students Needing Improvement", "3. Generate CSV Report", "4. Generate Text Report", "5. Back"};
            String selection = StudentManagementModel.getUserInput(String.join("\n", options), "Generate Reports");

            if(selection != null) {
                switch (selection) {
                    case "1":
                        String topN;
                        int topNInt;
                        String s1 = "Enter number of top performing students to display:";
                        String system1 = "Top Performing Students";
                        while (true) {
                            topN = StudentManagementModel.getUserInput(s1, system1);
                            if(topN == null) {
                                return;
                            }
                            try {
                                topNInt = Integer.parseInt(topN);
                                if (StudentManagementModel.validateTopN(topNInt, students.size())) {
                                    break; // Exit loop if input is valid
                                } else {
                                    StudentManagementView.displayOutOfRangeError(students.size());
                                }
                            } catch (NumberFormatException e) {
                                StudentManagementView.displayNumberFormatExceptionErrorMessage("Number Validation");
                            }
                        }
                        ArrayList<Student> topStudents = ViewGeneralStatistics.getTopPerformingStudents(students, topNInt);
    
                        StudentManagementModel.displayAllStudent(topStudents);
                        
                        break;
                    case "2":
                        String threshold;
                        double thresholdDouble;
                        double maxGPA = 4.0;
                        String s2 = "Enter GPA threshold for students needing improvement:";
                        String system2 = "Students Needing Improvement";
    
                        while (true) {
                            threshold = StudentManagementModel.getUserInput(s2, system2);
                            if(threshold == null) {
                                return;
                            }
                            try {
                                thresholdDouble = Double.parseDouble(threshold);
                                if (StudentManagementModel.validateThreshold(thresholdDouble, maxGPA)) {
                                    break; // Exit loop if input is valid
                                } else {
                                    StudentManagementView.displayOutOfRangeError((int) maxGPA);
                                }
                            } catch (NumberFormatException e) {
                                StudentManagementView.displayNumberFormatExceptionErrorMessage("Number Validation");
                            }
                        }
                        ArrayList<Student> studentsNeedingImprovement = ViewGeneralStatistics.getStudentsNeedingImprovement(students, thresholdDouble);
                        if(studentsNeedingImprovement.size() == 0) {
                            StudentManagementView.displayNoStudentCountNeedingImprovement(thresholdDouble);
                        } else {
                            StudentManagementModel.displayAllStudent(studentsNeedingImprovement);
                        }
                        break;
                    case "3":
                        ViewGeneralStatistics.generateCSVReport(students, "students_report.csv");
                        break;
                    case "4":
                        ViewGeneralStatistics.generatePlainTextReport(students, "students_report.txt");              
                        break;
                    case "5":
                        quit = true;
                        break;
                    default:
                        StudentManagementView.displayInvalidOperationErrorMessage("General Statistics");
                        break;
                }
            } else {
                return; 
            }
        }
    }

    //--------------------------------------------------------------------------
    // Search student by Class 
    //--------------------------------------------------------------------------
    public static void searchStudentByClass(ArrayList<Student> students) {
        String classCode = getClassCode("Search");
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
                StudentManagementView.displayNoStudentCountMessage();
            } else {
                avgGPA = StudentManagementModel.calculateAvgGPA(totalGPA, studentCount);
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
            
            for (Student student : students) {
                if (student.getStdName().toUpperCase().equals(stdName.toUpperCase())) {
                    count++;
                    stdInfo += student.toString() + "\n";                  
                }
            }
            

            if (stdInfo.isBlank()) {               
                StudentManagementView.displayNoStudentFoundError(stdName);
                break;
            } else if (count > 2){
                StudentManagementView.displayStudentCountExceedMessage();
            } else {
                StudentManagementView.displayStudentsByName(stdInfo);
                break;
            }
        }
    }     
    
//--------------------------------------------------------------------------
// Admin 
//-------------------------------------------------------------------------

//--------------------------------------------------------------------------
// Create New Student
//--------------------------------------------------------------------------
    public static void createNewStudent(ArrayList<Student> students, String system) {
        String stdName;
        String adminNo;
        String classCode;
        ArrayList<Module> modules = new ArrayList<>(); // Creating a list to store modules
        
        // Prompting user to input student name, admin number, class, and modules
        stdName = getStdName();
        if (stdName == null) {
            return;
        }
        
        adminNo = getAdminNo(students);
        if (adminNo == null) {
            return;
        }
        
        classCode = getClassCode(system);
        if (classCode == null) {
            return;
        }
        
        if(getModule(modules)){
            // Creating a student object with the input data
            Student student = new Student(stdName, adminNo, classCode, modules);
            students.add(student); // Adding the student to the list of students
            StudentManagementView.displayCreateNewStudentMessage();
        }; 
    }

//*****************************************************************************
    
    //Method to prompt student name
    private static String getStdName() {
        while (true) {
            String name = StudentManagementView.getStdName();

            // Check if user clicked Cancel or closed the dialog
            if (name== null) {
                return null;
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

    //Method to format the student name to store
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

    public static String getAdminNo(ArrayList<Student> students) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = StudentManagementView.getAdmNo();
    
            // Check if user clicked Cancel or closed the dialog
            if (adminNo == null) {
                return null;
            }
    
            // Check if admin number is empty
            if (StudentManagementModel.isEmpty(adminNo)) {
                StudentManagementView.displayBlankInputMessage();
                continue;
            } 
    
            // Check if admin number contains spaces
            if (adminNo.contains(" ")) {
                StudentManagementView.displaySpaceContainMessage();
                continue;
            } 
    
            // Check if admin number length is not equal to 8
            if (adminNo.length() != 8) {
                StudentManagementView.displayAdmNoLengthErrorMessage();
                continue;
            } 
    
            // Check if first character of admin number is not 'p' or a letter
            if (adminNo.charAt(0) != 'p') {
                StudentManagementView.displayFirstOneIsNotpErrorMessage();
                continue;
            } 
    
            // Check if all characters after the first one are digits
            if (!StudentManagementModel.containsOnlyDigits(adminNo.substring(1))) {
                StudentManagementView.displayExceptFirstOneIsNumberErrorMessage();
                continue;
            } 
    
            // Check if the admin number is a duplicate
            if (!StudentManagementModel.checkDuplicateAdminNo(students, adminNo)) {
                continue;
            }
    
            // Check if the admin number is invalid that system will not accept p0000000
            if (StudentManagementModel.cannotAdminNumber(adminNo)) {
                StudentManagementView.displayCannotAdminNumberMessage(adminNo);
                continue;
            }
    
            // If all checks pass, return the valid admin number
            return adminNo;
        }
    }
    
    //*****************************************************************************

    //Method to prompt student's Class

    // Method to prompt the user to enter a class code and validate the input
    public static String getClassCode(String system) {
        String classCode;
        String[] classArray;
        while (true) {
            classCode = StudentManagementView.getClassCode(system);

            // Check if user clicked Cancel or closed the dialog
            if (classCode == null) {
                return null;
            }

            // Split class code into an array based on '/'
            classCode = classCode.toUpperCase();
            classArray = classCode.split("/");

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
            // Check if class code length is either 12 or 13 or 15 characters
            if (classCode.length() != 12 && classCode.length() != 13 && classCode.length() != 14) {
                StudentManagementView.displayClassLengthErrorMessage();
                continue;
            }

            if (!StudentManagementModel.isValidFormat(classCode)) {
                StudentManagementModel.getErrorClassCode(classArray);
                continue;
            }

            // Check if all components of the class code are present and have valid lengths
            if (classArray.length != 4) {
                StudentManagementView.displayMissingClassInformationErrorMessage();
                continue;
            } else if (!StudentManagementModel.validatingClassCode(classCode)) {
                StudentManagementModel.getErrorClassCode(classArray);
                continue;
            }
            return classCode;
        }
    }

    //*****************************************************************************

    //Method to get student's modules

    // Method to prompt the user to input module information and validate it
    public static boolean getModule(ArrayList<Module> modules) {
        while (true) {
            try {
                // Prompt user to input number of modules taken
                String numberStr = StudentManagementView.getModuleCount();
                
                // Check if user clicked Cancel or closed the dialog
                if (numberStr == null) {
                    return false;
                }
                int numberInt = Integer.parseInt(numberStr);

                // Check if number of modules is at least 1
                if (numberInt <= 0) {
                    StudentManagementView.displayAtLeastOneModuleErrorMessage();
                    continue;
                }

                // Loop to prompt user for module information for each module
                for (int i = 1; i <= numberInt; i++) {
                    String moduleCode = getModuleCode(i, modules); // Get module code
                    if(moduleCode == null){
                        return false;
                    }
                    String moduleName = getModuleName(i, modules); // Get module name
                    if(moduleName == null){
                        return false;
                    }
                    Integer creditUnit = getCreditUnit(i); // Get credit unit
                    if(creditUnit == null){
                        return false;
                    }
                    Integer studentMark = getMark(i); // Get student mark
                    if(studentMark == null){
                        return false;
                    }
                    // Create a new module object
                    Module module = new Module(moduleCode, moduleName, creditUnit, studentMark);
                    
                    // Add module to the list
                    modules.add(module);
                }
                
                return true; // Successfully added modules
            } catch (NumberFormatException e) {
                // Handle if user enters a non-integer for number of modules
                StudentManagementView.displayNumberFormatExceptionErrorMessage();
            }
        }
    }

    // Method to prompt user for module code input and validate it
    public static String getModuleCode(int i, ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module code for a specific module
            String mc = StudentManagementView.getModuleCode(i);

            // Check if user clicked Cancel or closed the dialog
            if (mc == null) {
                return null;
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
            return mc.toUpperCase(); // Return valid module code
        }
    }

    // Method to prompt user for module name input and validate it
    public static String getModuleName(int i, ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module name for a specific module
            String mn = StudentManagementView.getModuleName(i);

            // Check if user clicked Cancel or closed the dialog
            if (mn == null) {
                return null;
            }
            // Check if input is empty
            if (StudentManagementModel.isEmpty(mn)) {
                StudentManagementView.displayBlankInputMessage();
                continue;
            }

            // Check if module name contains only letters
            if (!StudentManagementModel.containsOnlyLettersAndDigits(mn)) {
                StudentManagementView.displayModuleNameErrorMessage();
                continue;
            }
            
            if(!StudentManagementModel.checkDuplicateModuleName(modules, mn)){
                continue;
            }

            return mn.toUpperCase(); 
        }
    }

    // Method to prompt user for credit unit input and validate it
    public static Integer getCreditUnit(int i) {
        while (true) {
            try {
                // Prompt user for credit unit for a specific module
                String cu = StudentManagementView.getModuleCredit(i);

                // Check if user clicked Cancel or closed the dialog
                if (cu == null) {
                    return null;
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
    public static Integer getMark(int i) {
        while (true) {
            try {
                // Prompt user for module mark for a specific module
                String m = StudentManagementView.getModuleMark(i);
                        
                // Check if user clicked Cancel or closed the dialog
                if (m == null) {
                    return null;
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
            String adminNo = StudentManagementView.getAdmNoOfStudent();

            // Handle Cancel or X button click
            if (adminNo == null) {
                return;
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
                    StudentManagementView.displayStudentNotExistErrorMessage(adminNo);
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
        String moduleCode = getModuleCode(i, modules); // Get module code
        if(moduleCode == null) {
            return;
        }

        String moduleName = getModuleName(i, modules); // Get module name
        if(moduleName == null) {
            return;
        }

        Integer creditUnit = getCreditUnit(i); // Get credit unit
        if(creditUnit == null) {
            return;
        }

        Integer studentMark = getMark(i); // Get student mark
        if(studentMark == null) {
            return;
        }
        Module module = new Module(moduleCode.toUpperCase().trim(), moduleName.toUpperCase().trim(), creditUnit, studentMark); // Create module object
        //Add new module to modules of specific student using index and addModule method declared in Student Class
        students.get(index).addModule(module);
        StudentManagementView.displayModulesAddedMessage();
    }

//--------------------------------------------------------------------------
// Add new students or add more modules to existing students using CSV file
//--------------------------------------------------------------------------
    
    public static void getFilePath(ArrayList<Student> students) {
        String filePath;

        while (true) {
            filePath = StudentManagementView.getFilePath();

            if (filePath == null) {
                return; // Exit if cancel or close button is clicked
            }

            filePath = filePath.trim();

            if (filePath.isEmpty()) {
                StudentManagementView.displayBlankInputMessage();
                continue; // Ask for input again if the file path is empty
            }

            if (!StudentManagementModel.validateFilePath(filePath)) {
                continue; // Ask for input again if the file path is invalid
            }

            break; // Valid file path found, exit the loop
        }

        // Now filePath contains a valid file path
        StudentDataHandler.createStudentsFromCSV(students, filePath);
    }
//*****************************************************************************

}
