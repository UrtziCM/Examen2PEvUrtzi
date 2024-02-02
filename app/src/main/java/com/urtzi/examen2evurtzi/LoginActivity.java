package com.urtzi.examen2evurtzi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.username);
        pass = findViewById(R.id.passwd);
        Button loginButton = findViewById(R.id.iniciarsesion);
        loginButton.setOnClickListener(e -> tryLogin(user.getText().toString(), pass.getText().toString()));
        Button volverButton = findViewById(R.id.volver);
        volverButton.setOnClickListener(e -> finish());
    }

    private void tryLogin(String username, String password) {
        ImageView errorImage = findViewById(R.id.imagenerror);
        if (username.equals("admin") && password.equals("admin")) {
            login();
            errorImage.setVisibility(View.INVISIBLE);
        }
        else {
            MainActivity.ShowDialogWindow(LoginActivity.this,"Login incorrecto", "Usuario o contraseña incorrecto/s prueba con otras credenciales");
            errorImage.setVisibility(View.VISIBLE);
        }
    }

    private void login() {
        Intent intent = new Intent(this, AdministrationActivity.class);
        startActivity(intent);
    }
}