package ru.endroad.feature.feed.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ru.endroad.config.feature.entity.FeatureConfig
import ru.endroad.shared.serial.domain.GetBestSerialsUseCase
import ru.endroad.shared.serial.domain.GetPopularSerialsUseCase
import ru.endroad.shared.serial.domain.GetRecommendedSerialsUseCase

class FeedViewModel(
	private val getPopularSerials: GetPopularSerialsUseCase,
	private val getRecommendedSerials: GetRecommendedSerialsUseCase,
	private val getBestSerials: GetBestSerialsUseCase,
	val configuration: FeatureConfig
) : ViewModel() {

	//TODO обработчик ошибок
	val popularSerialList = liveData {
		val serialList = getPopularSerials()
		emit(serialList)
	}

	val recommendedSerialList = liveData {
		val serialList = getRecommendedSerials()
		emit(serialList)
	}

	val bestSerialList = liveData {
		val serialList = getBestSerials()
		emit(serialList)
	}

	fun openSerial(id: String) = Unit
}