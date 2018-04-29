package com.urise.webapp.model;

public enum ContactSection {
    TEL("Телефон:"),
    SKYPE("Skype:"),
    EMAIL("Email:"),
    LINKEDIN("LinkedIn:"),
    GITHUB("GitHub:");

    private String title;

    ContactSection(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
