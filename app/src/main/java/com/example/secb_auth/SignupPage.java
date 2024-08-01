package com.example.secb_auth;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPage extends AppCompatActivity {
    EditText email,password,Cpassword;
    Button signup;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_page);

        firebaseAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.txt_email);
        password=findViewById(R.id.txt_password);
        Cpassword=findViewById(R.id.txt_Cpassword);
        signup=findViewById(R.id.btn_signup);

        //Click on Signup Button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String cPassword=Cpassword.getText().toString().trim();

                if (Email.isEmpty()){
                    Toast.makeText(SignupPage.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (Password.isEmpty()){
                    Toast.makeText(SignupPage.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if (Password.length()<6){
                    Toast.makeText(SignupPage.this, "Password Too short", Toast.LENGTH_SHORT).show();
                }
                if (cPassword.equals(Password)){
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   Toast.makeText(SignupPage.this, "Signup Complete", Toast.LENGTH_SHORT).show();
                                   Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                   startActivity(intent);
                                   finish();
                               }else {
                                   Toast.makeText(SignupPage.this, "Error ", Toast.LENGTH_SHORT).show();
                               }
                        }
                    });
                }
            }
        });
    }
}