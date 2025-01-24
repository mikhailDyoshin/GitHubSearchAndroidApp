package com.example.githubsearchapp.domain.models

data class DateModel(val day: Int, val month: Int, val year: Int) {
    override fun toString(): String {
        return "$day.$month.$year"
    }
}
