package com.urise.webapp.model;

public enum ContactType {
    TEL("Телефон:"),
    SKYPE("Skype:"){
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:" + value +"'>" + value + "</a>";
        }
    },
    EMAIL("Email:"){
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    LINKEDIN("LinkedIn:"),
    GITHUB("GitHub:");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toHtml0(String value) {
        return title + " " + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}
