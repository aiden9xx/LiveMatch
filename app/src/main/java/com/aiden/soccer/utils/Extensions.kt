@file:Suppress("unused")

package com.aiden.soccer.utils
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
