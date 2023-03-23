package com.aiden.soccer.presentation.match.all

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentAllMatchesBinding
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.team.ScoreViewModel
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.utils.navigateToCalendar
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
import data.entities.Upcoming
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class AllMatchesFragment :
    BaseFragment<FragmentAllMatchesBinding, ScoreViewModel>(ScoreViewModel::class) {

    private val allMatchesAdapter = AllMatchesAdapter(this::onMatchItemClicked)

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
            allMatchesAdapter.submitList(match.matches?.upcoming)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.rvAllMatches.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllMatches.adapter = allMatchesAdapter
    }

    private fun onMatchItemClicked(matchData: MatchData) {
        if (matchData is Upcoming) {
            requireActivity().navigateToCalendar(matchData.description)
        }
    }
}