package ru.endroad.shared.serial.di

import org.koin.dsl.module
import org.koin.experimental.builder.single
import ru.endroad.shared.serial.domain.GetBestSerialsUseCase
import ru.endroad.shared.serial.domain.GetPopularSerialsUseCase
import ru.endroad.shared.serial.domain.GetRecommendedSerialsUseCase
import ru.endroad.shared.serial.domain.GetSerialDetailUseCase

val sharedSerialModule = module {
	single<GetPopularSerialsUseCase>()
	single<GetRecommendedSerialsUseCase>()
	single<GetBestSerialsUseCase>()
	single<GetSerialDetailUseCase>()
}