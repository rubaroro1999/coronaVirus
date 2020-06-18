package com.example.cloudcomputingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class preventionActivity extends AppCompatActivity {
    GridLayout mainGrid;
    ImageView im11, im22 , im33 , im44 ,im55 , im66;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevention);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid1);
//        setSingleEvent(mainGrid);

        im11 = findViewById(R.id.im11);
        im22 = findViewById(R.id.im22);
        im33 = findViewById(R.id.im33);
        im44 = findViewById(R.id.im44);
        im55 = findViewById(R.id.im55);
        im66 = findViewById(R.id.im66);
        getImage("handwash.png",im11);
        getImage("clean.png",im22);
        getImage("donttouch.png",im33);
        getImage("isolate.png",im44);
        getImage("stayhome.png",im55);
        getImage("wearing_mask.png",im66);
        setSingleEvent(mainGrid);
    }


    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI == 0){
                        Intent intent = new Intent(preventionActivity.this, VideoActivity.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }

    private void getImage(String imageName , final ImageView im) {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        final Bitmap[] my_image = new Bitmap[1];
        StorageReference ref = storage.getReference().child(imageName);
        try {
            final File localFile = File.createTempFile("Images", "png");
            ref.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    my_image[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    im.setImageBitmap(my_image[0]);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
