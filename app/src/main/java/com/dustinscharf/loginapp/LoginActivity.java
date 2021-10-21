package com.dustinscharf.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private User user;

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");

        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(MainActivity.EXTRA_USER_DATA);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> checkLoginData());
    }

    private void checkLoginData() {
        String usernameInput = this.usernameEditText.getText().toString();
        String passwordInput = this.passwordEditText.getText().toString();

        boolean loginSuccess = this.user.checkLoginData(usernameInput, passwordInput);
        if (loginSuccess) {
            openWelcomeActivity();
        } else {
            Toast.makeText(
                    this,
                    "Wrong login data, please enter the code...",
                    Toast.LENGTH_LONG
            ).show();
            openLoginErrorActivity();
        }
    }

    private void openWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);

        intent.putExtra(MainActivity.EXTRA_USER_DATA, this.user);

        startActivity(intent);
    }

    private void openLoginErrorActivity() {
        Intent intent = new Intent(this, LoginErrorActivity.class);
        startActivity(intent);
    }
}