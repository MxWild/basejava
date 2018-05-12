package com.urise.webapp.model;

import java.util.Objects;

public class DescSection extends Section {

    private String description;

    public DescSection(String description) {
        Objects.requireNonNull(description, "Description must be set");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DescSection that = (DescSection) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return "DescSection{" +
                "description='" + description + '\'' +
                '}';
    }
}
