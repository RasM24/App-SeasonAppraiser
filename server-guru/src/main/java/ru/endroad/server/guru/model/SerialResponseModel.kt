package ru.endroad.server.guru.model

import ru.endroad.shared.serial.entity.Serial

internal class SerialResponseModel(val series: List<SerialModel>)

data class SerialModel(
	val id: String,
	val imdbRating: Float,
	val name: String,
	val posterUrl: String
)

internal fun convertModel(serialModel: SerialModel): Serial =
	Serial(
		id = serialModel.id,
		name = serialModel.name,
		posterUrl = serialModel.posterUrl,
		rating = serialModel.imdbRating.toDouble(),
		seasonList = listOf()
	)