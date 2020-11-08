package com.codeeina.app_covid;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.perfmark.Link;

public class UserData {
    String firstName = "";
    String lastName = "";
    String email = "";
    boolean logged = false;
    List<String> friends;

    public UserData(String firstName, String lastName, String email, boolean logged) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.logged = logged;
        friends = new ArrayList<>();
    }
    public UserData(){

    }
    public String getName() {
        return firstName + " " + lastName;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public List<String> getFriends() {
        return friends;
    }
    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }
    public void addFriends(String friend) {
        friends.add(friend);
    }


    public boolean isLogged() {
        return logged;
    }
    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
