package com.valentun.smartschool.DTO;



public class Classroom {
    private long id;
    private String name;

    public Classroom() {
    }

    public Classroom(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
