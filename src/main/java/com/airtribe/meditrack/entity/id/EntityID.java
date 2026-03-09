package com.airtribe.meditrack.entity.id;

import java.util.Objects;
/**
 * @author Kavitha Krishnan
 * @since 2026
 */

public record EntityID(String value) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityID patientId)) return false;
        return Objects.equals(value, patientId.value);
    }

    @Override
    public String toString() {
        return value;
    }
}