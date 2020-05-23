package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.SeriesApi
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.SeriesDataSource
import ru.endroad.shared.serial.entity.Serial

internal class SeriesDataSourceImpl(private val api: SeriesApi) : SeriesDataSource {

	override suspend fun get(seriesId: String): Serial =
		api.getSeries(seriesId).let(::convertModel)
}