package com.desafiolatam.roomemployee.data

import android.media.Image
import android.util.Base64
import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converters {

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }



}