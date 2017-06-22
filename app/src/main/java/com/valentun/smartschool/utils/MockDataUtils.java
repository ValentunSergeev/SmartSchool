package com.valentun.smartschool.utils;

import com.valentun.smartschool.DTO.School;

import java.util.ArrayList;

/**
 * Created by Valentun on 22.06.2017.
 */

public class MockDataUtils {
    private static final ArrayList<School> allSchools = buildAllSchools();
    private static final int SCHOOLS_COUNT = 100;
    private static final int SCHOOLS_IN_CITY = 10;

    private static ArrayList<School> buildAllSchools() {
        ArrayList<School> result = new ArrayList<>();
        for (int i = 0; i < SCHOOLS_COUNT; i++) {
            result.add(new School(i, "City " + ((i + 1) / SCHOOLS_IN_CITY), "School " + (i % SCHOOLS_IN_CITY)));
        }
        return result;
    }

    public static ArrayList<School> findSchools(String schoolName, int maxResult) {
        ArrayList<School> searchResult = new ArrayList<>();
        for (School school: allSchools) {
            if (school.getName().contains(schoolName)) {
                searchResult.add(school);
                if (searchResult.size() == maxResult) break;
            }
        }
        return searchResult;
    }
}
