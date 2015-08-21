package com.tarapus.soundbycloud;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.widget.MediaController;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_play);

        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        int fileType = intent.getIntExtra("type", 0);

        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController;
        mediaController = new MediaController(PlayActivity.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        String pathExternalStorage = Environment.getExternalStorageDirectory().toString()+"/Movies/";
        if(fileType == 1)
            videoView.setVideoPath(pathExternalStorage + path +".mp3");
        else
            videoView.setVideoPath(pathExternalStorage + path +".mp4");

        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mediaplayer){
                finish();
            }

        });
    }
}
