package ru.endroad.shared.serial.domain

import ru.endroad.shared.serial.data.SerialsDataSource
import ru.endroad.shared.serial.entity.Serial

class GetSerialListByNameUseCase(private val serialsDataSource: SerialsDataSource) {
	suspend operator fun invoke(name: String): List<Serial> = serialsDataSource.get(name)
}