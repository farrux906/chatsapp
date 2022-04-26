 package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatsapp.Models.Users;
import com.example.chatsapp.databinding.ActivitySignupactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

 public class signupactivity extends AppCompatActivity {

    TextView textView;
 FirebaseAuth mAuth;
ActivitySignupactivityBinding binding;
FirebaseDatabase database;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);
binding = ActivitySignupactivityBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());


      mAuth = FirebaseAuth.getInstance();
database = FirebaseDatabase.getInstance();
progressDialog = new ProgressDialog(signupactivity.this);
progressDialog.setTitle("Creating Account");
progressDialog.setMessage("We're creating your account.");

binding.btnsignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (!binding.textusername.getText().toString().isEmpty() && !binding.Email.getText().toString().isEmpty() && !binding.Password.getText().toString().isEmpty()){
progressDialog.show();
            mAuth.createUserWithEmailAndPassword(binding.Email.getText().toString(),binding.Password.getText().toString())
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Users users = new Users(binding.textusername.getText().toString(),binding.Email.getText().toString(),binding.Password.getText().toString());
                        String id = task.getResult().getUser().getUid();
                        database.getReference().child("Users").child(id).setValue(users);
                        Toast.makeText(signupactivity.this, "Sign  Up Successful ", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(signupactivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
else{
            Toast.makeText(signupactivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
        }
    }
});


       textView = findViewById(R.id.textAlreadyhaveaccount) ;


       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(signupactivity.this,SignInActivity.class);
               startActivity(intent);
           }
       });
    }
}