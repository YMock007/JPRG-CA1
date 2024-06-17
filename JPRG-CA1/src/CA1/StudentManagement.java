
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
    
    private static void createNewStudent(ArrayList<Student> students, String system){
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
                showFinishMessage("New student added successfully!", "Student Enquiry System");
    }
   
//*****************************************************************************
    
//Method to prompt student name
    
    private static String getStdName(String system) {
        // Loop until a valid name is entered
        while (true) {
            // Prompt user to enter name 
            String name = JOptionPane.showInputDialog(null, 
                    "Enter Name:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if name is empty
            if (isEmpty(name)) {
                showErrorMessage("Name cannot be empty. Please enter a valid name.");
            } 
            // Check if name contains only letters
            else if (!containsOnlyLetters(name)) {
                showErrorMessage("Name cannot contain numbers. Please enter a valid name.");
            } 
            else {
                // If name is valid, format it and return
                return setName(name);
            }
        }
    }

    private static String setName(String name) {
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
    
    private static String getAdminNo(String system) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if admin number is empty
            if (isEmpty(adminNo)) {
                showErrorMessage("Admin Number cannot be empty. Please enter a valid Admin Number.");
            } 
            // Check if admin number contains spaces
            else if (adminNo.contains(" ")) {
                showErrorMessage("Admin Number cannot contain spaces. Please enter a valid Admin Number.");
            } 
            // Check if admin number length is not equal to 8
            else if (adminNo.length() != 8) {
                showErrorMessage("Invalid input. Please enter a valid Admin Number.");
            } 
            // Check if first character of admin number is not 'p' or a letter
            else if (adminNo.charAt(0) != 'p' || !Character.isLetter(adminNo.charAt(0))) {
                showErrorMessage("Admin Number must start with a letter or 'p'. Please enter a valid Admin Number.");
            } 
            // Check if all characters after the first one are digits
            else if (!containsOnlyDigits(adminNo.substring(1))) {
                showErrorMessage("All characters after the first one must be numbers. Please enter a valid Admin Number.");
            } 
            else {
                return adminNo;
            }
        }
    }
    
//*****************************************************************************
   
//Method to prompt student's Class

    // Method to prompt the user to enter a class code and validate the input
    private static String getClassCode(String system) {
        while (true) {
            String classCode = JOptionPane.showInputDialog(null,
                    "Enter Class:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if class code is empty
            if (isEmpty(classCode)) {
                showErrorMessage("Class cannot be empty. Please enter a valid Class.");
                continue;
            } 
            // Check if class code contains spaces
            else if (classCode.contains(" ")) {
                showErrorMessage("Class cannot contain spaces. Please enter a valid Class.");
                continue;
            } 
            // Check if class code length is either 12 or 13 characters
            else if (classCode.length() == 12 || classCode.length() == 13) {
                // Validate the format of class code based on its length
                if (classCode.length() == 12) {
                    if (!(classCode.charAt(3) == '/' && classCode.charAt(6) == '/' && classCode.charAt(9) == '/')) {
                        showErrorMessage("Please enter the class in valid format!");
                        continue;
                    }
                } else if (classCode.length() == 13) {
                    if (!(classCode.charAt(4) == '/' && classCode.charAt(7) == '/' && classCode.charAt(10) == '/')) {
                        showErrorMessage("Please enter the class in valid format!");
                        continue;
                    }
                }
            } 
            else {
                showErrorMessage("Invalid class code length.");
                continue;
            }

            // Split class code into an array based on '/'
            String[] classArray = classCode.split("/");

            // Check if all components of the class code are present and have valid lengths
            if (classArray.length != 4) {
                showErrorMessage("Some information is missing! Please enter a valid input.");
            } else {
                // Check individual components for validity
                if (!checkClassCodeLength(classArray)) {
                    continue;
                }
                // Validate the format of each component
                if (!containsOnlyLetters(classArray[0]) || !containsOnlyLetters(classArray[1]) || !containsOnlyAlphanumeric(classArray[2]) || !containsOnlyDigits(classArray[3])) {
                    showErrorMessage("Please enter the class in valid format.");
                    continue;
                }
                // Check if the type of class is either "FT" or "PT"
                if (!(classArray[1].equals("FT") || classArray[1].equals("PT"))) {
                    showErrorMessage("Invalid input in type of class.");
                    continue;
                }
            }
            return classCode;
        }
    }
    
//*****************************************************************************
   
//Method to get student's modules
    
    // Method to prompt the user to input module information and validate it
    private static void getModule(ArrayList<Module> modules, String system) {
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
                    showErrorMessage("Student must take at least one module!");
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
                showErrorMessage("Invalid input for number of modules.");
            }
        }
    }

    // Method to prompt user for module code input and validate it
    private static String getModuleCode(int i, String system, ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module code for a specific module
            String m = JOptionPane.showInputDialog(null,
                    "Enter module code for module " + i + ":",
                    system,
                    JOptionPane.QUESTION_MESSAGE);

            // Check if input is empty
            if (isEmpty(m)) {
                showErrorMessage("Input cannot be empty!");
                continue;
            }

            // Validate module code
            if (!validateModuleCode(m)) {
                continue;
            }else if(!checkDuplicateModuleCode(modules, m)){
                continue;
            }
            return m; // Return valid module code
        }
    }
 
    // Method to prompt user for module name input and validate it
    private static String getModuleName(int i, String system,  ArrayList<Module> modules) {
        while (true) {
            // Prompt user for module name for a specific module
            String m = JOptionPane.showInputDialog(null, 
                    "Enter module name for module " + i + ":",
                    system, 
                    JOptionPane.QUESTION_MESSAGE);

            // Check if input is empty
            if (isEmpty(m)) {
                showErrorMessage("Input cannot be empty!");
                continue;
            }

            // Check if module name contains only letters
            if (!containsOnlyLetters(m)) {
                showErrorMessage("Module name can only contain letters.");
                continue;
            }
            
            if(!checkDuplicateModuleName(modules, m)){
                continue;
            }

            return m; 
        }
    }

    // Method to prompt user for credit unit input and validate it
    private static int getCreditUnit(int i, String system) {
        while (true) {
            try {
                // Prompt user for credit unit for a specific module
                String m = JOptionPane.showInputDialog(null,
                        "Enter credit unit for module " + i + ":",
                        system, 
                        JOptionPane.QUESTION_MESSAGE);

                // Check if input is empty
                if (isEmpty(m)) {
                    showErrorMessage("Input cannot be empty!");
                    continue;
                }

                int creditUnit = Integer.parseInt(m); 

                return creditUnit;
            } catch (NumberFormatException e) {
                showErrorMessage("Invalid input for credit unit.");
            }
        }
    }

    // Method to prompt user for module mark input and validate it
    private static int getMark(int i, String system) {
        while (true) {
            try {
                // Prompt user for module mark for a specific module
                String m = JOptionPane.showInputDialog(null, 
                        "Enter module mark for module " + i + ":",
                        system, 
                        JOptionPane.QUESTION_MESSAGE);

                // Check if input is empty
                if (isEmpty(m)) {
                    showErrorMessage("Input cannot be empty!");
                    continue;
                }

                int mark = Integer.parseInt(m); 

                return mark; 
            } catch (NumberFormatException e) {
                showErrorMessage("Invalid input for module mark.");
            }
        }
    }

//*****************************************************************************
   
//Method to delete or add new module
    
    //Method to prpmpt user admin number for adding new modules or deleting student
    private static void getAdminNoForUpdateOrDeleteBothInOne(String system, int select, ArrayList<Student> students) {
        while (true) {
            // Prompt user to enter admin number 
            String adminNo = JOptionPane.showInputDialog(null,
                    "Enter Admin Number of student:", 
                    system, 
                    JOptionPane.QUESTION_MESSAGE).trim();

            // Check if admin number is empty
            if (isEmpty(adminNo)) {
                showErrorMessage("Admin Number cannot be empty. Please enter a valid Admin Number.");
            } 
            // Check if admin number contains spaces
            else if (adminNo.contains(" ")) {
                showErrorMessage("Admin Number cannot contain spaces. Please enter a valid Admin Number.");
            } 
            // Check if admin number length is not equal to 8
            else if (adminNo.length() != 8) {
                showErrorMessage("Invalid input. Please enter a valid Admin Number.");
            } 
            // Check if first character of admin number is not 'p' or a letter
            else if (adminNo.charAt(0) != 'p' || !Character.isLetter(adminNo.charAt(0))) {
                showErrorMessage("Admin Number must start with a letter or 'p'. Please enter a valid Admin Number.");
            } 
            // Check if all characters after the first one are digits
            else if (!containsOnlyDigits(adminNo.substring(1))) {
                showErrorMessage("All characters after the first one must be numbers. Please enter a valid Admin Number.");
            }else if(checkStudentExists(adminNo, students) != -1){
                //Check students exitst or not if exitst it will return index of studetns
                int index = checkStudentExists(adminNo, students);
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
    private static void deleteExistingStudent(int index, ArrayList<Student> students){
        //Delete student using remove method declared in Student Class
        students.remove(index);
        showFinishMessage("Student deleted!", "Student Enquiry System");
    }
    
    // Method to prompt the user to input module information and validate it
    private static void updateStudentModule(int index, ArrayList<Module> modules, ArrayList<Student> students, String system) {
        //Get number of modules of specific student
        int i = students.get(index).getModules().size() + 1;
        String moduleCode = getModuleCode(i, system, modules); // Get module code
        String moduleName = getModuleName(i, system, modules); // Get module name
        int creditUnit = getCreditUnit(i, system); // Get credit unit
        int studentMark = getMark(i, system); // Get student mark
        Module module = new Module(moduleCode, moduleName, creditUnit, studentMark); // Create module object
        //Add new module to modules of specific student using index and addModule method declared in Student Class
        students.get(index).addModule(module);
        showFinishMessage("Module added successfully", "Student Enquiry System");
    }
    
    //adding students //Later might delete
    public static void addStudents(ArrayList s){
        int n = 6;
        Random rand = new Random();
        for(int i = 0;i<n;i++){
            ArrayList<Module> moduleList = new ArrayList<>();
            int numberOfModules = 1;
            for(int j = 0; j < numberOfModules;j++){
                int unit = (int) rand.nextInt(4, 7);
                int mark = (int) (rand.nextInt(60, 90));
                int r = (int) rand.nextInt(1, 5);
                switch(r){
                    case 1 -> moduleList.add(new Module("ST0509", "JPRG", unit, mark));
                    case 2 -> moduleList.add(new Module("ST0503", "FOP", unit, mark));
                    case 3 -> moduleList.add(new Module("ST0525", "DBS", unit, mark));
                    case 4 -> moduleList.add(new Module("ST0506", "SEP", unit, mark));       
                }  
            }
            switch(i){
                case 0 -> s.add(new Student("Shinn", "p2340700", "DIT/FT/1B/11", moduleList));
                case 1 -> s.add(new Student("Alice", "p2340701", "DIT/FT/1B/12", moduleList));
                case 2 -> s.add(new Student("Bob", "p2340702", "DIT/FT/1B/13", moduleList));
                case 3 -> s.add(new Student("Carol", "p2340703", "DIT/FT/1B/14", moduleList));
                case 4 -> s.add(new Student("David", "p2340704", "DIT/FT/1B/15", moduleList));
                case 5 -> s.add(new Student("Eve", "p2340705", "DIT/FT/1B/16", moduleList));
            }
        }   
        
    }

//*****************************************************************************
    
    public static void showAdminSystem(ArrayList<Student> students) {
        boolean quit = false;
        final String system = "Student Admin System";// Setting the system name
        int select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = StudentManagementController.getSelect(system);
        switch(select){
            case 1 -> {
                createNewStudent(students, system);
            }
            
            case 2->{
                getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
            }
            
            case 3->{
                getAdminNoForUpdateOrDeleteBothInOne(system, select, students);
            }
            
            case 4->{
                JOptionPane.showMessageDialog(null,
                        "Program terminated. \nThank you!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                quit = true;
            }
        }
        }
    }
    
    
    //--------------------------------------------------------------------------
    // User System starts from here
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    // Selecting System
    //--------------------------------------------------------------------------
    public static int getSystem() {
        String system;
        int systemInt;
        // Define system options menu
        String systemOptions = "1. Admin System \n2. User system";
        
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
                    showFinishMessage("Program terminated. Thank you!", "Seleting System");
                    System.exit(0);
                }
                
                // Check if user input is empty
                if (isEmpty(system)) {
                    showErrorMessage("Input cannot be empty!");
                    continue;
                }

                // Parse user input to integer
                systemInt = Integer.parseInt(system);

                return systemInt;
            } catch (NumberFormatException e) {
                // Catch exception if input cannot be parsed to integer
                // Show error message and continue loop
                showErrorMessage("Please enter a valid input!");
            }
        }       
    }
    
    
    //--------------------------------------------------------------------------
    // User system option
    //--------------------------------------------------------------------------
    public static void showUserSystem(ArrayList<Student> students) {
        boolean quit = false;
        final String system = "Student Enquiry System";// Setting the system name
        int select;// Declaring variable to store user's selection
        // Prompting user to select an option from the menu and storing the selection
        while(!quit){
            select = getSelect(system);
        switch(select){
            case 1 -> {
                displayAllStudent(students);
            }
            
            case 2->{
                searchStudentByClass(students);
            }
            
            case 3->{
//                searchStudentByName(system, select, students);
            }
            
            case 4->{
                JOptionPane.showMessageDialog(null,
                        "Program terminated. \nThank you!",
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);
                quit = true;
            }
        }
        }
    }
    
    
    //--------------------------------------------------------------------------
    // Displaying All Student report 
    //--------------------------------------------------------------------------
    public static void displayAllStudent(ArrayList<Student> students) {
        
        int studentIndex = 1;
        int moduleIndex;
        String output = "";
        for (Student student : students) {
        moduleIndex = 1;
        output += "Student " + studentIndex++ + ":\n" +
                  "Name: " + student.getStdName() + "\n" + 
                  "Admin: " + student.getAdminNo() + "\n" +
                  "Class: " + student.getClassCode() + "\n";
        
        for(Module module: student.getModules()) {
            output += "Modules Taken\n" + 
                      moduleIndex++ + "." + module.getModuleCode() + "/" +
                      module.getModuleName() + "/" + + module.getCreditUnit() +
                      ": " + module.getStudentMark() +
                      "\n----------------------------\n";
        }            
    }

        JTextArea textArea = new JTextArea(30, 20); // Rows x Columns (increased columns for wider text)
        textArea.setText(output.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JOptionPane.showMessageDialog(null,
                scrollPane,
                "All Student Report",
                JOptionPane.INFORMATION_MESSAGE);
    }

    //--------------------------------------------------------------------------
    // Search student by Class 
    //--------------------------------------------------------------------------
    public static void searchStudentByClass(ArrayList<Student> students) {
        String classCode = getClassCode();
    }
    
    public static String getClassCode() {
        String userInput = "";
        String question = "Enter the class name to search";
        
        while(true) {
            userInput = JOptionPane.showInputDialog(null,
                        question,
                        "Search",
                        JOptionPane.QUESTION_MESSAGE;
            
        }
        
        return validatingClassCode(userInput) == false ? null : userInput;
    }
    
    public static boolean validatingClassCode(String userInput) {
        // Regular expression patterns for each part of the class code
        String coursePattern = "[A-Z]+"; // One or more uppercase letters
        String ftPtPattern = "FT|PT";
        String yearPattern = "[A-Z]\\d"; // One uppercase letter followed by one digit
        String classPattern = "\\d{2}"; // Two digits

        // Combined regular expression for the whole class code
        String classCodePattern = coursePattern + "/" + ftPtPattern + "/" + yearPattern + "/" + classPattern;

        // Compile the pattern
        Pattern pattern = Pattern.compile("^" + classCodePattern + "$");

        // Match the given class code against the pattern
        return pattern.matcher(userInput).matches();
    }
}



