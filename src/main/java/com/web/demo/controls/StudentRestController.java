package com.web.demo.controls;

import com.web.demo.dto.CourseDTO;
import com.web.demo.dtos.StudentDTO;
import com.web.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getMapping")
    public ResponseEntity<List<StudentDTO>> getStudents() {
        List<StudentDTO> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/postMapping")
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO student) {
        student = studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/putMapping")
    public ResponseEntity<StudentDTO> putExample(@RequestBody StudentDTO student) {
        student = studentService.updateStudent(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMapping")
    public ResponseEntity<String> deleteExample(@RequestParam("student-id") String studentId) {
        String response = studentService.deleteStudent(studentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/courses")
    public List<CourseDTO> retrieveCoursesForStudent(@PathVariable int studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @GetMapping("/students/{studentId}/courses/{courseId}")
    public CourseDTO retrieveDetailsForCourse(@PathVariable int studentId,
                                              @PathVariable String courseId) {
        return studentService.retrieveCourse(studentId, courseId);
    }

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<Void> registerStudentForCourse(
            @PathVariable int studentId, @RequestBody CourseDTO newCourse) {

        CourseDTO course = studentService.addCourse(studentId, newCourse);

        if (course == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
