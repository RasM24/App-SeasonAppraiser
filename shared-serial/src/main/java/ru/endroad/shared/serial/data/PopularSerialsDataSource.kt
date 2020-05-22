package ru.endroad.shared.serial.data

import ru.endroad.shared.serial.entity.Serial

interface PopularSerialsDataSource {
	suspend fun get(): List<Serial>
}