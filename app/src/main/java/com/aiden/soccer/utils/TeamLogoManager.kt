package com.aiden.soccer.utils

class TeamLogoManager {
    companion object {
        fun getTeamLogo(teamName: String?) : String {
            return when (teamName) {
                "Team Red Dragons" -> "https://tstzj.s3.amazonaws.com/dragons.png"
                "Team Cool Eagles" -> "https://tstzj.s3.amazonaws.com/eagle.png"
                "Team Chill Elephants" -> "https://tstzj.s3.amazonaws.com/elephant.png"
                "Team Win Kings" -> "https://tstzj.s3.amazonaws.com/kings.png"
                "Team Angry Pandas" -> "https://tstzj.s3.amazonaws.com/panda.png"
                "Team Fiery Phoenix" -> "https://tstzj.s3.amazonaws.com/phoenix.png"
                "Team Hungry Sharks" -> "https://tstzj.s3.amazonaws.com/sharks.png"
                "Team Growling Tigers" -> "https://tstzj.s3.amazonaws.com/tiger.png"
                "Team Royal Knights" -> "https://tstzj.s3.amazonaws.com/knights.png"
                else -> "https://tstzj.s3.amazonaws.com/lion.png"
            }
        }
    }
}