package com.web.demo.entities;

import java.util.Date;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit<T> {

    @CreatedBy
    @Column(name="created_by")
    protected T createdBy;
    @LastModifiedBy
    @Column(name="modified_by")
    protected T modifiedBy;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    protected Date createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    protected Date modifiedDate;
    public T getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }
    public T getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(T modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getModifiedDate() {
        return modifiedDate;
    }
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @PrePersist
    public void onPrePersist() {
    }

    @PreUpdate
    public void onPreUpdate() {  }

    @PreRemove
    public void onPreRemove() {  }
}
