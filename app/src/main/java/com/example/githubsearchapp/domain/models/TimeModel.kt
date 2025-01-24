package com.example.githubsearchapp.domain.models

data class TimeModel(val hour: Int, val minute: Int, val second: Int) {
    override fun toString(): String {
        return "$hour:$minute:$second"
    }
}
