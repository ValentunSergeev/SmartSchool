package com.valentun.smartschool.DTO;

/**
 * Created by Valentun on 08.07.2017.
 */

public class Teacher {
    private long id;
    private String name;

    public Teacher() {
    }

    public Teacher(long id, String name) {
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
