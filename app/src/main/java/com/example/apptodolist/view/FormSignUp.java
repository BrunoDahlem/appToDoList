package com.example.apptodolist.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apptodolist.R;
import com.example.apptodolist.controller.UserController;
import com.example.apptodolist.model.User;


public class FormSignUp extends AppCompatActivity {

    private EditText edit_name,edit_email,edit_pass;
    private Button btn_sign_up;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sign_up);
        LoadComponents();

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserController userController = new UserController(view.getContext());

                user.setName(edit_name.getText().toString());
                user.setEmail(edit_email.getText().toString());
                user.setPass(edit_pass.getText().toString());

                userController.signUpUser(user,view);
                //finish();
            }
        });
    }
    private void LoadComponents(){
        edit_name = findViewById(R.id.edit_name);
        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        btn_sign_up = findViewById(R.id.btn_sign_up);
    }
}