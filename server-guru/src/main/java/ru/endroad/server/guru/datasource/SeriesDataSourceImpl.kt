package ru.endroad.server.guru.datasource

import ru.endroad.server.guru.api.SeriesApi
import ru.endroad.server.guru.model.convertModel
import ru.endroad.shared.serial.data.SeriesDataSource
import ru.endroad.shared.serial.entity.Episode
import ru.endroad.shared.serial.entity.Season
import ru.endroad.shared.serial.entity.Serial
import kotlin.random.Random

internal class SeriesDataSourceImpl(private val api: SeriesApi) : SeriesDataSource {

	override suspend fun get(seriesId: String): Serial =
		api.getSeries(seriesId).let(::convertModel)
//		Serial(
//			id = "",
//			name = "",
//			posterUrl = "",
//			rating = .0,
//			seasonList = listOf(
//				short,
//				medium,
//				long,
//				short,
//				medium,
//				short,
//				medium,
//				long,
//				short,
//				medium,
//				short,
//				medium,
//				long,
//				short,
//				medium,
//				short,
//				medium,
//				long,
//				short,
//				medium
//			)
//		)
//
//	private val short = Season(0, List(8) { Episode(it, randomRating) })
//	private val medium = Season(0, List(15) { Episode(it, randomRating) })
//	private val long = Season(0, List(30) { Episode(it, randomRating) })
//
//	private val randomRating get() = Random.nextDouble(10.0)
}