package com.example.apptodolist.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.apptodolist.R;
import com.example.apptodolist.model.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class TaskController implements View.OnClickListener {
    FirebaseFirestore db;
    public TaskController() {
        this.db = FirebaseFirestore.getInstance();
    }
    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.form_tasks,null,false);

        final EditText editTextTitle = (EditText) formElementsView.findViewById(R.id.edit_name_task);

        new AlertDialog.Builder(context).setView(formElementsView).setTitle("Nova Tarefa").setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = editTextTitle.getText().toString();
                Task task = new Task(title);
                Map<String,Object> tasks = new HashMap<>();
                tasks.put("tasks", FieldValue.arrayUnion(task));

                String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                DocumentReference dr = db.collection("Users").document(user_id);
                dr.set(tasks, SetOptions.merge());

                dialogInterface.cancel();
            }
        }).show();
    }
}
