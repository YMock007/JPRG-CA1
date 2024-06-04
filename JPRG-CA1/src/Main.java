import java.util.ArrayList;
import javax.swing.JOptionPane;
// import java.util.Arrays;

public class Main {
    
    private static int getSelect(String system) {
        // Initialize variables to store user input
        String userSelectStr;
        int userSelectInt;

        // Define admin options menu
        String adminOptions = "1. Add new student \n2. Delete student \n3. Add new module for student \n4. Quit";

        // Loop until valid input is received
        while (true) {
            try {
                userSelectStr = JOptionPane.showInputDialog(null,
                        adminOptions,
                        system,
                        JOptionPane.QUESTION_MESSAGE
                );

                // Check if user input is empty
                if (isEmpty(userSelectStr)) {
                    showErrorMessage("Input cannot be empty!");
                    continue;
                }

                // Parse user input to integer
                userSelectInt = Integer.parseInt(userSelectStr);

                return userSelectInt;
            } catch (NumberFormatException e) {
                // Catch exception if input cannot be parsed to integer
                // Show error message and continue loop
                showErrorMessage("Please enter a valid input!");
            }
        }
    }

    
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

    // Check if a string contains only digits
    private static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Check if a string contains only letters and whitespace characters
    private static boolean containsOnlyLetters(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    // Check if first character is a digit and second character is a letter
    private static boolean containsOnlyAlphanumeric(String adminNo) {
        return !(!Character.isDigit(adminNo.charAt(0)) || !Character.isLetter(adminNo.charAt(1)));
    }

    // Check if a string is empty or contains only whitespace characters
    private static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }


    // Method to display an error message dialog box
    private static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, 
                message, 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
    }

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

    // Method to check the lengths of components of the class code
    private static boolean checkClassCodeLength(String[] classArray) {
        // Check the length of each component and show error messages if invalid
        if (classArray[0].length() < 3 || classArray[0].length() > 4) {
            showErrorMessage("Invalid input in course of class.");
            return false;
        }
        if (classArray[1].length() != 2) {
            showErrorMessage("Invalid input in type of class.");
            return false;
        }
        if (classArray[2].length() != 2) {
            showErrorMessage("Invalid input in stage/path of class.");
            return false;
        }
        if (classArray[3].length() != 2) {
            showErrorMessage("Invalid input in class number.");
            return false;
        }
        return true;
    }


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
                    String moduleCode = getModuleCode(i, system); // Get module code
                    String moduleName = getModuleName(i, system); // Get module name
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
    private static String getModuleCode(int i, String system) {
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
            }

            return m; // Return valid module code
        }
    }

    // Method to validate module code
    private static boolean validateModuleCode(String m) {
        // Convert module code to character array
        char[] mArray = m.toCharArray();

        // Check if module code is 6 characters long
        if (mArray.length != 6) {
            showErrorMessage("Module code must be 6 characters long.");
            return false;
        } 
        // Check if first 2 characters are alphabets
        else if (!Character.isLetter(mArray[0]) || !Character.isLetter(mArray[1])) {
            showErrorMessage("First 2 characters must be alphabets in module code.");
            return false;
        }

        // Check if last 4 characters are digits
        for (int i = 2; i < mArray.length; i++) {
            if (!Character.isDigit(mArray[i])) {
                showErrorMessage("Last 4 characters must be digits in module code.");
                return false;
            }
        }

        return true;
    }

    // Method to prompt user for module name input and validate it
    private static String getModuleName(int i, String system) {
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


    public static void main(String[] args) {
        final String system = "Student Admin System";// Setting the system name
        int select;// Declaring variable to store user's selection
        
        // Prompting user to select an option from the menu and storing the selection
        select = getSelect(system);
        
        switch(select){
            case 1 -> {
                String stdName;
                String adminNo;
                String classCode; //naming variabel as "class" will lead to error, it is reserved keyword
                ArrayList<Module> modules = new ArrayList<>();// Creating a list to store modules
                ArrayList<Student> students = new ArrayList<>(); // Creating a list to store students
                
                // Prompting user to input student name, admin number, class, and modules
                stdName = getStdName(system);
                adminNo = getAdminNo(system);
                classCode = getClassCode(system);
                getModule(modules, system);
                
                // Creating a student object with the input data
                Student student = new Student(stdName, adminNo, classCode, modules);
                students.add(student);// Adding the student to the list of students
                System.out.println(student.toString());
            }
        }
        
        
    }
}