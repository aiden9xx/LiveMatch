package com.aiden.soccer.presentation.team

import android.content.Intent
import android.provider.CalendarContract
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.soccer.R
import com.aiden.soccer.databinding.FragmentTeamBinding
import com.aiden.soccer.presentation.match.MatchActivity
import com.aiden.soccer.presentation.team.adapter.AllMatchesAdapter
import com.aiden.soccer.presentation.team.adapter.TeamAdapter
import com.aiden.soccer.utils.NetworkUtils
import com.aiden.soccer.utils.animationHide
import com.aiden.soccer.utils.show
import com.aiden.soccer.utils.viewBinding
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
class TeamFragment : Fragment(R.layout.fragment_team) {

    private val binding by viewBinding { FragmentTeamBinding.bind(it) }
    private val viewModel by viewModels<ScoreViewModel>()
    private val adapter = TeamAdapter(this::onItemClicked)
    private val allMatchesAdapter = AllMatchesAdapter(this::onMatchItemClicked)

    override fun onStart() {
        super.onStart()
        initView()
        observeTeams()
        observeNetwork()
    }

    /**
     * Observe data
     * ( we also can create a BaseFragment and the put abstract
     * class observeTeams in BaseFragment )
     */
    private fun observeTeams() {
        viewModel.teamsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading<*> -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    if (state.data!!.isNotEmpty()) {
                        adapter.submitList(state.data!!.filter { it.logo != null })
                        showLoading(false)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }

        viewModel.matchesLiveData.observe(viewLifecycleOwner) { match ->
            allMatchesAdapter.submitList(match.matches?.upcoming?.take(5))
        }
    }

    private fun getTeams() = viewModel.getTeams()

    /**
     * Initialize recyclerview
     */
    private fun initView() {
        binding.run {
            val linearLayoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            rvRecyclerview.layoutManager = linearLayoutManager
            rvRecyclerview.adapter = adapter

            val linearLayoutManager2 = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            rvAllMatches.layoutManager = linearLayoutManager2
            rvAllMatches.adapter = allMatchesAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {

    }

    /**
     * Navigate to the detail fragment.
     */
    private fun onItemClicked(team: Team) {
        val intent = Intent(context, MatchActivity::class.java)
        intent.putExtra(MatchActivity.KEY_TEAM_ID, team.id)
        startActivity(intent)
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

    /**
     * When network change ( We can bring it to BaseFragment )
     */
    private fun observeNetwork() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.tvNetworkStatus.text =
                    getString(R.string.no_internet)
                binding.lnNetwork.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.fail
                        )
                    )
                }
            } else {
                if (viewModel.teamsLiveData.value is Resource.Error || adapter.itemCount == 0) {
                    getTeams()
                    viewModel.getMatches()
                }
                binding.tvNetworkStatus.text =
                    getString(R.string.internet_connected)
                binding.lnNetwork.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.greenColor
                        )
                    )
                    animationHide()
                }
            }
        }
    }
}