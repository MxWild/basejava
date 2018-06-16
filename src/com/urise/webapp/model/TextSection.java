package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends Section {

    private String description;

    public TextSection() {
    }

    public TextSection(String description) {
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

        TextSection that = (TextSection) o;

        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "description='" + description + '\'' +
                '}';
    }
}
