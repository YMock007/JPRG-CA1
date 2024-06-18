
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeyin
 */
public class StudentUser {
    
    public static void main(String[] args) {
        // ------------------------------------------------------
        // Configs / Booting Up
        // ------------------------------------------------------
        int system;
        boolean quit = false;
        ArrayList<Student> students = new ArrayList<>(); // Creating a list to store students
        
        // ------------------------------------------------------
        //Creating some students in database
        // ------------------------------------------------------
        
        SimulateStudents.addStudents(students);
        for(Student student : students){
                System.out.println(student.toString());
            }
        
        while(!quit) {
            system = StudentManagementController.getSystem();
            switch (system) {
                case 1 -> {
                    StudentManagementController.showAdminSystem(students);
                }
                case 2 -> {
                    StudentManagementController.showUserSystem(students);
                }
                case 3 -> {
                    StudentManagementView.showTerminateMessage();
                    quit = true;                   
                }
            }   
        }
    }
}
