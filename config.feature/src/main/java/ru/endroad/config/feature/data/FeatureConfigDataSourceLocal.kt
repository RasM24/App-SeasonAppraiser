package ru.endroad.config.feature.data

import ru.endroad.config.feature.entity.FeatureConfig

class FeatureConfigDataSourceLocal : FeatureConfigDataSource {

	override fun get(): FeatureConfig = FeatureConfig(
		popularSerial = false,
		recommendedSerial = false
	)
}