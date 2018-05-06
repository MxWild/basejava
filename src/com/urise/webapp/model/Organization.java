package com.urise.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Organization {

    private String name;
    private String url;

    private Date dateStart;
    private Date dateEnd;
    private String title;
    private String description;

    public Organization(String name, String url, Date dateStart, Date dateEnd, String title, String description) {
        Objects.requireNonNull(title, "Title required");
        Objects.requireNonNull(dateStart, "dateStart required");
        Objects.requireNonNull(dateEnd, "dateEnd required");
        this.name = name;
        this.url = url;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.title = title;
        this.description = description;
    }


}
