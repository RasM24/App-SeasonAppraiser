package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.SerialsDataSource
import ru.endroad.shared.serial.entity.Serial

internal class SerialsDataSourceImpl(private val api: BrowseApi) : SerialsDataSource {

	override suspend fun get(name: String): List<Serial> =
		api.getSerials(0, name).series.map(::convertModel)
}