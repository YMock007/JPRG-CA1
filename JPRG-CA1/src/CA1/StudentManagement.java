
/**
 *
 * @author shinn
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.Random;
import java.util.regex.Pattern;
public class StudentManagement {
//*****************************************************************************
    
//Create New Student
    
    public static void createNewStudent(ArrayList<Student> students, String system){
        String stdName;
                String adminNo;
                String classCode; //naming variabel as "class" will lead to error, it is reserved keyword
                ArrayList<Module> modules = new ArrayList<>();// Creating a list to store modules
                
                // Prompting user to input student name, admin number, class, and modules
                stdName = getStdName(system);
                adminNo = getAdminNo(system);
                classCode = getClassCode(system);
                getModule(modules, system);
                
                // Creating a student object with the input data
                Student student = new Student(stdName, adminNo, classCode, modules);
                students.add(student);// Adding the student to the list of students
                StudentManagementView.displayFinishMessage("New student added successfully!", "Student Enquiry System");
    }
   
//*****************************************************************************
    
//Method to prompt student name
    
    public static String getStdName(String system) {
        // Loop until a valid name is entered
        while (true) {
            // Prompt user to enter name 
            String name = JOptionPane.showInputDialog(null, 
                    "Enter Name:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if name is empty
            if (StudentManagementModel.isEmpty(name)) {
                StudentManagementView.displayErrorMessage("Name cannot be empty. Please enter a valid name.", "Student Admin System");
            } 
            // Check if name contains only letters
            else if (!StudentManagementModel.containsOnlyLetters(name)) {
                StudentManagementView.displayErrorMessage("Name cannot contain numbers. Please enter a valid name.", "Student Admin System");
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
    
    public static String getAdminNo(String system) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if admin number is empty
            if (StudentManagementModel.isEmpty(adminNo)) {
                StudentManagementView.displayErrorMessage("Admin Number cannot be empty. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if admin number contains spaces
            else if (adminNo.contains(" ")) {
                StudentManagementView.displayErrorMessage("Admin Number cannot contain spaces. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if admin number length is not equal to 8
            else if (adminNo.length() != 8) {
                StudentManagementView.displayErrorMessage("Invalid input. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if first character of admin number is not 'p' or a letter
            else if (adminNo.charAt(0) != 'p' || !Character.isLetter(adminNo.charAt(0))) {
                StudentManagementView.displayErrorMessage("Admin Number must start with a letter or 'p'. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if all characters after the first one are digits
            else if (!StudentManagementModel.containsOnlyDigits(adminNo.substring(1))) {
                StudentManagementView.displayErrorMessage("All characters after the first one must be numbers. Please enter a valid Admin Number.", "Student Admin System");
            } 
            else {
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

            // Check if class code is empty
            if (StudentManagementModel.isEmpty(classCode)) {
                StudentManagementView.displayErrorMessage("Class cannot be empty. Please enter a valid Class.", "Student Admin System");
                continue;
            } 
            // Check if class code contains spaces
            else if (classCode.contains(" ")) {
                StudentManagementView.displayErrorMessage("Class cannot contain spaces. Please enter a valid Class.", "Student Admin System");
                continue;
            } 
            // Check if class code length is either 12 or 13 characters
            else if (classCode.length() == 12 || classCode.length() == 13) {
                // Validate the format of class code based on its length
                if (classCode.length() == 12) {
                    if (!(classCode.charAt(3) == '/' && classCode.charAt(6) == '/' && classCode.charAt(9) == '/')) {
                        StudentManagementView.displayErrorMessage("Please enter the class in valid format!", "Student Admin System");
                        continue;
                    }
                } else if (classCode.length() == 13) {
                    if (!(classCode.charAt(4) == '/' && classCode.charAt(7) == '/' && classCode.charAt(10) == '/')) {
                        StudentManagementView.displayErrorMessage("Please enter the class in valid format!", "Student Admin System");
                        continue;
                    }
                }
            } 
            else {
                StudentManagementView.displayErrorMessage("Invalid class code length.", "Student Admin System");
                continue;
            }

            // Split class code into an array based on '/'
            String[] classArray = classCode.split("/");

            // Check if all components of the class code are present and have valid lengths
            if (classArray.length != 4) {
                StudentManagementView.displayErrorMessage("Some information is missing! Please enter a valid input.", "Student Admin System");
            } else {
                // Check individual components for validity
                if (!StudentManagementModel.checkClassCodeLength(classArray)) {
                    continue;
                }
                // Validate the format of each component
                if (!StudentManagementModel.containsOnlyLetters(classArray[0]) || !StudentManagementModel.containsOnlyLetters(classArray[1]) || !StudentManagementModel.containsOnlyAlphanumeric(classArray[2]) || !StudentManagementModel.containsOnlyDigits(classArray[3])) {
                    StudentManagementView.displayErrorMessage("Please enter the class in valid format.", "Student Admin System");
                    continue;
                }
                // Check if the type of class is either "FT" or "PT"
                if (!(classArray[1].equals("FT") || classArray[1].equals("PT"))) {
                    StudentManagementView.displayErrorMessage("Invalid input in type of class.", "Student Admin System");
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

                // Check if number of modules is at least 1
                if (numberInt <= 0) {
                    StudentManagementView.displayErrorMessage("Student must take at least one module!", "Student Admin System");
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
                StudentManagementView.displayErrorMessage("Invalid input for number of modules.", "Student Admin System");
            }
        }
    }

    // Method to prompt user for module code input and validate it
    public static String getModuleCode(int i, String system, ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module code for a specific module
            String m = JOptionPane.showInputDialog(null,
                    "Enter module code for module " + i + ":",
                    system,
                    JOptionPane.QUESTION_MESSAGE);

            // Check if input is empty
            if (StudentManagementModel.isEmpty(m)) {
                StudentManagementView.displayErrorMessage("Input cannot be empty!", "Student Admin System");
                continue;
            }

            // Validate module code
            if (StudentManagementModel.validateModuleCode(m)) {
                continue;
            }else if(!StudentManagementModel.checkDuplicateModuleCode(modules, m)){
                continue;
            }
            return m; // Return valid module code
        }
    }
 
    // Method to prompt user for module name input and validate it
    public static String getModuleName(int i, String system,  ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module name for a specific module
            String m = JOptionPane.showInputDialog(null, 
                    "Enter module name for module " + i + ":",
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if input is empty
            if (StudentManagementModel.isEmpty(m)) {
                StudentManagementView.displayErrorMessage("Input cannot be empty!", "Student Admin System");
                continue;
            }

            // Check if module name contains only letters
            if (!StudentManagementModel.containsOnlyLetters(m)) {
                StudentManagementView.displayErrorMessage("Module name can only contain letters.", "Student Admin System");
                continue;
            }
            
            if(!StudentManagementModel.checkDuplicateModuleName(modules, m)){
                continue;
            }

            return m; 
        }
    }

    // Method to prompt user for credit unit input and validate it
    public static int getCreditUnit(int i, String system) {
        while (true) {
            try {
                // Prompt user for credit unit for a specific module
                String m = JOptionPane.showInputDialog(null,
                        "Enter credit unit for module " + i + ":",
                        system, 
                        JOptionPane.QUESTION_MESSAGE);

                // Check if input is empty
                if (StudentManagementModel.isEmpty(m)) {
                    StudentManagementView.displayErrorMessage("Input cannot be empty!", "Student Admin System");
                    continue;
                }

                int creditUnit = Integer.parseInt(m); 

                return creditUnit;
            } catch (NumberFormatException e) {
                StudentManagementView.displayErrorMessage("Invalid input for credit unit.", "Student Admin System");
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

                // Check if input is empty
                if (StudentManagementModel.isEmpty(m)) {
                    StudentManagementView.displayErrorMessage("Input cannot be empty!", "Student Admin System");
                    continue;
                }

                int mark = Integer.parseInt(m); 

                return mark; 
            } catch (NumberFormatException e) {
                StudentManagementView.displayErrorMessage("Invalid input for module mark.", "Student Admin System");
            }
        }
    }

//*****************************************************************************
   
//Method to delete or add new module
    
    //Method to prpmpt user admin number for adding new modules or deleting student
    public static void getAdminNoForUpdateOrDeleteBothInOne(String system, int select, ArrayList<Student> students) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number of student:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if admin number is empty
            if (StudentManagementModel.isEmpty(adminNo)) {
                StudentManagementView.displayErrorMessage("Admin Number cannot be empty. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if admin number contains spaces
            else if (adminNo.contains(" ")) {
                StudentManagementView.displayErrorMessage("Admin Number cannot contain spaces. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if admin number length is not equal to 8
            else if (adminNo.length() != 8) {
                StudentManagementView.displayErrorMessage("Invalid input. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if first character of admin number is not 'p' or a letter
            else if (adminNo.charAt(0) != 'p' || !Character.isLetter(adminNo.charAt(0))) {
                StudentManagementView.displayErrorMessage("Admin Number must start with a letter or 'p'. Please enter a valid Admin Number.", "Student Admin System");
            } 
            // Check if all characters after the first one are digits
            else if (!StudentManagementModel.containsOnlyDigits(adminNo.substring(1))) {
                StudentManagementView.displayErrorMessage("All characters after the first one must be numbers. Please enter a valid Admin Number.", "Student Admin System");
            }else if(StudentManagementModel.checkStudentExists(adminNo, students) != -1){
                //Check students exitst or not if exitst it will return index of studetns
                int index = StudentManagementModel.checkStudentExists(adminNo, students);
                switch(select){
                    case 2 : 
                        //Delete student
                        deleteExistingStudent(index, students);
                        break;
                    case 3 :
                        //Add new modules 
                        updateStudentModule(index, students.get(index).getModules(), students, system);
                        break;
                }
            }
        break;
        }
    }
    
    //Method to delete existing student
    public static void deleteExistingStudent(int index, ArrayList<Student> students){
        //Delete student using remove method declared in Student Class
        students.remove(index);
        StudentManagementView.displayFinishMessage("Student deleted!", "Student Enquiry System");
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
        StudentManagementView.displayFinishMessage("Module added successfully", "Student Enquiry System");
    }
    
   

//*****************************************************************************

    


}



