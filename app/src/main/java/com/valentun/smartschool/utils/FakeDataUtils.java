package com.valentun.smartschool.utils;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.DTO.Lesson;
import com.valentun.smartschool.DTO.School;
import com.valentun.smartschool.DTO.Teacher;

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

    public static final ArrayList<School> allSchools = generateSchools();
    public static final ArrayList<Group> allGroups = generateGroups();
    public static final ArrayList<Teacher> allTeachers  = generateTeachers();


    private static ArrayList<Group> generateGroups() {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < GROUPS_COUNT ; i++) {
            for (int j = 0; j < GROUP_POSTFIXES.length; j++) {
                result.add(new Group(i*10 + j, (i + 1) + GROUP_POSTFIXES[j]));
            }
        }
        return result;
    }

    private static ArrayList<School> generateSchools() {
        ArrayList<School> result = new ArrayList<>();
        for (int i = 0; i < SCHOOLS_COUNT; i++) {
            result.add(new School(i, "City " + ((i + 1) / SCHOOLS_IN_CITY), "School " + (i % SCHOOLS_IN_CITY)));
        }
        return result;
    }


    public static ArrayList<Group> findGroups(String groupName, int maxResults) {
        ArrayList<Group> searchResult = new ArrayList<>();
        for (Group group : allGroups) {
            if (group.getName().contains(groupName)) {
                searchResult.add(group);
                if (searchResult.size() == maxResults) break;
            }
        }
        return searchResult;
    }

    public static Group findGroupById(long id) {
        for (Group group: allGroups) {
            if (group.getId() == id) return group;
        }

        return null;
    }

    public static Teacher findTeacherById(long id) {
        for (Teacher teacher: allTeachers) {
            if (teacher.getId() == id) return teacher;
        }

        return null;
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

    public static ArrayList<Teacher> generateTeachers() {
        ArrayList<Teacher> result = new ArrayList<>();

        for (int i = 0; i < TEACHERS_COUNT; i++) {
            result.add(new Teacher(i, "Teacher " + (i+ 1)));
        }

        return result;
    }
}
