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
import java.util.Random;

public class SimulateStudents {

    // Generate a random name consisting of letters only
    public static String generateRandomName(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        StringBuilder name = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            name.append(randomChar);
        }

        return name.toString();
    }

    public static void addStudents(ArrayList<Student> students) {
        int totalStudents = 300;
        int totalClasses = 15;
        String baseClassCode = "DIT/FT/1B/";
        Random rand = new Random();

        // Create class codes list
        ArrayList<String> classCodes = new ArrayList<>();
        for (int i = 1; i <= totalClasses; i++) {
            classCodes.add(baseClassCode + String.format("%02d", i));
        }

        // Add students
        for (int i = 0; i < totalStudents; i++) {
            ArrayList<Module> moduleList = new ArrayList<>();
            int numberOfModules = 2;

            for (int j = 0; j < numberOfModules; j++) {
                int unit = rand.nextInt(4) + 4;  // Random between 4 and 7
                int mark = rand.nextInt(31) + 60; // Random between 60 and 90
                int r = rand.nextInt(4) + 1;
                switch (r) {
                    case 1 -> moduleList.add(new Module("ST0509", "JPRG", unit, mark));
                    case 2 -> moduleList.add(new Module("ST0503", "FOP", unit, mark));
                    case 3 -> moduleList.add(new Module("ST0525", "DBS", unit, mark));
                    case 4 -> moduleList.add(new Module("ST0506", "SEP", unit, mark));
                }
            }

            // Randomly assign a class code
            String classCode = classCodes.get(rand.nextInt(totalClasses));
            String name = StudentManagementController.setName(generateRandomName(6));  // Generate a 6-letter random name
            String id = "p2340" + String.format("%03d", i);

            students.add(new Student(name, id, classCode, moduleList));
        }
    }
}
