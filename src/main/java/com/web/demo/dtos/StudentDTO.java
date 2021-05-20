package com.web.demo.dtos;

import com.web.demo.dto.CourseDTO;

import java.util.List;

public class StudentDTO {

    private int studentId;
    private String studentName;
    private String fatherName;
    private String gender;
    private String mobile;
    private String category;
    private List<CourseDTO> courses;

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, String fatherName,
                      List<CourseDTO> courses) {
        super();
        this.studentId = id;
        this.studentName = name;
        this.fatherName = fatherName;
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format(
                "Student [id=%s, name=%s, description=%s, courses=%s]", studentId,
                studentName, fatherName, courses);
    }
}
