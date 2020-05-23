package ru.endroad.server.guru

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.endroad.server.guru.api.BrowseApi
import ru.endroad.server.guru.api.SERVER_URL
import ru.endroad.server.guru.api.SeriesApi
import ru.endroad.server.guru.datasource.BestSerialsDataSourceImpl
import ru.endroad.server.guru.datasource.PopularSerialsDataSourceImpl
import ru.endroad.server.guru.datasource.RecommendedSerialsDataSourceImpl
import ru.endroad.server.guru.datasource.SeriesDataSourceImpl
import ru.endroad.shared.serial.data.BestSerialsDataSource
import ru.endroad.shared.serial.data.PopularSerialsDataSource
import ru.endroad.shared.serial.data.RecommendedSerialsDataSource
import ru.endroad.shared.serial.data.SeriesDataSource

val guruServerModule = module {
	single {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY
		val httpClient = OkHttpClient.Builder()
		httpClient.addInterceptor(logging)

		Retrofit.Builder()
			.baseUrl(SERVER_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(httpClient.build())
			.build()
	}

	single { get<Retrofit>().create<BrowseApi>() }
	single { get<Retrofit>().create<SeriesApi>() }

	singleBy<BestSerialsDataSource, BestSerialsDataSourceImpl>()
	singleBy<PopularSerialsDataSource, PopularSerialsDataSourceImpl>()
	singleBy<RecommendedSerialsDataSource, RecommendedSerialsDataSourceImpl>()
	singleBy<SeriesDataSource, SeriesDataSourceImpl>()
}