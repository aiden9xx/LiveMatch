package com.aiden.soccer.presentation.match.all

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentAllMatchesBinding
import com.aiden.soccer.extension.convertDateToLong
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.match.WatchMatchActivity
import com.aiden.soccer.presentation.team.ScoreViewModel
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.utils.MatchManager
import com.aiden.soccer.utils.navigateToCalendar
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
import data.entities.Previous
import data.entities.Upcoming
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class AllMatchesFragment :
    BaseFragment<FragmentAllMatchesBinding, ScoreViewModel>(ScoreViewModel::class) {

    private val allMatchesAdapter = AllMatchesAdapter(this::onMatchItemClicked)
    private val previousMatchesAdapter = AllMatchesAdapter(this::onPreviousMatchItemClicked)

    override val layoutId: Int
        get() = R.layout.fragment_all_matches

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentAllMatchesBinding {
        return FragmentAllMatchesBinding.inflate(inflater)
    }

    override fun initAction() {
        viewModelSelf.getMatches()
        binding.lnHeader.imvBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onSubscribeObserver() {
        super.onSubscribeObserver()
        viewModelSelf.matchesLiveData.observe(viewLifecycleOwner) { match ->
            allMatchesAdapter.submitList(MatchManager.getUpcomingListWithDays(match.matches?.upcoming?.toMutableList()) as List<MatchData>?)
            previousMatchesAdapter.submitList(match.matches?.previous)
            with(binding) {
                tvUpcoming.visibility = View.VISIBLE
                tvPrevious.visibility = View.VISIBLE
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.rvUpcomingMatches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUpcomingMatches.adapter = allMatchesAdapter

        binding.rvPreviousMatches.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = previousMatchesAdapter
        }
    }

    private fun onMatchItemClicked(matchData: MatchData) {
        if (matchData is Upcoming) {
            requireActivity().navigateToCalendar(
                matchData.description,
                matchData.date?.convertDateToLong
            )
        }
    }

    private fun onPreviousMatchItemClicked(matchData: MatchData) {
        if (matchData is Previous) {
            val intent = Intent(requireContext(), WatchMatchActivity::class.java)
            intent.putExtra(WatchMatchActivity.KEY_PREVIOUS_VIDEO, matchData.highlights )
            startActivity(intent)
        }
    }
}