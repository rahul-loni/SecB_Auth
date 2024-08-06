package com.example.secb_auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity {
    Button getEmail;
    EditText txt_email;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password_page);

        getEmail=findViewById(R.id.btn_getEmail);
        txt_email=findViewById(R.id.txt_email);
        firebaseAuth=FirebaseAuth.getInstance();

        getEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=txt_email.getText().toString().trim();
                if (Email.isEmpty()){
                    Toast.makeText(ForgotPasswordPage.this, "Enter Email First", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPasswordPage.this, "Please Check Email", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });
    }
}