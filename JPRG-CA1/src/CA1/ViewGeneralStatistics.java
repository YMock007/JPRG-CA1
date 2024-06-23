/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yeyin
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// import com.itextpdf.text.Document;
// import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.Paragraph;
// import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class ViewGeneralStatistics {

    // public static void generateCSVReport(ArrayList<Student> students, String filePath) {
    //     try (FileWriter writer = new FileWriter(filePath)) {
    //         writer.append("Name,AdminNo,Class,GPA\n");
    //         for (Student student : students) {
    //             writer.append(student.getStdName()).append(",");
    //             writer.append(student.getAdminNo()).append(",");
    //             writer.append(student.getClassCode()).append(",");
    //             writer.append(String.valueOf(student.calculateGPA())).append("\n");
    //         }
    //         System.out.println("CSV report generated successfully.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void generatePDFReport(ArrayList<Student> students, String filePath) {
    //     Document document = new Document();
    //     try {
    //         PdfWriter.getInstance(document, new FileOutputStream(filePath));
    //         document.open();
    //         for (Student student : students) {
    //             document.add(new Paragraph(student.getPerformanceSummary()));
    //             document.add(new Paragraph("\n"));
    //         }
    //         System.out.println("PDF report generated successfully.");
    //     } catch (DocumentException | IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         document.close();
    //     }
    // }

    public static void generatePerformanceSummary(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println(student.getPerformanceSummary());
        }
    }

    public static ArrayList<Student> getTopPerformingStudents(ArrayList<Student> students, int topN) {
        students.sort((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()));
        return new ArrayList<>(students.subList(0, Math.min(topN, students.size())));
    }

    public static ArrayList<Student> getStudentsNeedingImprovement(ArrayList<Student> students, double threshold) {
        ArrayList<Student> needingImprovement = new ArrayList<>();
        for (Student student : students) {
            if (student.calculateGPA() < threshold) {
                needingImprovement.add(student);
            }
        }
        return needingImprovement;
    }
}
