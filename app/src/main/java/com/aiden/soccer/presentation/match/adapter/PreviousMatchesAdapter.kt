package com.aiden.soccer.presentation.match.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aiden.soccer.databinding.ItemMatchPreviosBinding
import com.aiden.soccer.presentation.match.viewholder.PreviousMatchViewHolder
import data.entities.MatchData

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
class PreviousMatchesAdapter(
    private val onItemClicked: (MatchData) -> Unit
) : ListAdapter<MatchData, PreviousMatchViewHolder>(matchItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PreviousMatchViewHolder(
        ItemMatchPreviosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PreviousMatchViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val matchItemCallBack = object : DiffUtil.ItemCallback<MatchData>() {
            override fun areItemsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem == newItem

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MatchData, newItem: MatchData): Boolean =
                oldItem == newItem
        }
    }
}
