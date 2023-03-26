package com.aiden.soccer.extension

import java.text.SimpleDateFormat
import java.util.*

val String.getMatchTime: String?
    get() {
        val iso8601DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )

        val date = iso8601DateFormat.parse(this)

        val newFormat = SimpleDateFormat(
            "hh:mm aa",
            Locale.getDefault()
        )

        date?.let {
            return newFormat.format(it).lowercase()
        }

        return null
    }

val String.getMatchDateMonth: String?
    get() {
        try {
            val iso8601DateFormat = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            )

            val date = iso8601DateFormat.parse(this)

            val newFormat = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

            date?.let {
                return newFormat.format(it).lowercase()
            }
        } catch (ex: Exception) {
            return null
        }

        return null
    }


val String.convertDateToLong: Long?
    get() {
        val iso8601DateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )

        return iso8601DateFormat.parse(this)?.time
    }
