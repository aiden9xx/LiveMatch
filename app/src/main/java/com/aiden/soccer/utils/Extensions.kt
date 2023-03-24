@file:Suppress("unused")

package com.aiden.soccer.utils
import android.app.Activity
import android.content.Intent
import android.provider.CalendarContract

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */

fun Activity.navigateToCalendar(description: String?, timeStart: Long?) {
    val intent = Intent(Intent.ACTION_INSERT)
    intent.data = CalendarContract.Events.CONTENT_URI
    intent.putExtra(CalendarContract.Events.TITLE, description)
    intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
    intent.putExtra(CalendarContract.Events.DTSTART, timeStart)
    intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
    intent.putExtra(CalendarContract.Events.HAS_ALARM, true)
    intent.putExtra(CalendarContract.Events.ALL_DAY, false)
    startActivity(intent)
}
