package ru.endroad.shared.serial.data

import ru.endroad.shared.serial.entity.Serial

interface SeriesDataSource {
	suspend fun get(seriesId: String): Serial
}