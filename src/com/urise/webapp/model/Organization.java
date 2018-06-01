package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization {

    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link link, List<Position> positions) {
        this.homePage = link;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + positions.hashCode();
        return result;
    }

    private static class Position {

        private LocalDate dateStart;
        private LocalDate dateEnd;
        private String title;
        private String description;

        public Position(String name, String url, LocalDate dateStart, LocalDate dateEnd, String title, String description) {
            Objects.requireNonNull(title, "Title required");
            Objects.requireNonNull(dateStart, "dateStart required");
            Objects.requireNonNull(dateEnd, "dateEnd required");
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.title = title;
            this.description = description;
        }

        public LocalDate getDateStart() {
            return dateStart;
        }

        public LocalDate getDateEnd() {
            return dateEnd;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;

            if (!dateStart.equals(position.dateStart)) return false;
            if (dateEnd != null ? !dateEnd.equals(position.dateEnd) : position.dateEnd != null) return false;
            if (!title.equals(position.title)) return false;
            return description.equals(position.description);
        }

        @Override
        public int hashCode() {
            int result = dateStart.hashCode();
            result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "dateStart=" + dateStart +
                    ", dateEnd=" + dateEnd +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
