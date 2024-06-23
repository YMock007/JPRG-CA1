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

public class Student {
    private String stdName;
    private String adminNo;
    private String classCode;
    private ArrayList<Module> modules;

    public Student(String stdName, String adminNo, String classCode, ArrayList<Module> modules2) {
        this.stdName = stdName;
        this.adminNo = adminNo;
        this.classCode = classCode;
        this.modules = modules2;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }


    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }
    
    public double calculateGPA() {
        double numerator = 0.0;
        double denominator = 0.0;
        int gradePoint;
        for (Module module : this.modules) {
            gradePoint = StudentManagementModel.getGradePoint(module.getStudentMark());

            numerator += (module.getCreditUnit() * gradePoint);
            denominator += module.getCreditUnit();
        }

        double gpa = numerator / denominator;
        return Double.parseDouble(String.format("%.2f", gpa));
    }
    
    
    @Override
    public String toString() {
        String string = "Name: " + this.stdName +
                        "\nAdmin: " + this.adminNo + 
                        "\nClass: " + this.classCode + 
                        "\nModule taken:\n";
        for(int i = 0; i < this.modules.size(); i++) {
            string += (i+1) + ". " + this.modules.get(i) + "\n";
        }
        
        string += "\nGPA: " + this.calculateGPA() + "\n----------------------";
        return string;
    }
}
