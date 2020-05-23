package ru.endroad.server.guru.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.endroad.server.guru.model.SerialResponseModel

internal interface BrowseApi {

	@GET("/browse")
	suspend fun getSerials(@Query("page") page: Int, @Query("query") query: String? = null): SerialResponseModel
}