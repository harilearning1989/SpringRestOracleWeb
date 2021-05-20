package com.web.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE_AUDITING")
public class EmployeeAudit extends Audit<String> {

    @Id
    @Column(name = "EMP_AUDIT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int empAuditId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "COMPANY_NAME")
    private String compName;

    public int getEmpAuditId() {
        return empAuditId;
    }

    public void setEmpAuditId(int empAuditId) {
        this.empAuditId = empAuditId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    @Override
    public String toString() {
        return "empAuditId= " + getEmpAuditId() + " firstName= " + getFirstName() + " lastName= " + getLastName()
                + " createdBy= " + getCreatedBy() + " modifiedBy= " + getModifiedBy()
                + " createdDate= " + getCreatedDate() + " modifiedDate= " + getModifiedDate();
    }
}
