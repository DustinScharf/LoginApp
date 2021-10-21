package com.dustinscharf.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {
    public static final String EXTRA_USER_DATA =
            "com.dustinscharf.loginapp.WelcomeActivity.EXTRA_USER_DATA";

    private User user;

    private EditText passwordEditText;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logoutItem) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(MainActivity.EXTRA_USER_DATA);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(
                "Welcome back, " + this.user.getUsername() + "!"
        );

        passwordEditText = findViewById(R.id.passwordEditText);

        Button changePasswordButton = findViewById(R.id.changePasswordButton);

        changePasswordButton.setOnClickListener(view -> changePasswordButtonClicked());
    }

    private void changePasswordButtonClicked() {
        String newPassword = this.passwordEditText.getText().toString();
        if (newPassword.equals("")) {
            Toast.makeText(
                    this,
                    "Enter a password",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        this.user.setPassword(newPassword);
        this.openLoginActivity();
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);

        intent.putExtra(EXTRA_USER_DATA, this.user);

        startActivity(intent);
    }
}