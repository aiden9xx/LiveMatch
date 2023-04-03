package com.aiden.soccer.presentation.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aiden.soccer.databinding.ItemTeamBinding
import com.aiden.soccer.presentation.team.viewholder.TeamsViewHolder
import data.entities.Team

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class TeamAdapter(
    private val onItemClicked: (Team) -> Unit
) : ListAdapter<Team, TeamsViewHolder>(teamItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamsViewHolder(
        ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val teamItemCallBack = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
                oldItem == newItem
        }
    }
}
