package com.nhsoft.test;

public class PhoneNumber {
    private String name;
    private int number;


    public PhoneNumber() {
    }

    public PhoneNumber(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (number != that.number) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

}
