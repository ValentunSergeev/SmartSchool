package com.valentun.smartschool.utils;

import com.valentun.smartschool.DTO.Group;
import com.valentun.smartschool.DTO.School;

import java.util.ArrayList;

/**
 * Created by Valentun on 22.06.2017.
 */

public class FakeDataUtils {
    private static final ArrayList<School> allSchools = buildAllSchools();
    public static final ArrayList<Group> allGroups = buildGroups();

    private static final int SCHOOLS_COUNT = 100;
    private static final int SCHOOLS_IN_CITY = 10;

    private static final int GROUPS_COUNT = 11;
    private static final String[] GROUP_POSTFIXES = {"A", "B", "C"};


    private static ArrayList<Group> buildGroups() {
        ArrayList<Group> result = new ArrayList<>();
        for (int i = 0; i < GROUPS_COUNT ; i++) {
            for (String postfix: GROUP_POSTFIXES) {
                result.add(new Group(i, (i + 1) + postfix));
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

    public static ArrayList<Group> findGroups(String groupName, int maxResults) {
        ArrayList<Group> searchResult = new ArrayList<>();
        for (Group group: allGroups) {
            if (group.getName().contains(groupName)) {
                searchResult.add(group);
                if (searchResult.size() == maxResults) break;
            }
        }
        return searchResult;
    }
}
