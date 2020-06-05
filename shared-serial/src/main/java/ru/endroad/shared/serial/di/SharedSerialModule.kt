package ru.endroad.shared.serial.di

import org.koin.dsl.module
import org.koin.experimental.builder.single
import ru.endroad.shared.serial.domain.*

val sharedSerialModule = module {
	single<GetPopularSerialsUseCase>()
	single<GetRecommendedSerialsUseCase>()
	single<GetBestSerialsUseCase>()
	single<GetSerialDetailUseCase>()
	single<GetSerialListByNameUseCase>()
}