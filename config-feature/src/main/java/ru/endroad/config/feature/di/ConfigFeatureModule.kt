package ru.endroad.config.feature.di

import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy
import ru.endroad.config.feature.data.FeatureConfigDataSource
import ru.endroad.config.feature.data.FeatureConfigDataSourceLocal
import ru.endroad.config.feature.domain.GetFeatureConfigUseCase

val configFeatureModule = module {
	singleBy<FeatureConfigDataSource, FeatureConfigDataSourceLocal>()

	single<GetFeatureConfigUseCase>()
	single { get<GetFeatureConfigUseCase>().invoke() }
}