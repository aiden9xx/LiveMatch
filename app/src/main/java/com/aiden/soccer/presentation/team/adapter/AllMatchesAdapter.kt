package com.aiden.soccer.presentation.team.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aiden.soccer.databinding.ItemMatchBinding
import com.aiden.soccer.presentation.match.viewholder.UpcomingMatchViewHolder
import data.entities.MatchData

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class AllMatchesAdapter(
    private val onItemClicked: (MatchData) -> Unit
) : ListAdapter<MatchData, UpcomingMatchViewHolder>(teamItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UpcomingMatchViewHolder(
        ItemMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UpcomingMatchViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val teamItemCallBack = object : DiffUtil.ItemCallback<MatchData>() {
            override fun areItemsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem == newItem
        }
    }
}
