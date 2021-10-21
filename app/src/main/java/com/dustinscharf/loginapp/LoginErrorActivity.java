package com.dustinscharf.loginapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;
import java.util.Random;

public class LoginErrorActivity extends AppCompatActivity {
    private TextView codeTextView;
    private EditText codeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_error);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login refused");

        codeTextView = findViewById(R.id.codeTextView);
        codeEditText = findViewById(R.id.codeEditText);

        codeTextView.setText(this.generateCode());

        Button tryAgainButton = findViewById(R.id.tryAgainButton);
        tryAgainButton.setOnClickListener(view -> checkCode());
    }

    private void checkCode() {
        String inputCode = this.codeEditText.getText().toString();
        String generatedCode = codeTextView.getText().toString();

        if (!inputCode.equals(generatedCode)) {
            Toast.makeText(
                    this,
                    "Wrong code, please try again...",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        finish();
    }

    private String generateCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; ++i) {
            int randomDigit = random.nextInt(10);
            stringBuilder.append(randomDigit);
        }
        return stringBuilder.toString();
    }
}