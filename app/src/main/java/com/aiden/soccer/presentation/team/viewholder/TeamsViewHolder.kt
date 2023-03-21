package com.aiden.soccer.presentation.team.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aiden.soccer.R
import com.aiden.soccer.databinding.ItemTeamBinding
import data.entities.Team

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class TeamsViewHolder(private val binding: ItemTeamBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(team: Team, onItemClicked: (Team) -> Unit) {
        binding.imvTeamA.load(team.logo) {
            placeholder(R.drawable.ic_soccer_place_holder)
            error(R.drawable.ic_soccer_place_holder)
        }
        binding.tvTeamName.text = team.name

        binding.root.setOnClickListener {
            onItemClicked(team)
        }
    }
}