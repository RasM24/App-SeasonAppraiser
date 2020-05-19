package ru.endroad.shared.serial.data

import ru.endroad.shared.serial.entity.Serial

interface RecommendedSerialsDataSource {
	suspend fun get(): List<Serial>
}