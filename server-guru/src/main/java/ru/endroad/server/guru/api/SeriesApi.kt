package ru.endroad.server.guru.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.endroad.server.guru.model.SeriesV2Response

interface SeriesApi {

	@GET("/series/v2/{id}")
	suspend fun getSeries(@Path("id") seriesId: String): SeriesV2Response
}