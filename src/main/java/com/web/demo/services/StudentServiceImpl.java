package com.web.demo.services;

import com.web.demo.dto.CourseDTO;
import com.web.demo.dtos.StudentDTO;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public List<StudentDTO> getStudents() {
        List<StudentDTO> students = new ArrayList<>();
        StudentDTO student = new StudentDTO();
        students.add(student);
        return students;
    }
    @Override
    public StudentDTO saveStudent(StudentDTO student) {
        student.setStudentId(1);
        student.setStudentName("Arun");
        student.setCategory("A");
        student.setGender("Male");
        student.setMobile("9494968081");
        student.setFatherName("Ramakrishna");
        return student;
    }
    @Override
    public StudentDTO updateStudent(StudentDTO student) {
        student.setStudentId(2);
        student.setStudentName("John");
        student.setCategory("A");
        student.setGender("Male");
        student.setMobile("9494968081");
        student.setFatherName("Ramakrishna");
        return student;
    }
    @Override
    public String deleteStudent(String studentId) {
        return "StudentDTO is deleted";
    }

    private static List<StudentDTO> students = new ArrayList<>();

    static {
        //Initialize Data
        CourseDTO course1 = new CourseDTO("Course1", "Spring", "10Steps", Arrays
                .asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));
        CourseDTO course2 = new CourseDTO("Course2", "Spring MVC", "10 Examples",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));
        CourseDTO course3 = new CourseDTO("Course3", "Spring Boot", "6K Students",
                Arrays.asList("Learn Maven", "Learn Spring",
                        "Learn Spring MVC", "First Example", "Second Example"));
        CourseDTO course4 = new CourseDTO("Course4", "Maven",
                "Most popular maven course on internet!", Arrays.asList(
                "Pom.xml", "Build Life Cycle", "Parent POM",
                "Importing into Eclipse"));

        StudentDTO ranga = new StudentDTO(1, "Ranga Karanam",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        StudentDTO satish = new StudentDTO(2, "Satish T",
                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        students.add(ranga);
        students.add(satish);
    }
    @Override
    public List<StudentDTO> retrieveAllStudents() {
        return students;
    }
    @Override
    public StudentDTO retrieveStudent(int studentId) {
        for (StudentDTO student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }
    @Override
    public List<CourseDTO> retrieveCourses(int studentId) {
        StudentDTO student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }
    @Override
    public CourseDTO retrieveCourse(int studentId, String courseId) {
        StudentDTO student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (CourseDTO course : student.getCourses()) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }

    private SecureRandom random = new SecureRandom();
    @Override
    public CourseDTO addCourse(int studentId, CourseDTO course) {
        StudentDTO student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }
}
