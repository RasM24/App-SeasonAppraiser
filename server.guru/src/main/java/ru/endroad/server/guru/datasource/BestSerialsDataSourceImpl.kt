package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.BestSerialsDataSource
import ru.endroad.shared.serial.entity.Serial

internal class BestSerialsDataSourceImpl(private val api: BrowseApi) : BestSerialsDataSource {

	override suspend fun get(page: Int): List<Serial> =
		api.getSerials(page).map(::convertModel)
}