package com.valentun.smartschool.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Valentun on 22.06.2017.
 */

public class PreferenceUtils {
    private static final String PREFERENCE_SCHOOL_ID_KEY = "selectedSchoolId";

    private static final String PREFERENCE_KEY = "smartSchool";
    private static final long DEFAULT_ID_VALUE = -1;

    private PreferenceUtils(){}

    public static boolean isSchoolSelected(Context context) {
        return getSelectedSchool(context) != DEFAULT_ID_VALUE;
    }

    public static long getSelectedSchool(Context context) {
        SharedPreferences preferences = getPreferences(context);

        return preferences.getLong(PREFERENCE_SCHOOL_ID_KEY, DEFAULT_ID_VALUE);
    }

    public static void setSelectedSchool(Context context, long schoolId) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putLong(PREFERENCE_SCHOOL_ID_KEY, schoolId);
        editor.apply();
    }

    public static void deleteSelectedSchool(Context context) {
        setSelectedSchool(context, DEFAULT_ID_VALUE);
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }


}
