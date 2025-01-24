package com.example.githubsearchapp.domain.models

data class DateTimeModel(val time: TimeModel, val date: DateModel) {
    override fun toString(): String {
        return "$date $time"
    }
}
