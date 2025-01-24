package com.example.githubsearchapp.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.githubsearchapp.domain.models.DateModel
import com.example.githubsearchapp.domain.models.DateTimeModel
import com.example.githubsearchapp.domain.models.TimeModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ParseDateTimeUseCase() {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(input: String?): DateTimeModel {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val dateTime = ZonedDateTime.parse(input, formatter)

        val date = DateModel(
            day = dateTime.dayOfMonth,
            month = dateTime.monthValue,
            year = dateTime.year
        )
        
        val time = TimeModel(
            hour = dateTime.hour,
            minute = dateTime.minute,
            second = dateTime.second
        )
        
        return DateTimeModel(date = date, time = time)
    }
}