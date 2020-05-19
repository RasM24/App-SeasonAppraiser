package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.model.SerialModel
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.PopularSerialsDataSource
import ru.endroad.shared.serial.entity.Serial
import kotlin.random.Random

internal class PopularSerialsDataSourceImpl(private val api: BrowseApi) : PopularSerialsDataSource {

	private val randomPage get() = Random.nextInt(20)

	override suspend fun get(): List<Serial> =
		api.getSerials(randomPage).map(::convertModel)
}