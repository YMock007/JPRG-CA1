/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author shinn
 */
import java.util.ArrayList;

public class Student {
    private String stdName;
    private String adminNo;
    private String classCode;
    private ArrayList<StudentModule> modules;

    public Student(String stdName, String adminNo, String classCode, ArrayList<StudentModule> modules) {
        this.stdName = stdName;
        this.adminNo = adminNo;
        this.classCode = classCode;
        this.modules = modules;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdnName) {
        this.stdName = stdnName;
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

    public ArrayList<StudentModule> getModules() {
        return modules;
    }

    public void setModules(ArrayList<StudentModule> modules) {
        this.modules = modules;
    }

    public void addModule(StudentModule module) {
        this.modules.add(module);
    }

    public void removeModule(StudentModule module) {
        this.modules.remove(module);
    }
}
