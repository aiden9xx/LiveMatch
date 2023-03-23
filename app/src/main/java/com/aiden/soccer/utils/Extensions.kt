@file:Suppress("unused")

package com.aiden.soccer.utils
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
fun String.dateTimeFormat(): String {
    return if (!this.isNullOrEmpty()) {
        val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val requiredSdf = SimpleDateFormat("MM.dd.yyyy HH:mm", Locale.getDefault())
        requiredSdf.format(sourceSdf.parse(this))
    } else {
        ""
    }
}

fun Activity.navigateToCalendar(description: String?) {
    val intent = Intent(Intent.ACTION_INSERT)
    intent.data = CalendarContract.Events.CONTENT_URI
    intent.putExtra(CalendarContract.Events.TITLE, description)
    intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
    intent.putExtra(CalendarContract.Events.HAS_ALARM, true)
    intent.putExtra(CalendarContract.Events.ALL_DAY, false)
    startActivity(intent)
}
