package ru.endroad.shared.serial.domain

import ru.endroad.shared.serial.data.RecommendedSerialsDataSource
import ru.endroad.shared.serial.entity.Serial

class GetRecommendedSerialsUseCase(private val serialsDataSource: RecommendedSerialsDataSource) {
	suspend operator fun invoke(): List<Serial> = serialsDataSource.get()
}