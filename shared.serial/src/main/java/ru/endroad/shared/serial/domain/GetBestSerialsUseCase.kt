package ru.endroad.shared.serial.domain

import ru.endroad.shared.serial.data.BestSerialsDataSource
import ru.endroad.shared.serial.entity.Serial

class GetBestSerialsUseCase(private val serialsDataSource: BestSerialsDataSource) {
	suspend operator fun invoke(): List<Serial> = serialsDataSource.get()
}