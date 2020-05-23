package ru.endroad.config.feature.domain

import ru.endroad.config.feature.data.FeatureConfigDataSource
import ru.endroad.config.feature.entity.FeatureConfig

class GetFeatureConfigUseCase(private val dataSource: FeatureConfigDataSource) {
	operator fun invoke(): FeatureConfig = dataSource.get()
}