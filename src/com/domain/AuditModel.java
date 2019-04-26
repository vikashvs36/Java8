package com.domain;

import java.util.Date;

// Create AuditModel modal
public class AuditModel {

    private Date createdAt=new Date();
    private Date updatedAt=new Date();

    public AuditModel(){
    }

    public AuditModel(Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
