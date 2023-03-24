package com.aiden.soccer.presentation.match

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aiden.soccer.R
import com.aiden.soccer.databinding.ActivityWatchMatchBinding
import com.aiden.soccer.presentation.base.BaseActivity
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class WatchMatchActivity :
    BaseActivity<ActivityWatchMatchBinding, MainViewModel>(MainViewModel::class), Player.Listener {
    private lateinit var exoPlayer: ExoPlayer
    private var videoUrl = ""
    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.lnHeader.imvBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.lnHeader.tvTitle.text = getString(R.string.hight_lights)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra(KEY_PREVIOUS_VIDEO)?.let {
            videoUrl = it
            setupPlayer()
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.getInt(KEY_MEDIA_ITEM) != 0) {
                val restoredMediaItem = savedInstanceState.getInt(KEY_MEDIA_ITEM)
                val seekTime = savedInstanceState.getLong(KEY_SEEK_TIME)
                exoPlayer.seekTo(restoredMediaItem, seekTime)
                exoPlayer.play()
            }
        }
    }

    private fun setupPlayer() {
        with(binding) {
            exoPlayer = ExoPlayer.Builder(this@WatchMatchActivity).build().apply {
                setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
                prepare()
                playWhenReady = true
            }
            playerView.player = exoPlayer
            exoPlayer.addListener(this@WatchMatchActivity)
        }
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
    }

    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            Player.STATE_READY -> {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_SEEK_TIME, exoPlayer.currentPosition)
        outState.putInt(KEY_MEDIA_ITEM, exoPlayer.currentMediaItemIndex)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityWatchMatchBinding {
        return ActivityWatchMatchBinding.inflate(inflater)
    }

    companion object {
        const val KEY_PREVIOUS_VIDEO = "key_previous_video"
        const val KEY_SEEK_TIME = "key_seek_time"
        const val KEY_MEDIA_ITEM = "key_media_item"
    }
}