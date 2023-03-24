package com.aiden.soccer.presentation.match

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentUpcomingMatchBinding
import com.aiden.soccer.extension.convertDateToLong
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.team.ScoreViewModel
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import com.aiden.soccer.utils.navigateToCalendar
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
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
            requireActivity().navigateToCalendar(matchData.description, matchData.date?.convertDateToLong)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentUpcomingMatchBinding {
        return FragmentUpcomingMatchBinding.inflate(inflater)
    }
}