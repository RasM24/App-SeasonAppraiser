package ru.endroad.shared.serial.domain

import ru.endroad.shared.serial.data.SeriesDataSource
import ru.endroad.shared.serial.entity.Serial

class GetSerialDetailUseCase(private val seriesDataSource: SeriesDataSource) {
	suspend operator fun invoke(): Serial = seriesDataSource.get()
}