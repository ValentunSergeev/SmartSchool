package com.valentun.smartschool.utils;

import com.valentun.smartschool.DTO.NamedEntity;
import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.DTO.School;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Valentun on 22.06.2017.
 */

public class FakeDataUtils {
    private static final int SCHOOLS_COUNT = 100;
    private static final int SCHOOLS_IN_CITY = 10;

    private static final int GROUPS_COUNT = 11;
    private static final String[] GROUP_POSTFIXES = {"A", "B", "C"};

    private static final int TEACHERS_COUNT = 30;

    private static final ArrayList<School> allSchools = buildAllSchools();
    public static final ArrayList<NamedEntity> allGroups = buildGroups();


    private static ArrayList<NamedEntity> buildGroups() {
        ArrayList<NamedEntity> result = new ArrayList<>();
        for (int i = 0; i < GROUPS_COUNT ; i++) {
            for (int j = 0; j < GROUP_POSTFIXES.length; j++) {
                result.add(new NamedEntity(i*10 + j, (i + 1) + GROUP_POSTFIXES[j]));
            }
        }
        return result;
    }

    private static ArrayList<School> buildAllSchools() {
        ArrayList<School> result = new ArrayList<>();
        for (int i = 0; i < SCHOOLS_COUNT; i++) {
            result.add(new School(i, "City " + ((i + 1) / SCHOOLS_IN_CITY), "School " + (i % SCHOOLS_IN_CITY)));
        }
        return result;
    }

    public static ArrayList<School> findSchools(String schoolName, int maxResults) {
        ArrayList<School> searchResult = new ArrayList<>();
        for (School school: allSchools) {
            if (school.getName().contains(schoolName)) {
                searchResult.add(school);
                if (searchResult.size() == maxResults) break;
            }
        }
        return searchResult;
    }

    public static ArrayList<NamedEntity> findGroups(String groupName, int maxResults) {
        ArrayList<NamedEntity> searchResult = new ArrayList<>();
        for (NamedEntity group : allGroups) {
            if (group.getName().contains(groupName)) {
                searchResult.add(group);
                if (searchResult.size() == maxResults) break;
            }
        }
        return searchResult;
    }

    public static ArrayList<Lesson> generateLessons() {
        Random random = new Random();
        int count = random.nextInt(3) + 5;
        ArrayList<Lesson> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lesson lesson = new Lesson();
            result.add(lesson);
        }
        return result;
    }

    public static ArrayList<NamedEntity> generateTeachers() {
        ArrayList<NamedEntity> result = new ArrayList<>();

        for (int i = 0; i < TEACHERS_COUNT; i++) {
            result.add(new NamedEntity(i, "Teacher " + (i+ 1)));
        }

        return result;
    }
}
