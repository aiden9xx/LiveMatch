package com.aiden.soccer.presentation.match.viewholder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiden.soccer.R
import com.aiden.soccer.databinding.ItemMatchPreviosBinding
import com.aiden.soccer.extension.getMatchDateMonth
import com.aiden.soccer.utils.TeamLogoManager
import data.entities.MatchData
import data.entities.Previous

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class PreviousMatchViewHolder(private val binding: ItemMatchPreviosBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(match: MatchData, onItemClicked: (MatchData) -> Unit) {
        when (match) {
            is Previous -> {
                binding.tvTeamA.text = match.home
                binding.tvTeamB.text = match.away
                binding.tvDay.text = match.date?.getMatchDateMonth
                binding.tvDay.visibility = View.VISIBLE
                loadTeamLogo(
                    TeamLogoManager.getTeamLogo(match.home),
                    TeamLogoManager.getTeamLogo(match.away),
                )
                when {
                    match.home == match.winner -> {
                        binding.tvTeamA.setTextColor(Color.parseColor(COLOR_WINNER))
                        binding.tvTeamB.setTextColor(Color.BLACK)
                        binding.tvDate.text = FAKE_SCORE_TEAM_A_WIN
                    }
                    match.away == match.winner -> {
                        binding.tvTeamA.setTextColor(Color.BLACK)
                        binding.tvTeamB.setTextColor(Color.parseColor(COLOR_WINNER))
                        binding.tvDate.text = FAKE_SCORE_TEAM_B_WIN
                    }
                    else -> {
                        binding.tvTeamA.setTextColor(Color.BLACK)
                        binding.tvTeamB.setTextColor(Color.BLACK)
                        binding.tvDate.text = FAKE_SCORE_DRAW
                    }
                }

                binding.root.setOnClickListener {
                    onItemClicked(match)
                }
            }
        }
    }

    private fun loadTeamLogo(logoTeamAUrl: String?, logoTeamBUrl: String?) {
        if (logoTeamAUrl.isNullOrEmpty() || logoTeamBUrl.isNullOrEmpty())  return
        binding.imvTeamA.load(logoTeamAUrl) {
            placeholder(R.drawable.ic_soccer_place_holder)
            error(R.drawable.ic_soccer_place_holder)
        }
        binding.imvTeamB.load(logoTeamBUrl) {
            placeholder(R.drawable.ic_soccer_place_holder)
            error(R.drawable.ic_soccer_place_holder)
        }
    }

    companion object {
        const val FAKE_SCORE_TEAM_A_WIN = "1 : 0"
        const val FAKE_SCORE_TEAM_B_WIN = "0 : 2"
        const val FAKE_SCORE_DRAW = "2 : 2"
        const val COLOR_WINNER = "#44A348"
    }
}