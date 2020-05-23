package ru.endroad.config.feature.data

import ru.endroad.config.feature.entity.FeatureConfig

interface FeatureConfigDataSource {
	fun get(): FeatureConfig
}