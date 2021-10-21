package com.dustinscharf.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_USER_DATA = "com.dustinscharf.loginapp.EXTRA_USER_DATA";

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Register");

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(view -> registerButtonClicked());
    }

    private void registerButtonClicked() {
        if (this.usernameEditText.getText().toString().equals("")) {
            Toast.makeText(
                    this,
                    "Enter an username",
                    Toast.LENGTH_SHORT
            ).show();

        } else if (this.passwordEditText.getText().toString().equals("")) {
            Toast.makeText(
                    this,
                    "Enter a password",
                    Toast.LENGTH_SHORT
            ).show();

        } else {
            this.openLoginActivity();
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);

        User user = new User(
                this.usernameEditText.getText().toString(),
                this.passwordEditText.getText().toString()
        );
        intent.putExtra(EXTRA_USER_DATA, user);

        startActivity(intent);
    }
}