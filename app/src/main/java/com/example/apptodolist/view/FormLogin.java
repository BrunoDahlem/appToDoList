package com.example.apptodolist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.apptodolist.R;
import com.example.apptodolist.controller.UserController;
import com.example.apptodolist.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {

    private TextView txt_sing_up;
    private EditText edit_email,edit_pass;
    private Button btn_login;
    ProgressBar progress_bar;
    User user = new User();
    String[] msgs = {"Preencha todos os campos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        LoadComponents();


        txt_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FormLogin.this, FormSignUp.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserController userController = new UserController(view.getContext());

                String email = edit_email.getText().toString();
                String pass = edit_pass.getText().toString();

                if (email.isEmpty() || pass.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view,msgs[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    user.setEmail(email);
                    user.setPass(pass);
                    userController.signInUser(user,view);
                    //finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(FormLogin.this, PerfilPerson.class);
            startActivity(intent);
            finish();
        }
    }
    private void LoadComponents() {
        txt_sing_up = findViewById(R.id.txt_sing_up);
        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        btn_login = findViewById(R.id.btn_login);
        progress_bar = findViewById(R.id.progress_bar);
    }
}