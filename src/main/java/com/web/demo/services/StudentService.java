package com.web.demo.services;

import com.web.demo.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getStudents();

    StudentDTO saveStudent(StudentDTO student);

    StudentDTO updateStudent(StudentDTO student);

    String deleteStudent(String studentId);
}
