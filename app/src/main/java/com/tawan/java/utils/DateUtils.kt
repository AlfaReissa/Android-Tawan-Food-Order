package com.tawan.java.utils

import java.text.SimpleDateFormat

class DateUtils {

    fun getReadableDate(unix:String): String? {
        val redr = unix.toLong()
        val sdf = SimpleDateFormat("MMMM d, yyyy 'at' h:mm a")
        val date = sdf.format(redr)
        return date
    }



}