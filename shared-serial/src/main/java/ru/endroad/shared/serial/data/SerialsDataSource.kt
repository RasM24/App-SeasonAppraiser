package ru.endroad.shared.serial.data

import ru.endroad.shared.serial.entity.Serial

interface SerialsDataSource {
	suspend fun get(name: String): List<Serial>
}