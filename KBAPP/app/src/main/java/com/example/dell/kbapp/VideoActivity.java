package com.example.dell.kbapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {


    VideoView videoView;

    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        videoView = findViewById(R.id.videoView2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String videoPath = "android.resource://" + getPackageName() + "/raw/cp";
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }


//    public void vidioPlay(View v){
//        String videoPath = "android.resource://" + getPackageName() + "/raw/cp";
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        videoView.start();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
