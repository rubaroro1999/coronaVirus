package com.example.cloudcomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView ;
    Button play , stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        play = findViewById(R.id.play);
//        stop = findViewById(R.id.stop);
        videoView = findViewById(R.id.videoView);


        String url ="https://firebasestorage.googleapis.com/v0/b/coronaviurs-ca084.appspot.com/o/Hand-washing%20Steps%20Using%20the%20WHO%20Technique.mp4?alt=media&token=a72d6ea6-6a33-48c6-b10a-42298eb335b1";
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
//
    }
}
