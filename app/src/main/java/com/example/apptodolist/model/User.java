package com.example.apptodolist.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String email;
    private String pass;
    private ArrayList<Task> tasks;

    public User(){

    }

    public User(String name,String email,String pass){
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public User( String id,String name,String email,String pass){
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
