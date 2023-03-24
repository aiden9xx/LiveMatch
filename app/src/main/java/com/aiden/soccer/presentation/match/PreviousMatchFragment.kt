package com.aiden.soccer.presentation.match

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentUpcomingMatchBinding
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.match.adapter.PreviousMatchesAdapter
import com.aiden.soccer.presentation.team.ScoreViewModel
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
import data.entities.Previous
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class PreviousMatchFragment :
    BaseFragment<FragmentUpcomingMatchBinding, ScoreViewModel>(ScoreViewModel::class) {

    private val adapter = PreviousMatchesAdapter(this::onItemClicked)
    private val mainViewModel: MainViewModel by activityViewModels()

    override val layoutId: Int
        get() = R.layout.fragment_upcoming_match

    override fun initView(savedInstanceState: Bundle?) {
        binding.rvRecyclerview.adapter = adapter
        mainViewModel.match?.let {
            adapter.submitList(it.matches?.previous)
        }
    }

    private fun onItemClicked(match: MatchData) {
        if (match is Previous) {
            val intent = Intent(requireContext(), WatchMatchActivity::class.java)
            intent.putExtra(WatchMatchActivity.KEY_PREVIOUS_VIDEO, match.highlights )
            startActivity(intent)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentUpcomingMatchBinding {
        return FragmentUpcomingMatchBinding.inflate(inflater)
    }
}