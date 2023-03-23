package com.aiden.soccer.presentation.match.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiden.soccer.R
import com.aiden.soccer.databinding.ItemMatchBinding
import com.aiden.soccer.extension.getDate
import com.aiden.soccer.extension.toConfirmTime
import com.aiden.soccer.utils.TeamLogoManager
import data.entities.MatchData
import data.entities.Previous
import data.entities.Upcoming

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class UpcomingMatchViewHolder(private val binding: ItemMatchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(match: MatchData, onItemClicked: (MatchData) -> Unit) {
        when (match) {
            is Upcoming -> {
                loadTeamLogo(
                    TeamLogoManager.getTeamLogo(match.home),
                    TeamLogoManager.getTeamLogo(match.away),
                )
                binding.tvTeamA.text = match.home
                binding.tvTeamB.text = match.away
                binding.tvDate.text = match.date?.toConfirmTime
                binding.tvDateTime.text = match.date?.getDate
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
}