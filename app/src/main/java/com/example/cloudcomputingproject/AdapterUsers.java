package com.example.cloudcomputingproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterUsers  extends RecyclerView.Adapter<AdapterUsers.MyHolder>{
       Context context;
       List<ModelUser> userList;

    public AdapterUsers(Context context, List<ModelUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.row_users,parent , false);



        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        final String hisUid = userList.get(position).getUid();
        final String user = userList.get(position).getEmail();

      holder.Email.setText(user);

      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(context,ChatActivity.class);
              i.putExtra("hisUid", hisUid);
              i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              context.startActivity(i);

          }
      });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView Email;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            Email = itemView.findViewById(R.id.emailUser);

        }
    }
}
