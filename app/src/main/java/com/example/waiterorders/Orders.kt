package com.example.waiterorders

data class Orders(
    val title: String,
    val exTip: Int,
    var isChecked: Boolean = false
)