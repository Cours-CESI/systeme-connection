package fr.tetemh.managers;

import fr.tetemh.cclass.Student;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class StudentManager {
    private HashMap<String, Student> students;

    public StudentManager() {
        this.students = new HashMap<>();
    }

    public Student createStudent(String name, int age, String password) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        student.setPassword(password);
        this.getStudents().put(name, student);
        return student;
    }
}
