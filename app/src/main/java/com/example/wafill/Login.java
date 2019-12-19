package com.example.wafill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText email, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void showDashboard(View view) {
        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
        } else if (passwordInput.isEmpty()) {
            password.setError("Field Can't be empty");
        } else if (passwordInput.length() < 6) {
            password.setError("Password too short");
        } else {
            email.setError(null);
            password.setError(null);
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Logging in. Please wait.");
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(emailInput, passwordInput)
                    .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent dashboardActivity = new Intent(Login.this, MainActivity.class);
                                startActivity(dashboardActivity);
                            } else {
                                Toast.makeText(Login.this, "Login Failed or User not registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void showSignUpScreen(View view) {
        Intent signUpActivity = new Intent(this, SignUp.class);
        startActivity(signUpActivity);
    }

}
