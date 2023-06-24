package com.example.apptodolist.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apptodolist.view.PerfilPerson;
import com.example.apptodolist.R;
import com.example.apptodolist.model.Task;
import com.example.apptodolist.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserController {

    FirebaseFirestore db;
    private Context context;
    String[] msgs = {"Preencha todos os campos","Cadastrado com sucesso"};

    public UserController(Context context) {
        this.db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    public void signInUser(User user,View view) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.getEmail(), user.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(context,PerfilPerson.class);
                    context.startActivity(intent);

                } else {
                    String erro = "Erro ao logar usuario";
                    Snackbar snackbar = Snackbar.make(view,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }
    public void signUpUser(User user, View view) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Map<String,Object> users = new HashMap<>();
                    users.put("name",user.getName());

                    String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    DocumentReference dr = db.collection("Users").document(user_id);
                    dr.set(users);
                        Snackbar snackbar = Snackbar.make(view,msgs[1],Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                        Intent intent = new Intent(context, PerfilPerson.class);
                        context.startActivity(intent);
                    } else {
                        String erro;
                        erro = "Erro ao cadastrar usuario";
                        Snackbar snackbar = Snackbar.make(view,erro,Snackbar.LENGTH_SHORT);
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    }
            }
        });
    }

    public void signOutUser() {
        FirebaseAuth.getInstance().signOut();
    }
    public User getUser(String userID) {
        User user = new User();
        user.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());

        DocumentReference dr = db.collection("Users").document(user.getId());
        dr.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    user.setName(value.getString("name"));
                }
            }
        });
        return user;
    }
}
