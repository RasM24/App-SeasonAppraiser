package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.RecommendedSerialsDataSource
import ru.endroad.shared.serial.entity.Serial
import kotlin.random.Random

internal class RecommendedSerialsDataSourceImpl(private val api: BrowseApi) : RecommendedSerialsDataSource {

	private val randomPage get() = Random.nextInt(10)

	override suspend fun get(): List<Serial> =
		api.getSerials(randomPage).series.map(::convertModel)
}