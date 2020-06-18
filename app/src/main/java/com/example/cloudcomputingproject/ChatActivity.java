package com.example.cloudcomputingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChatActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageView profileImg;
    TextView nameTv , statuesTv;
    EditText messageEd;
    ImageButton sendBtn;
    String hisUid;
    String myUid;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;

    ValueEventListener seenListener;
    List<MessageChat>messageChatList;
    AdapterMeg adapterMeg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        recyclerView = findViewById(R.id.rvChat);
        profileImg = findViewById(R.id.profimg);
        nameTv = findViewById(R.id.userName);
        statuesTv = findViewById(R.id.statues);

        messageEd = findViewById(R.id.messageEd);
        sendBtn = findViewById(R.id.sendBtn);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

           Intent i = getIntent();
           hisUid = i.getStringExtra("hisUid");

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Users");

        Query userQury = databaseReference.orderByChild("uid").equalTo(hisUid);
        userQury.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String name = ""+ds.child("email").getValue();
                      nameTv.setText(name);

                }
             }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEd.getText().toString();
                if (TextUtils.isEmpty(message)){
                    Toast.makeText(ChatActivity.this, "Can not send the empty message", Toast.LENGTH_SHORT).show();
                }else{
                    sendMessage(message);
                }
            }
        });




        readMsg();

        seenMsg();
    }

    private void seenMsg() {
        userRef = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    MessageChat chat = ds.getValue(MessageChat.class);
                    if (chat.getReciver().equals(myUid) && chat.getSender().equals(hisUid)){
                        HashMap<String , Object> hashMap = new HashMap<>();
                        hashMap.put("isSeen", true);
                        ds.getRef().updateChildren(hashMap);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readMsg() {

        messageChatList = new ArrayList<>();
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messageChatList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    MessageChat chat = ds.getValue(MessageChat.class);
                    if (chat.getReciver().equals(myUid)&& chat.getSender().equals(hisUid) ||
                            chat.getReciver().equals(hisUid)&& chat.getSender().equals(myUid) )
               messageChatList.add(chat);

                }
                adapterMeg = new AdapterMeg(getApplicationContext(),messageChatList);
                adapterMeg.notifyDataSetChanged();
                recyclerView.setAdapter(adapterMeg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMessage(String message) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
       String timestamp = String.valueOf(System.currentTimeMillis());

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",myUid);
        hashMap.put("reciver",hisUid);
        hashMap.put("message", message);
        hashMap.put("timestamp", timestamp);
        hashMap.put("isSeen", false);

        databaseReference.child("Chats").push().setValue(hashMap);

        messageEd.setText("");


    }

    public  void  checkUserStatues(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
               myUid = firebaseUser.getUid();
        }else{
startActivity(new Intent(getApplicationContext(),LoginActivity.class));
 finish();

        }


    }

    @Override
    protected void onStart() {
        checkUserStatues();
        super.onStart();
    }


    @Override
    protected void onPause() {
        super.onPause();
        userRef.removeEventListener(seenListener);
    }
}
