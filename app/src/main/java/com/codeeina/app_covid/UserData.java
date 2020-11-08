package com.codeeina.app_covid;

import java.util.LinkedList;

public class UserData {
    private static String name = "";
    private static boolean logged = false;
    private static LinkedList<String> friends = new LinkedList<String>();

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

    public static LinkedList<String> getFriends() {
        return friends;
    }

    public static void addFriends(String friend) {
        friends.add(friend);
    }

    static {
        friends.add("Marius");
    }
}
