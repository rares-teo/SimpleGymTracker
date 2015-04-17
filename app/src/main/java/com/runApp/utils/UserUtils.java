package com.runApp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rares on 20/02/15.
 */
public class UserUtils {

    private static final String PREFERENCES = "user_pref";
    private static final String IS_FIRST_TIME = "is_first_time";
    private static final String USER_AGE = "user_age";
    private static final String USER_WEIGHT = "user_weight";
    private static final String USER_HEIGHT = "user_height";
    private static final String USER_GENDER = "user_gender";
    private static final String USER_RESTING_HEART_RATE = "user_resting_heart_rate";
    private static final String USER_MAXIMUM_HEART_RATE = "user_resting_heart_rate";
    private static final String EXERCISE_NUMBER = "exercise_number";
    private static final String TODAY_DATE_STRING = "today_date";
    private static final String DEVICE_BATTERY = "device_battery";
    private static final String VERY_HARD_LIMIT = "very_hard_limit";
    private static final String HARD_LIMIT = "hard_limit";
    private static final String MODERATE_LIMIT = "moderate_limit";
    private static final String LIGHT_LIMIT = "light_limit";
    //    private static final String ACTUAL_SPEED = "actual_speed";
    private static final String IS_TRACKING = "is_tracking_now";
    private static final String STEPS_NUMBER = "steps_number";
    private static final String BURNT_CALORIES = "calories";
    private static final String IS_SERVICE_RUNNING = "is_service_running";
    private static final String ALARM_TIME = "alarm_time";

    private static SharedPreferences mUserPrefs;
    private static String userGender;
    private static Date alarmTime = null;
    private static String todayDateString = null;
    private static Date todayDate = null;

    private static SharedPreferences getPrefs() {
        if (mUserPrefs == null) {
            mUserPrefs = Utils.getContext().getSharedPreferences(PREFERENCES,
                    Context.MODE_PRIVATE);
        }
        return mUserPrefs;
    }

    public static boolean isFirstTime() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_TIME, true);
    }

    public static void setIsFirstTime(boolean firstTime) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME, firstTime).apply();
    }

    public static boolean isServiceRunning() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_SERVICE_RUNNING, false);
    }

    public static void setIsServiceRunning(boolean isServiceRunning) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(IS_SERVICE_RUNNING, isServiceRunning).apply();
    }

    public static float getBurntCalories() {
        LogUtils.LOGE("UserUtils", "getBurntCalories");
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(BURNT_CALORIES, 0);
    }

    public static void setBurntCalories(float calories) {
        LogUtils.LOGE("UserUtils", "setBurntCalories" + calories);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(BURNT_CALORIES, calories).apply();
    }

    public static int getStepsNumber() {
        LogUtils.LOGE("UserUtils", "getStepsNumber");
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(STEPS_NUMBER, 0);
    }

    public static void setStepsNumber(int stepsNumber) {
        LogUtils.LOGE("UserUtils", "setStepsNumber " + stepsNumber);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(STEPS_NUMBER, stepsNumber).apply();
    }

    public static int getExerciseNumber() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(EXERCISE_NUMBER, 0);
    }

    public static void setExerciseNumber(int exerciseNumber) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(EXERCISE_NUMBER, exerciseNumber).apply();
    }

    public static int getUserAge() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //TODO change default value
        return sharedPreferences.getInt(USER_AGE, 23);
    }

    public static void setUserAge(int userAge) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(USER_AGE, userAge).apply();
        setVeryHardLimit();
        setHardLimit();
        setModerateLimit();
        setLightLimit();
    }

    public static int getUserWeight() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //TODO change default value
        return sharedPreferences.getInt(USER_WEIGHT, 70);
    }

    public static void setUserWeight(int weight) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(USER_WEIGHT, weight).apply();
    }

    public static int getUserHeight() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //TODO change default value
        return sharedPreferences.getInt(USER_HEIGHT, 175);
    }

    public static void setUserHeight(int height) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(USER_HEIGHT, height).apply();
    }

    public static void setUserGender(String gender) {
        SharedPreferences.Editor editor = getPrefs().edit();
        userGender = gender;
        editor.putString(USER_GENDER, userGender).apply();
    }

    public static String getUserGender() {
        if (userGender == null) {
            userGender = getPrefs().getString(USER_GENDER, "Man");
        }
        return userGender;
    }

    public static int getUserRestingHeartHeartRate() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //TODO change default value
        return sharedPreferences.getInt(USER_RESTING_HEART_RATE, 70);
    }

    public static void setUserRestingHeartRate(int restingHeartRate) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(USER_RESTING_HEART_RATE, restingHeartRate).apply();
    }

    public static float getUserMaximumHeartRate() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        //TODO change default value
        return sharedPreferences.getFloat(USER_MAXIMUM_HEART_RATE, 200);
    }

    public static void setUserMaximumHeartRate() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(USER_MAXIMUM_HEART_RATE, (208 - (0.7f * getUserAge()))).apply();
    }

    public static String getTodayDateString() {
        if (todayDateString == null) {
            todayDateString = getPrefs().getString(TODAY_DATE_STRING, "");
        }
        return todayDateString;
    }

    public static Date getTodayDate() {
        if (todayDate == null) {
            try {
                todayDate = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).parse(getTodayDateString());
            } catch (ParseException e) {
                return null;
            }
        }
        return todayDate;
    }

    private static void setTodayDateString(String todayDateString) {
        LogUtils.LOGE("setTodayDateString", todayDateString);
        SharedPreferences.Editor editor = getPrefs().edit();
        if (todayDateString != null && !"".equals(todayDateString)) {
            UserUtils.todayDateString = todayDateString;
            editor.putString(TODAY_DATE_STRING, todayDateString).apply();
        } else {
            UserUtils.todayDateString = null;
        }
    }

    public static void checkDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        Date today = new Date();
//        try {
//            Date todayWithZeroTime = formatter.parse(formatter.format(today));
//            String dateString = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).format(todayWithZeroTime);
//            if (!dateString.equals(getTodayDateString())) {
//                setTodayDateString(dateString);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dateInString = "11/4/2015 19:56";
        try {
            Date date = sdf.parse(dateInString);
            String dateString = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).format(date);
            if (!dateString.equals(getTodayDateString())) {
                setTodayDateString(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int getDeviceBattery() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(DEVICE_BATTERY, -1);
    }

    public static void setDeviceBattery(int deviceBattery) {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(DEVICE_BATTERY, deviceBattery).apply();
    }

    public static float getVeryHardLimit() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(VERY_HARD_LIMIT, 170.0f);
    }

    private static void setVeryHardLimit() {
        float veryHardLimit = (90 * getUserMaximumHeartRate() / 100.0f);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(VERY_HARD_LIMIT, veryHardLimit).apply();
    }

    public static float getHardLimit() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(HARD_LIMIT, 152.0f);
    }

    private static void setHardLimit() {
        float veryHardLimit = (80 * getUserMaximumHeartRate() / 100.0f);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(HARD_LIMIT, veryHardLimit).apply();
    }

    public static float getModerateLimit() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(MODERATE_LIMIT, 133.0f);
    }

    private static void setModerateLimit() {
        float veryHardLimit = (70 * getUserMaximumHeartRate() / 100.0f);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(MODERATE_LIMIT, veryHardLimit).apply();
    }

    public static float getLightLimit() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(LIGHT_LIMIT, 114.0f);
    }

    private static void setLightLimit() {
        float veryHardLimit = (60 * getUserMaximumHeartRate() / 100.0f);
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(LIGHT_LIMIT, veryHardLimit).apply();
    }

//    public static float getActualSpeed() {
//        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
//        return sharedPreferences.getFloat(ACTUAL_SPEED, 0.0f);
//    }
//
//    public static void setActualSpeed(float speed) {
//        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
//        sharedPreferences.edit().putFloat(ACTUAL_SPEED, speed).apply();
//    }

    //used for inserting from GPS Tracker
    public static boolean isTracking() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_TRACKING, false);
    }

    public static void setIsTracking() {
        SharedPreferences sharedPreferences = Utils.getContext().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(IS_TRACKING, !isTracking()).apply();
    }

    public static void setAlarmTime(String alarmTimeSet) {
        SharedPreferences.Editor editor = getPrefs().edit();
        if (alarmTimeSet != null && !"".equals(alarmTimeSet)) {
            try {
                alarmTime = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).parse(alarmTimeSet);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            editor.putString(ALARM_TIME, alarmTimeSet).apply();
        } else {
            alarmTime = null;
        }
    }

    public static Date getAlarmTime() {
        if (alarmTime == null) {
            try {
                alarmTime = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US).parse(
                        getPrefs().getString(ALARM_TIME, ""));
            } catch (ParseException e) {
                return null;
            }
        }
        return alarmTime;
    }

}
