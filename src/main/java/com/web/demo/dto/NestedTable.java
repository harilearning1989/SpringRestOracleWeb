package com.web.demo.dto;

public class NestedTable {

    private int empId;
    private String lastName;

    public NestedTable(int employee_id,String last_name){
        this.empId=employee_id;
        this.lastName=last_name;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
