package com.aiden.soccer.utils

import com.aiden.soccer.extension.getMatchDateMonth
import data.entities.Upcoming

class MatchManager {
    companion object {
        fun getUpcomingListWithDays(list: MutableList<Upcoming>?): MutableList<Upcoming> {
            if (list.isNullOrEmpty()) return mutableListOf()
            for (i in 0 until list.size) {
                list[i].day = list[i].date?.getMatchDateMonth
            }

            for (i in 0 until list.size) {
                val day = list[i].day
                if (!day.isNullOrEmpty()) {
                    for (j in i + 1 until list.size) {
                        if (day == list[j].day) {
                            list[j].day = ""
                        }
                    }
                }
            }

            return list
        }
    }
}