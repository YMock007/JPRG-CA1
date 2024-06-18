
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
            
            System.out.println(numerator);
            System.out.println(denominator);
        }

        double gpa = numerator / denominator;
        return Double.parseDouble(String.format("%.2f", gpa));
    }

    @Override
    public String toString() {
        return stdName + "/n" + adminNo + "\n" + classCode + "\n" + modules;
    }
}
