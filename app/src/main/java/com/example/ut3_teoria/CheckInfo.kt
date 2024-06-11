package com.example.ut3_teoria

data class CheckInfo(
    var title: String,
    var selected: Boolean,
    var onCheckedChange: (Boolean) -> Unit
)
