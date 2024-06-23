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

public class Module {
    String moduleCode;
    String moduleName;
    int creditUnit;
    int studentMark;

    public Module(String moduleCode, String moduleName, int creditUnit, int studentMark) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.creditUnit = creditUnit;
        this.studentMark = studentMark;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getCreditUnit() {
        return creditUnit;
    }

    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }

    public int getStudentMark() {
        return studentMark;
    }

    public void setStudentMark(int studentMark) {
        this.studentMark = studentMark;
    }
    
        public String calculateGrade() {
        if(this.studentMark >= 80) {
            return "A";
        } else if(this.studentMark >= 70) {
            return "B";
        } else if(this.studentMark >=60) {
            return "C";
        } else if(this.studentMark >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
        
    @Override
    public String toString() {
        return this.getModuleCode() + "/" + this.moduleName + "/" + this.creditUnit + ": " + this.calculateGrade();
    }
}


