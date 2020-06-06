package ru.endroad.server.guru.model

import ru.endroad.shared.serial.entity.Episode
import ru.endroad.shared.serial.entity.Season
import ru.endroad.shared.serial.entity.Serial

data class SeriesV2Response(
	val posterLink: String,
	val seasons: Int,
	val matrixEpisodes: List<MatrixEpisodes>
)

data class MatrixEpisodes(
	val season: Int,
	val episode: Int,
	val rating: Double
)

internal fun convertModel(seriesModel: SeriesV2Response): Serial =
	Serial(
		id = "null",
		name = "null",
		posterUrl = seriesModel.posterLink,
		rating = .0,
		seasonList = seriesModel.seasonList
	)

private val SeriesV2Response.seasonList: List<Season>
	get() = List(seasons) { matrixEpisodes.takeSeason(it+1) }

private fun List<MatrixEpisodes>.takeSeason(seasonNumber: Int): Season =
	filter { it.season == seasonNumber }
		.sortedBy(MatrixEpisodes::episode)
		.map(::convertMatrix)
		.let { Season(0, it) }

private fun convertMatrix(matrixEpisodes: MatrixEpisodes): Episode =
	Episode(matrixEpisodes.episode, matrixEpisodes.rating)