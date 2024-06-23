/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * 
 * @author Ye Yint Aung
 * @class DIT/FT/2B/23
 * @admission_number p2340656
 * @system Enquiry System
 * 
 */

 import java.io.File;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 
 public class ViewGeneralStatistics {
 
     public static void generateCSVReport(ArrayList<Student> students, String filePath) {
        File file = new File(filePath);
        int counter = 1;

        // Check if the file already exists
        while (file.exists()) {
            // If it exists, generate a new file path
            String newFilePath = generateNewFilePath(filePath, counter);
            file = new File(newFilePath);
            counter++;
        }
         try (FileWriter writer = new FileWriter(file)) {
             writer.append("Name,AdminNo,Class,GPA,ModuleCode,ModuleName,CreditUnit,StudentMark\n");
             for (Student student : students) {
                 writer.append(student.getStdName()).append(",");
                 writer.append(student.getAdminNo()).append(",");
                 writer.append(student.getClassCode()).append(",");
                 writer.append(String.valueOf(student.calculateGPA())).append(",");
                 for(Module module : student.getModules()) {
                    writer.append(module.getModuleCode()).append(",");
                    writer.append(module.getModuleName()).append(",");
                    writer.append(String.valueOf(module.getCreditUnit())).append(",");
                    writer.append(String.valueOf(module.getStudentMark())).append("\n,,,,");
                 }
                 writer.append("\n");
            }
            StudentManagementView.displaySuccessGenerateCSV();
         } catch (IOException e) {
            StudentManagementView.displayErrorGenerateCSV();
         }
     }
 
     public static void generatePlainTextReport(ArrayList<Student> students, String filePath) {
        File file = new File(filePath);
        int counter = 1;

        // Check if the file already exists
        while (file.exists()) {
            // If it exists, generate a new file path
            String newFilePath = generateNewFilePath(filePath, counter);
            file = new File(newFilePath);
            counter++;
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Student student : students) {
                writer.append(student.toString()).append("\n");
            }
            StudentManagementView.displaySuccessGenerateTXT();
        } catch (IOException e) {
            StudentManagementView.displayErrorGenerateTXT();
        }
        
        try (FileWriter writer = new FileWriter(filePath)) {
             for (Student student : students) {
                 writer.append(student.toString());
             }
             StudentManagementView.displaySuccessGenerateTXT();
         } catch (IOException e) {
            StudentManagementView.displayErrorGenerateTXT();
         }
     }
 

    public static ArrayList<Student> getTopPerformingStudents(ArrayList<Student> students, int topN) {
        students.sort((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()));
        return new ArrayList<>(students.subList(0, Math.min(topN, students.size())));
    }

    public static ArrayList<Student>getStudentsNeedingImprovement(ArrayList<Student> students, double threshold) {
        ArrayList<Student> needingImprovement = new ArrayList<>();
        for (Student student : students) {
            if (student.calculateGPA() < threshold) {
                needingImprovement.add(student);
            }
        }
        return needingImprovement;
    }

    private static String generateNewFilePath(String originalFilePath, int counter) {
        // Generate a new file path by appending a counter before the file extension
        int lastIndex = originalFilePath.lastIndexOf('.');
        String newFilePath;
        if (lastIndex != -1) {
            newFilePath = originalFilePath.substring(0, lastIndex) + "_" + counter + originalFilePath.substring(lastIndex);
        } else {
            newFilePath = originalFilePath + "_" + counter; // Fallback if no extension found
        }
        return newFilePath;
    }

}
