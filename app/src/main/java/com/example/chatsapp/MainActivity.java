package com.example.chatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatsapp.Adapter.fragmentsAdapter;
import com.example.chatsapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class     MainActivity extends AppCompatActivity {

ActivityMainBinding binding;
FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

mAuth= FirebaseAuth.getInstance();

binding.viewPager.setAdapter(new fragmentsAdapter(getSupportFragmentManager()));
binding.TableLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.settings:
                Toast.makeText(this, "setting is clicked", Toast.LENGTH_SHORT).show();
        break;
            case  R.id.groupchat:
                Toast.makeText(this, "Group chat is started.", Toast.LENGTH_SHORT).show();
        break;
            case  R.id.logout:
              mAuth.signOut();
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);

        break;
        }

        return super.onOptionsItemSelected(item);

    }
}