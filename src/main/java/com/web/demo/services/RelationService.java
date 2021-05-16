package com.web.demo.services;

import com.web.demo.entities.StudentInfo;

import java.util.Collection;
import java.util.List;

public interface RelationService {
    public List<StudentInfo> getStudentInfo();

    public List<StudentInfo> findByCategoryAndGender(String category, String gender);

    public List<StudentInfo> findByFatherName(String fatherName);

    Collection<StudentInfo> getStudentTable(String fatherName);
}
