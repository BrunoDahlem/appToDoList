package com.example.apptodolist.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptodolist.R;
import com.example.apptodolist.controller.UserController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PerfilPerson extends AppCompatActivity {

    private TextView name_user,email_user;
    private Button btn_exit,btn_tasks;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_person);

        LoadComponents();

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserController userController = new UserController(view.getContext());
                userController.signOutUser();
                Intent intent = new Intent(PerfilPerson.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
        btn_tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilPerson.this,ListTasks.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference dr = db.collection("Users").document(user_id);
        dr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    name_user.setText(value.getString("name"));
                    email_user.setText(email);
                }
            }
        });
    }

    private void LoadComponents() {
        name_user = findViewById(R.id.name_user);
        email_user = findViewById(R.id.email_user);
        btn_exit = findViewById(R.id.btn_exit);
        btn_tasks = findViewById(R.id.btn_tasks);
    }
}

