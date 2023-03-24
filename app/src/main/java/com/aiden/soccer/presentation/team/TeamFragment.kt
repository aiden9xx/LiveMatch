package com.aiden.soccer.presentation.team

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentTeamBinding
import com.aiden.soccer.extension.convertDateToLong
import com.aiden.soccer.presentation.base.BaseFragment
import com.aiden.soccer.presentation.match.MatchActivity
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.presentation.team.adapter.TeamAdapter
import com.aiden.soccer.utils.navigateToCalendar
import dagger.hilt.android.AndroidEntryPoint
import data.entities.MatchData
import data.entities.Team
import data.entities.Upcoming
import data.remote.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
/**
 * [TeamFragment] will list [Team] items.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TeamFragment : BaseFragment<FragmentTeamBinding, ScoreViewModel>(ScoreViewModel::class) {

    private val teamAdapter = TeamAdapter(this::onItemClicked)
    private val allMatchesAdapter = AllMatchesAdapter(this::onMatchItemClicked)

    override val layoutId: Int
        get() = R.layout.fragment_team

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentTeamBinding {
        return FragmentTeamBinding.inflate(inflater)
    }

    override fun onSubscribeObserver() {
        viewModelSelf.teamsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading<*> -> {}
                is Resource.Success -> {
                    if (state.data!!.isNotEmpty()) {
                        teamAdapter.submitList(state.data!!.filter { it.logo != null })
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModelSelf.matchesLiveData.observe(viewLifecycleOwner) { match ->
            allMatchesAdapter.submitList(match.matches?.upcoming?.take(5))
        }

        fetchData()
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(binding) {
            rvTeams.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = teamAdapter
            }

            rvUpcomingMatches.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = allMatchesAdapter
            }

            tvViewAll.setOnClickListener {
                findNavController().navigate(R.id.action_teamFragment_to_allMatchesFragment)
            }
        }
    }

    private fun fetchData() {
        with(viewModelSelf) {
            getTeams()
            getMatches()
        }
    }

    private fun onItemClicked(team: Team) {
        val intent = Intent(context, MatchActivity::class.java)
        intent.putExtra(MatchActivity.KEY_TEAM_ID, team.id)
        startActivity(intent)
    }

    private fun onMatchItemClicked(matchData: MatchData) {
        if (matchData is Upcoming) {
            requireActivity().navigateToCalendar(matchData.description, matchData.date?.convertDateToLong)
        }
    }
}