package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private Link organizationUrl;

    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String title;
    private String description;

    public Organization(String name, String url, LocalDate dateStart, LocalDate dateEnd, String title, String description) {
        Objects.requireNonNull(title, "Title required");
        Objects.requireNonNull(dateStart, "dateStart required");
        Objects.requireNonNull(dateEnd, "dateEnd required");
        this.organizationUrl = new Link(name, url);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organizationUrl.equals(that.organizationUrl)) return false;
        if (!dateStart.equals(that.dateStart)) return false;
        if (!dateEnd.equals(that.dateEnd)) return false;
        if (!title.equals(that.title)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = organizationUrl.hashCode();
        result = 31 * result + dateStart.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationUrl=" + organizationUrl +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
