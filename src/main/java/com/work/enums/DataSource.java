package com.work.enums;

public enum DataSource {
    MASTER("master"),
    SLAVE("slave");

    private String name;

    DataSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
