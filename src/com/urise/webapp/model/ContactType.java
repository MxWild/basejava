package com.urise.webapp.model;

public enum ContactType {
    TEL("Телефон:"),
    SKYPE("Skype:"),
    EMAIL("Email:"),
    LINKEDIN("LinkedIn:"),
    GITHUB("GitHub:");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
