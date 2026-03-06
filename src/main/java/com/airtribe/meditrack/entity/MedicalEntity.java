package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.entity.id.EntityID;

import java.time.LocalDateTime;

public abstract class MedicalEntity {

    protected EntityID id;
    protected LocalDateTime createdAt;

    public MedicalEntity(EntityID id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
    }

    public EntityID getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // common behavior for all entities
    public void printAuditInfo() {
        System.out.println("Entity ID: " + id + " Created At: " + createdAt);
    }
}