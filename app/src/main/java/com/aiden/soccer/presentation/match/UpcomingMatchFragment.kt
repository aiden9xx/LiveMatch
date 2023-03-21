package com.aiden.soccer.presentation.match

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentUpcomingMatchBinding
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.match.adapter.PreviousMatchesAdapter
import com.aiden.soccer.presentation.team.ScoreViewModel
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
import data.entities.Previous
import data.entities.Upcoming
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UpcomingMatchFragment :
    BaseFragment<FragmentUpcomingMatchBinding, ScoreViewModel>(ScoreViewModel::class) {

    private val adapter = AllMatchesAdapter(this::onMatchItemClicked)
    private val mainViewModel: MainViewModel by activityViewModels()

    override val layoutId: Int
        get() = R.layout.fragment_upcoming_match

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.run {
            rvRecyclerview.adapter = adapter
        }

        mainViewModel.match?.let {
            adapter.submitList(it.matches?.upcoming)
        }
    }

    private fun onMatchItemClicked(matchData: MatchData) {
        if (matchData is Upcoming) {
            val intent = Intent(Intent.ACTION_INSERT)
            intent.data = CalendarContract.Events.CONTENT_URI
            intent.putExtra(CalendarContract.Events.TITLE, matchData.description)
            intent.putExtra(CalendarContract.Events.DESCRIPTION, matchData.description)
            intent.putExtra(CalendarContract.Events.HAS_ALARM, true)
            intent.putExtra(CalendarContract.Events.ALL_DAY, false)
            startActivity(intent)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentUpcomingMatchBinding {
        return FragmentUpcomingMatchBinding.inflate(inflater)
    }
}