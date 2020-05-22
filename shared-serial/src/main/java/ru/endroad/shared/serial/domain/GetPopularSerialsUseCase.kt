package ru.endroad.shared.serial.domain

import ru.endroad.shared.serial.data.PopularSerialsDataSource
import ru.endroad.shared.serial.entity.Serial

class GetPopularSerialsUseCase(private val serialsDataSource: PopularSerialsDataSource) {
	suspend operator fun invoke(): List<Serial> = serialsDataSource.get()
}