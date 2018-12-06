package com.nhsoft.test;

public enum TestEnum {
    SUCCESS("qq");

    private String name;

    TestEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
