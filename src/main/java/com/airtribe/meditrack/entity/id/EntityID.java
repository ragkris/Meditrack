package com.airtribe.meditrack.entity.id;

import java.util.Objects;

public class EntityID {

    private final String value;

    public EntityID(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityID)) return false;
        EntityID patientId = (EntityID) o;
        return Objects.equals(value, patientId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}