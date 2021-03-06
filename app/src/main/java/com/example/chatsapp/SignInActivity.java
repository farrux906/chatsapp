package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatsapp.Models.Users;
import com.example.chatsapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
    TextView textView;


    ActivitySignInBinding binding;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
   FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textView = findViewById(R.id.textAlreadyhaveaccount1) ;

binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase  = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please Waith\n Validation in Progress.");









        binding.btnsignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.Email.getText().toString().isEmpty() && !binding.Password.getText().toString().isEmpty()){
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(binding.Email.getText().toString(),binding.Password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                                        startActivity(intent);

                                    }
                                    else{
                                        Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });
                }
                else{
                    Toast.makeText(SignInActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
if (mAuth.getCurrentUser()!=null){
    Intent intent = new Intent(SignInActivity.this,MainActivity.class);
    startActivity(intent);

}




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,signupactivity.class);
                startActivity(intent);
            }
        });
    }
}