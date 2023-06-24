package com.example.apptodolist.adapter;

import com.example.apptodolist.controller.UserController;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataBaseAdapter {
    FirebaseFirestore db;

    public DataBaseAdapter() {
        this.db = FirebaseFirestore.getInstance();
    }
}
