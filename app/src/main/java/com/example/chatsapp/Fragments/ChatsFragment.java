package com.example.chatsapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatsapp.Adapter.UsersAdapter;
import com.example.chatsapp.Models.Users;
import com.example.chatsapp.R;
import com.example.chatsapp.databinding.FragmentChatsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {


    public ChatsFragment() {

    }

FragmentChatsBinding binding;
    ArrayList<Users> list= new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
binding=FragmentChatsBinding.inflate(inflater,container,false);
database=FirebaseDatabase.getInstance();
        UsersAdapter adapter = new UsersAdapter(list,getContext());
      binding.charRecylerview.setAdapter(adapter);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
      binding.charRecylerview.setLayoutManager(layoutManager);

      database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
list.clear();
for(DataSnapshot dataSnapshot : snapshot.getChildren()){
    Users users      = dataSnapshot.getValue(Users.class);
    users.setUserId(dataSnapshot.getKey());
    if (!users.getUserId().equals(FirebaseAuth.getInstance().getUid())){
        list.add(users);
    }

}

adapter.notifyDataSetChanged();
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {



          }





      });




        return binding.getRoot();
    }
}