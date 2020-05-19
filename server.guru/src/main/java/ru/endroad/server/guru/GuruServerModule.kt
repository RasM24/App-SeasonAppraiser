package ru.endroad.server.guru

import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.api.SERVER_URL
import ru.endroad.server.guru.datasource.BestSerialsDataSourceImpl
import ru.endroad.server.guru.datasource.PopularSerialsDataSourceImpl
import ru.endroad.server.guru.datasource.RecommendedSerialsDataSourceImpl
import ru.endroad.shared.serial.data.BestSerialsDataSource
import ru.endroad.shared.serial.data.PopularSerialsDataSource
import ru.endroad.shared.serial.data.RecommendedSerialsDataSource

val guruServerModule = module {
	retrofitApi<BrowseApi>()

	singleBy<BestSerialsDataSource, BestSerialsDataSourceImpl>()
	singleBy<PopularSerialsDataSource, PopularSerialsDataSourceImpl>()
	singleBy<RecommendedSerialsDataSource, RecommendedSerialsDataSourceImpl>()
}

private val retrofit = Retrofit.Builder()
	.baseUrl(SERVER_URL)
	.addConverterFactory(GsonConverterFactory.create())
	.build()

private inline fun <reified API : Any> Module.retrofitApi() = single { retrofit.create(API::class.java) }