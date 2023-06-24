package com.example.apptodolist.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apptodolist.R;
import com.example.apptodolist.adapter.TaskAdapter;
import com.example.apptodolist.controller.TaskController;
import com.example.apptodolist.model.Task;
import com.example.apptodolist.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class ListTasks extends AppCompatActivity {
    private TextView title_list_tasks;
    private RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    private Button btn_tasks, btn_return_perfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);
        LoadComponents();
        btn_return_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTasks.this, PerfilPerson.class);
                startActivity(intent);
                finish();
            }
        });
        btn_tasks.setOnClickListener(new TaskController());

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference dr = db.collection("Users").document(user_id);
        dr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    User user = value.toObject(User.class);
                    taskAdapter = new TaskAdapter(user.getTasks());
                    recyclerView.setAdapter(taskAdapter);
                    title_list_tasks.setText("Lista de tarefas de " + value.getString("name"));
                }
            }
        });
    }

    private void LoadComponents() {
        btn_tasks = findViewById(R.id.btn_tasks);
        btn_return_perfil = findViewById(R.id.btn_return_perfil);
        title_list_tasks = findViewById(R.id.title_list_tasks);
        recyclerView = findViewById(R.id.recycler_view_tasks);
    }
}