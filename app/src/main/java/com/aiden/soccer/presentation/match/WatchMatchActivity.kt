package com.aiden.soccer.presentation.match

import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.MediaController
import com.aiden.soccer.databinding.ActivityWatchMatchBinding
import com.aiden.soccer.presentation.base.BaseActivity
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class WatchMatchActivity :
    BaseActivity<ActivityWatchMatchBinding, MainViewModel>(MainViewModel::class) {

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        intent.getStringExtra(KEY_PREVIOUS_VIDEO)?.let { videoUrl ->
            with(binding) {
                videoView.setVideoURI(Uri.parse(videoUrl))
                val mediaController = MediaController(this@WatchMatchActivity)
                mediaController.setAnchorView(videoView)
                mediaController.setMediaPlayer(videoView)
                videoView.setMediaController(mediaController)
                videoView.setZOrderOnTop(true)
                videoView.requestFocus()
                videoView.start()
            }
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityWatchMatchBinding {
        return ActivityWatchMatchBinding.inflate(inflater)
    }

    override fun onSubscribeObserver() {
        viewModelSelf.localTeamsLiveData.observe(this) {
            val localTeam = viewModelSelf.localTeams
        }
    }

    companion object {
        const val KEY_PREVIOUS_VIDEO = "key_previous_video"
    }
}