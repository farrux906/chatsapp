package com.example.chatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.chatsapp.databinding.ActivityChatdetailBinding;

public class chatdetailActivity extends AppCompatActivity {
    ActivityChatdetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatdetail);
binding= ActivityChatdetailBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
getSupportActionBar().hide();
    }
}