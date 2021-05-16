package com.web.demo.services;

import com.web.demo.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    public List<StudentDTO> getStudents() {
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student = new StudentDTO();
        students.add(student);
        return students;
    }

    public StudentDTO saveStudent(StudentDTO student) {
        student.setStudentId(1);
        student.setStudentName("Arun");
        student.setCategory("A");
        student.setGender("Male");
        student.setMobile("9494968081");
        student.setFatherName("Ramakrishna");
        return student;
    }

    public StudentDTO updateStudent(StudentDTO student) {
        student.setStudentId(2);
        student.setStudentName("John");
        student.setCategory("A");
        student.setGender("Male");
        student.setMobile("9494968081");
        student.setFatherName("Ramakrishna");
        return student;
    }

    public String deleteStudent(String studentId) {
        return "StudentDTO is deleted";
    }
}
