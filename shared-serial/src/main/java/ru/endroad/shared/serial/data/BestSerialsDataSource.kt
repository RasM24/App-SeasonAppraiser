package ru.endroad.shared.serial.data

import ru.endroad.shared.serial.entity.Serial

interface BestSerialsDataSource {
	suspend fun get(page: Int = 0): List<Serial>
}