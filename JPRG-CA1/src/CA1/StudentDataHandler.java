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
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentDataHandler {

    public static void createStudentsFromCSV(ArrayList<Student> students, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1); // Use -1 to keep empty trailing fields

                if (values.length < 7) {
                    // Handle incomplete line gracefully
                    continue;
                }

                String stdName = values[0].trim();
                String adminNo = values[1].trim();
                String classCode = values[2].trim();
                String moduleCode = values[3].trim();
                String moduleName = values[4].trim();
                int creditUnit = Integer.parseInt(values[5].trim());
                int mark = Integer.parseInt(values[6].trim());

                Module module = new Module(moduleCode, moduleName, creditUnit, mark);

                // Flag to track if we found an existing student with the same admin number
                boolean studentExists = false;

                // Check if student with the same admin number already exists
                for (Student existingStudent : students) {
                    if (existingStudent.getAdminNo().equals(adminNo)) {
                        studentExists = true;

                        // Check if the module with the same module code exists for this student
                        boolean moduleExists = false;
                        for (Module existingModule : existingStudent.getModules()) {
                            if (existingModule.getModuleCode().equals(moduleCode)) {
                                moduleExists = true;
                                break;
                            }
                        }

                        // If module with same code does not exist, add new module to this student
                        if (!moduleExists) {
                            existingStudent.getModules().add(module);
                        }

                        break; // Exit loop since we found the student
                    }
                }

                // If student with admin number does not exist, create new student and add to list
                if (!studentExists) {
                    ArrayList<Module> modules = new ArrayList<>();
                    modules.add(module);
                    Student student = new Student(stdName, adminNo, classCode, modules);
                    students.add(student);;
                }
            }
            StudentManagementView.displayStudentsAddedCSVMessage();
        } catch (IOException e) {
            e.printStackTrace();
            // Display error message to user or handle it appropriately
            StudentManagementView.displayErrorGenerateCSV();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}