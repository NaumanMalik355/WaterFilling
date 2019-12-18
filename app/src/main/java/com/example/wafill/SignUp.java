package com.example.wafill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SignUp extends AppCompatActivity {
    EditText username, emailAddress, address, number, password;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.txtUsername);
        emailAddress = findViewById(R.id.textEmail);
        address = findViewById(R.id.txtAddress);
        number = findViewById(R.id.txtPhoneNumber);
        password = findViewById(R.id.textPassword);

        //It will create the collection(Node) name Users
        databaseReference=FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void showDasha(View view) {
        final String usernameInput = username.getText().toString().trim();
        final String emailInput = emailAddress.getText().toString().trim();
        final String addressInput = address.getText().toString().trim();
        final String phoneInput = number.getText().toString().trim();
        final String passwordInput = password.getText().toString().trim();
        if (usernameInput.isEmpty()) {
            username.setError("Field can't be empty");
        } else if (emailInput.isEmpty()) {
            emailAddress.setError("Field can't be empty");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailAddress.setError("Please enter a valid email address");
        } else if (addressInput.isEmpty()) {
            address.setError("Field can't be empty");
        } else if (phoneInput.isEmpty()) {
            number.setError("Field can't be empty");
        } else if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
        } else {
            username.setError(null);
            emailAddress.setError(null);
            address.setError(null);
            number.setError(null);
            password.setError(null);

            firebaseAuth.createUserWithEmailAndPassword(emailInput,passwordInput)
                    .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String id=databaseReference.push().getKey();
                                final Users users=new Users(id,usernameInput,emailInput,addressInput,phoneInput,passwordInput);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Intent dashboardActivity = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(dashboardActivity);
                                    }
                                });
                            }else{

                            }
                        }
                    });
        }
    }
}
