package com.valentun.smartschool.DTO;

public class School {
    private long id;
    private String city;
    private String name;

    public School() {
    }

    public School(long id,String city, String name) {
        this.city = city;
        this.name = name;
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
