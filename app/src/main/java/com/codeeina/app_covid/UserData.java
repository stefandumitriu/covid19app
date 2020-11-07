package com.codeeina.app_covid;

public class UserData {
    private static String name = "";
    private static boolean logged = false;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserData.name = name;
    }

    public static boolean isLogged() {
        return logged;
    }

    public static void setLogged(boolean logged) {
        UserData.logged = logged;
    }
}
