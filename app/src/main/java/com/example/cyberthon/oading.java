package com.example.cyberthon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class oading extends AppCompatActivity {
Button tbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oading);
        tbn=findViewById(R.id.nxt);
        tbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(oading.this,MainPage.class));

            }
        });
        VideoView videoView = (VideoView) findViewById(R.id.videoView);  //casting to VideoView is not Strictly required above API level 26
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video); //set the path of the video that we need to use in our VideoView
        videoView.start();  //start() method of the VideoView class will start the video to play
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

}