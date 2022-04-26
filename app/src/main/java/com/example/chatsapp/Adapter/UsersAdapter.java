package com.example.chatsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatsapp.Models.Users;
import com.example.chatsapp.R;
import com.example.chatsapp.chatdetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Viewholder> {

    ArrayList<Users> list;
    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);
   return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Users users= list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.avatar3).into(holder.image);
        holder.username.setText(users.getUserName());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
   Intent intent = new Intent(context, chatdetailActivity.class);
   intent.putExtra("userId",users.getUserId());
   intent.putExtra("profilePic",users.getProfilePic());
   intent.putExtra("userName",users.getUserName());

   context.startActivity(intent);
    }
});


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView username,lastname;

        public Viewholder(@NonNull View itemView) {


            super(itemView);

        image = itemView.findViewById(R.id.profile_img);
        username = itemView.findViewById(R.id.usernamelist);
        lastname = itemView.findViewById(R.id.lastmessage);

        }
    }
}
