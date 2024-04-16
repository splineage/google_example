package com.example.stepcountdao

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStoragePublicDirectory
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.stepcountdao.databinding.ActivityMainBinding
import com.example.stepcountdao.databinding.ActivitySecondBinding
import java.io.File

/**
 * @author jskim
 * @email jskim@1thefull.com
 * @created 2023/04/26 1:17 PM
 * @desc
 */
class SecondActivity: AppCompatActivity() {

//    private lateinit var videoView: VideoView
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoView = findViewById<VideoView>(binding.videoView.id)
//        videoView = findViewById(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        val uri : Uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")


        videoView.setVideoURI(uri)
        videoView.start()
    }
}