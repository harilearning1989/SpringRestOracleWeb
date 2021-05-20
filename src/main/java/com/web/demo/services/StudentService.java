package com.web.demo.services;

import com.web.demo.dto.CourseDTO;
import com.web.demo.dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getStudents();

    StudentDTO saveStudent(StudentDTO student);

    StudentDTO updateStudent(StudentDTO student);

    String deleteStudent(String studentId);

    List<StudentDTO> retrieveAllStudents();

    StudentDTO retrieveStudent(int studentId);

    List<CourseDTO> retrieveCourses(int studentId);

    CourseDTO retrieveCourse(int studentId, String courseId);

    CourseDTO addCourse(int studentId, CourseDTO course);
}
