// import java.util.ArrayList;
import javax.swing.JOptionPane;
// import java.util.Arrays;

public class Main {
    
     // Method to get a validated and normalized student name from user input
    private static String getStdName() {
        while (true) {
            String name = JOptionPane.showInputDialog("Enter Name: ");
            
            // Check if the entered name is null or blank
            if (isEmpty(name)) {
                showErrorMessage("Name cannot be empty. Please enter a valid name.");
            } else if (!containsOnlyLetters(name)) {
                // Display error message if name contains numbers
                JOptionPane.showMessageDialog(null,
                        "Name cannot contain numbers. Please enter a valid name.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Return the normalized name if it passes validation
                return normalizeName(name);
            }
        }
    }


    // Method to normalize a name by removing extra whitespace
    private static String normalizeName(String name) {
        // Replace all consecutive whitespace characters with a single space
        String modifiedName = name.trim().replaceAll("\\s+", " ");
        String[] nameArray = modifiedName.split("");
        nameArray[0] = nameArray[0].toUpperCase();
        for(int i = 1;i<nameArray.length;i++){
            if(nameArray[i].equals(" ")){
                nameArray[i+1] = nameArray[i+1].toUpperCase();
            }
        }
        // Convert the array back to a string
        StringBuilder result = new StringBuilder();
        for (String letter : nameArray) {
            result.append(letter);
        }

        return result.toString();
    }
    
    // Method to get a validated admin number from user input
    private static String getAdminNo() {
        while (true) {
            String adminNo = JOptionPane.showInputDialog("Enter Admin Number: ");
            adminNo = adminNo.trim();
            
            // Check if the entered admin number is null or blank using the isEmptyOrBlank function 
            // Also check format(mustbe p*******) * indicates number)
            if (isEmpty(adminNo)) {
                showErrorMessage("Admin Number cannot be empty. Please enter a valid Admin Number.");
            }else if (adminNo.contains(" ")) {
                showErrorMessage("Admin Number cannot contain spaces. Please enter a valid Admin Number.");
            } else if(!(adminNo.length() == 8)){
                showErrorMessage("Invalid input. Please enter a valid Admin Number.");
            }else if (!(adminNo.charAt(0) == 'p') || !(Character.isLetter(adminNo.charAt(0)))) {
                showErrorMessage("Admin Number must start with a letter or 'p'. Please enter a valid Admin Number.");
            }else if (!containsOnlyDigits(adminNo.substring(1))) {
                showErrorMessage("All characters after the first one must be numbers. Please enter a valid Admin Number.");
            } else {
                return adminNo;
            }
        }
    }

    //Check only contian number
    private static boolean containsOnlyDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    
        // Method to check if a name contains any numbers
    private static boolean containsOnlyLetters(String name) {
        // Iterate through each character of the name
        for (char c : name.toCharArray()) {
            // Check if the character is not a letter or whitespace
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        // If no numbers are found, return true
        return true;
    }
    
    //Mthod to check for first must be number and sec must be alphabet
    private static boolean containsOnlyAlphanumeric(String adminNo) {
    
        // Check the first character
        char firstChar = adminNo.charAt(0);
        if (!Character.isDigit(firstChar)) {
            return false; // First character is not a digit
        }

        // Check the second character
        char secondChar = adminNo.charAt(1);
        if (!Character.isLetter(secondChar)) {
            return false; // Second character is not a letter
        }

        // All conditions are met
        return true;
    }
    
    //Check input is empty
    private static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }
    
        //Show error message
    private static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
        
    // Method to get a validated class from user input
    private static String getClassCode() {
        while (true) {
            String classCode = JOptionPane.showInputDialog("Enter Class: ");
            classCode = classCode.trim();

            if (isEmpty(classCode)) {
                showErrorMessage("Class cannot be empty. Please enter a valid Class.");
                continue;
            } else if (classCode.contains(" ")) {
                showErrorMessage("Class cannot contain spaces. Please enter a valid Class.");
                continue;
            } else if (classCode.length() == 12) {
            if (!(classCode.charAt(3) == '/' && classCode.charAt(6) == '/' && classCode.charAt(9) == '/')) {
                showErrorMessage("Please enter the class in valid format!");
                continue;
            }
            } else if (classCode.length() == 13) {
                if (!(classCode.charAt(4) == '/' && classCode.charAt(7) == '/' && classCode.charAt(10) == '/')) {
                    showErrorMessage("Please enter the class in valid format!");}
                continue;
            } else {
                String[] classArray = classCode.split("/");
                if (classArray.length != 4) {
                    showErrorMessage("Some information is missing! Please enter a valid input.");
                } else {
                    //Check input's length
                    if(!checkClassCodeLength(classArray)){
                        continue;
                    }
                    // Check if other parts contain only alphanumeric characters and digits respectively
                    if (!containsOnlyLetters(classArray[0]) || !containsOnlyLetters(classArray[1]) || !containsOnlyAlphanumeric(classArray[2]) || !containsOnlyDigits(classArray[3])) {
                        showErrorMessage("Please enter the class in valid format.");
                        continue; // Skip further checks if the input is invalid
                    }
                    // Check if the second part is "ft" or "pt"
                    if (!(classArray[1].equalsIgnoreCase("ft") || classArray[1].equalsIgnoreCase("pt"))) {
                        showErrorMessage("Invalid input in type of class.");
                        continue; // Skip further checks if the input is invalid
                    }
                }
            }
            // Return the class code if all validation checks pass
            return classCode;
        }
    }
    
    private static boolean checkClassCodeLength(String[] classArray) {
        // Check the length of the first part
        if (classArray[0].length() < 3 || classArray[0].length() > 4) {
            showErrorMessage("Invalid input in course of class.");
            return false; // Exit the function if the length is invalid
        }
        // Check the length of the second part
        if (classArray[1].length() != 2) {
            showErrorMessage("Invalid input in type of class.");
            return false;
        }
        // Check the length of the third part
        if (classArray[2].length() != 2) {
            showErrorMessage("Invalid input in stage/path of class.");
            return false;
        }
        // Check the length of the fourth part
        if (classArray[3].length() != 2) {
            showErrorMessage("Invalid input in class number.");
            return false;
        }
        return true;
    }    
    
    public static void main(String[] args) {
        //Booting up
        String stdName;
        String adminNo;
        String classCode;
        //To store student modules
        //First it will create modules objects and then store in arrayList
        // ArrayList<StudentModule> modules = new ArrayList<>();
        
        stdName = getStdName();
        adminNo = getAdminNo();
        classCode = getClassCode();
        System.out.println(stdName + "(" + adminNo + ")" + classCode);
    }
}
