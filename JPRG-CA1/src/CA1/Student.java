
/**
 *
 * @author shinn
 */
import java.util.ArrayList;

public class Student {
    private String stdName;
    private String adminNo;
    private String classCode;
    private ArrayList<Module> modules;

    public Student(String stdName, String adminNo, String classCode, ArrayList<Module> modules) {
        this.stdName = stdName;
        this.adminNo = adminNo;
        this.classCode = classCode;
        this.modules = modules;
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
    

    // New method for report generation
    public String getPerformanceSummary() {
        return String.format("Name: %s, AdminNo: %s, Class: %s, GPA: %.2f", stdName, adminNo, classCode, calculateGPA());
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
