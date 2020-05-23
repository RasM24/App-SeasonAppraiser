package ru.endroad.shared.serial.entity

data class Serial(val id: String, val name: String, val posterUrl: String, val rating: Double, val seasonList: List<Season>)