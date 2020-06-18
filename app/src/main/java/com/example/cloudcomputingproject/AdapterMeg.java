package com.example.cloudcomputingproject;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterMeg  extends RecyclerView.Adapter<AdapterMeg.MyHolder>{

private  static  final  int MSG_TYPE_LEFT = 0;
    private  static  final  int MSG_TYPE_RIGHT = 1;

    Context context;
    List<MessageChat> chatList;

    FirebaseUser firebaseUser;

    public AdapterMeg(Context context, List<MessageChat> chatList) {
        this.context = context;
        this.chatList = chatList;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType == MSG_TYPE_RIGHT){
           View view = LayoutInflater.from(context).inflate(R.layout.row_msg_right,parent , false);
           return  new MyHolder(view);
       }else{
           View view = LayoutInflater.from(context).inflate(R.layout.row_msg_left,parent , false);
           return  new MyHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        String message = chatList.get(position).getMessage();

        holder.messgeTv.setText(message);

        if(position == chatList.size()-1){
            if (chatList.get(position).isSend()){
                holder.isSendTv.setText("Seen");

            }else{
                holder.isSendTv.setText("Deliverd");

            }
        }else{
            holder.isSendTv.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chatList.get(position).getSender().equals(firebaseUser.getUid())){
            return  MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }


    }

    class MyHolder extends RecyclerView.ViewHolder{


        ImageView pof;
        TextView messgeTv , timeTv , isSendTv;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            pof = itemView.findViewById(R.id.poo);
            messgeTv = itemView.findViewById(R.id.messageTv);
            isSendTv = itemView.findViewById(R.id.isSeenTv);

        }
    }
}
